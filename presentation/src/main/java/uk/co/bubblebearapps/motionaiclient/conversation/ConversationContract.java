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

import java.util.List;

import uk.co.bubblebearapps.motionaiclient.base.BasePresenter;
import uk.co.bubblebearapps.motionaiclient.base.BaseView;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.ConversationBubble;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public interface ConversationContract {

    interface View extends BaseView {

        void clearInput();

        void setMessages(List<ConversationBubble> conversationBubbleList);

        void appendMessage(ConversationBubble message);

        void setInputText(String inputText);

        void setSendButtonEnabled(boolean show);

        void showErrorMessage();

        void openUrl(String url);

        void removeMessage(ConversationBubble message);

        void setColorScheme(int colorPrimary);

        void setTitle(String title);

        void swapMessage(ConversationBubble oldMessage, ConversationBubble newMessage);

        void playYouTubeVideo(String youtubeId);
    }

    interface Presenter extends BasePresenter<View> {

        void onInputTextUpdated(String inputText);

        void onSendButtonPress();

        void onClearButtonPress();

        void onRestartButtonPress();


        // list actions
        void onQuickReplyPress(QuickReplyModel quickReply);

        void onCardImagePress(CardModel cardModel);

        void onCardButtonPress(CardButtonModel cardButtonModel);

        void onUrlClick(String url);

        void onYouTubeThumbnailTapped(String youtubeId);
    }


}
