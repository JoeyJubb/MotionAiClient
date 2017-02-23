/*
 * Copyright 2017 Bubblebear Apps Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.bubblebearapps.motionaiclient.conversation;

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Subscriber;
import uk.co.bubblebearapps.motionaiclient.BotInfo;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.UserInfo;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.ConversationBubble;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.UserInputConversationBubble;
import uk.co.bubblebearapps.motionaiclient.interactor.MessageBot;
import uk.co.bubblebearapps.motionaiclient.mapper.BotResponseModelMapper;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by joefr_000 on 20/02/2017.
 */

public class ConversationPresenterTest {

    public static final int TEST_COLOR = Color.RED;
    private static final String LOREM = "lorem";
    private static final String EMPTY = "";

    @Mock
    MessageBot messageBot;
    @Mock
    BotInfo botInfo;
    @Mock
    UserInfo userInfo;

    @Mock
    ConversationContract.View view;
    ConversationPresenter conversationPresenter;

    @Test
    public void onDestroyed() throws Exception {

    }

    @Test
    public void onInputTextUpdated() throws Exception {
        reset(view);

        conversationPresenter.onInputTextUpdated(EMPTY);
        verify(view).setSendButtonEnabled(false);

        conversationPresenter.onInputTextUpdated(LOREM);
        verify(view).setSendButtonEnabled(true);
    }

    @Test
    public void onSendButtonPress() throws Exception {
        reset(view);
        reset(messageBot);
        conversationPresenter.onInputTextUpdated(LOREM);

        ArgumentCaptor<UserInputConversationBubble> quickReplyCaptor = ArgumentCaptor.forClass(UserInputConversationBubble.class);
        ArgumentCaptor<MessageBot.RequestValue> requestValueArgumentCaptor = ArgumentCaptor.forClass(MessageBot.RequestValue.class);

        conversationPresenter.onSendButtonPress();

        verify(messageBot).execute(requestValueArgumentCaptor.capture(), Matchers.<Subscriber<BotResponse>>any());
        verify(view).appendMessage(quickReplyCaptor.capture());
        verify(view).setInputText(null);
        verify(view).setSendButtonEnabled(false);

        assertEquals(LOREM, requestValueArgumentCaptor.getValue().getMessage());
        assertEquals(LOREM, quickReplyCaptor.getValue().getText());
    }

    @Test
    public void onQuickReplyPress() throws Exception {
        reset(view);
        reset(messageBot);

        QuickReplyModel quickReply = Mockito.mock(QuickReplyModel.class);
        when(quickReply.getTextContent()).thenReturn(LOREM);

        ArgumentCaptor<UserInputConversationBubble> quickReplyCaptor = ArgumentCaptor.forClass(UserInputConversationBubble.class);
        ArgumentCaptor<MessageBot.RequestValue> requestValueArgumentCaptor = ArgumentCaptor.forClass(MessageBot.RequestValue.class);

        conversationPresenter.onQuickReplyPress(quickReply);
        verify(view).appendMessage(quickReplyCaptor.capture());
        verify(messageBot).execute(requestValueArgumentCaptor.capture(), Matchers.<Subscriber<BotResponse>>any());

        assertEquals(LOREM, requestValueArgumentCaptor.getValue().getMessage());
        assertEquals(LOREM, quickReplyCaptor.getValue().getText());
    }

    @Test
    public void onCardImagePress() throws Exception {

        reset(view);

        CardModel cardModel = Mockito.mock(CardModel.class);
        when(cardModel.getUrl()).thenReturn(LOREM);
        conversationPresenter.onCardImagePress(cardModel);

        verify(view).openUrl(LOREM);

    }

    @Test
    public void onCardButtonPress() throws Exception {
        reset(view);

        conversationPresenter.onClearButtonPress();
        verify(view).setInputText(null);

    }

    @Test
    public void onClearButtonPress() throws Exception {
        reset(view);

        Class<List<ConversationBubble>> listClass = (Class<List<ConversationBubble>>) (Class) List.class;
        ArgumentCaptor<List<ConversationBubble>> argumentCaptor = ArgumentCaptor.forClass(listClass);

        conversationPresenter.onClearButtonPress();
        verify(view).setMessages(argumentCaptor.capture());

        assertEquals(0, argumentCaptor.getValue().size());

    }

    @Test
    public void onRestartButtonPress() throws Exception {

        reset(view);
        reset(messageBot);

        ArgumentCaptor<UserInputConversationBubble> uiCaptor = ArgumentCaptor.forClass(UserInputConversationBubble.class);
        ArgumentCaptor<MessageBot.RequestValue> rvCaptor = ArgumentCaptor.forClass(MessageBot.RequestValue.class);

        conversationPresenter.onRestartButtonPress();

        verify(messageBot).execute(rvCaptor.capture(), Matchers.<Subscriber<BotResponse>>any());
        verify(view).appendMessage(uiCaptor.capture());

        assertEquals(ConversationPresenter.RESTART_COMMAND, rvCaptor.getValue().getMessage());
        assertEquals(ConversationPresenter.RESTART_COMMAND, uiCaptor.getValue().getText());


    }

    @Test
    public void attachView() throws Exception {

        conversationPresenter.detachView();
        reset(view);

        conversationPresenter.onInputTextUpdated(LOREM);
        when(botInfo.getColor()).thenReturn(TEST_COLOR);
        when(botInfo.getName()).thenReturn(LOREM);
        List<ConversationBubble> messageList = Mockito.anyListOf(ConversationBubble.class);

        conversationPresenter.attachView(view);
        verify(view).setInputText(LOREM);
        verify(view).setColorScheme(TEST_COLOR);
        verify(view).setSendButtonEnabled(true);
        verify(view).setTitle(LOREM);
        verify(view).setMessages(messageList);
    }

    @Test
    public void detachView() throws Exception {
        //no op
    }


    @Test
    public void onUrlClick() throws Exception {

        reset(view);
        conversationPresenter.onUrlClick(LOREM);
        verify(view).openUrl(LOREM);
    }

    @Test
    public void onYouTubeThumbnailTapped() throws Exception {
        reset(view);
        conversationPresenter.onYouTubeThumbnailTapped(LOREM);
        verify(view).playYouTubeVideo(LOREM);
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        conversationPresenter = new ConversationPresenter(messageBot, botInfo, userInfo, new BotResponseModelMapper());
        conversationPresenter.attachView(view);
    }

}
