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

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.common.base.Strings;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rx.Subscriber;
import uk.co.bubblebearapps.motionaiclient.BotInfo;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.CardButton;
import uk.co.bubblebearapps.motionaiclient.UserInfo;
import uk.co.bubblebearapps.motionaiclient.conversation.model.BotResponseModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.ConversationBubble;
import uk.co.bubblebearapps.motionaiclient.conversation.model.ConversationBubbleVisitor;
import uk.co.bubblebearapps.motionaiclient.conversation.model.EndOfConversationBubble;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModelsList;
import uk.co.bubblebearapps.motionaiclient.conversation.model.UserInputConversationBubble;
import uk.co.bubblebearapps.motionaiclient.exception.EndOfConversationException;
import uk.co.bubblebearapps.motionaiclient.exception.RetryException;
import uk.co.bubblebearapps.motionaiclient.interactor.MessageBot;
import uk.co.bubblebearapps.motionaiclient.mapper.BotResponseModelMapper;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public class ConversationPresenter implements ConversationContract.Presenter {

    public static final String RESTART_COMMAND = "Restart";
    private final MessageBot messageBot;
    private final BotResponseModelMapper botResponseModelMapper;

    private final ConversationBubbleVisitor conversationBubbleVisitor;
    private final BotInfo botInfo;
    private final UserInfo userInfo;

    private ConversationContract.View mView;

    private List<ConversationBubble> mMessageList;
    private QuickReplyModelsList mQuickReplies;

    private String inputText;

    public ConversationPresenter(MessageBot messageBot, BotResponseModelMapper botResponseModelMapper, ConversationBubbleVisitor conversationBubbleVisitor, BotInfo botInfo, UserInfo userInfo) {
        this.messageBot = messageBot;
        this.botResponseModelMapper = botResponseModelMapper;
        this.conversationBubbleVisitor = conversationBubbleVisitor;
        this.botInfo = botInfo;
        this.userInfo = userInfo;

        mMessageList = new ArrayList<>();
        sendMessage(RESTART_COMMAND, false);
    }

    @Override
    public void onInputTextUpdated(String inputText) {
        this.inputText = inputText;
        applySendButton();
    }

    private void applySendButton() {
        if (mView != null) {
            mView.setSendButtonEnabled(!TextUtils.isEmpty(inputText));
        }
    }

    @Override
    public void onSendButtonPress() {

        if (TextUtils.isEmpty(inputText)) {
            applySendButton();
        } else {
            final String input = inputText;
            inputText = null;
            applyTextInput();
            applySendButton();

            sendMessage(input, true);
        }

    }

    @Override
    public void onQuickReplyPress(QuickReplyModel quickReply) {
        sendMessage(quickReply.getTextContent(), true);
    }

    @Override
    public void onCardImagePress(CardModel cardModel) {
        if (mView != null) {
            if (!Strings.isNullOrEmpty(cardModel.getUrl())) {
                mView.openUrl(cardModel.getUrl());
            }
        }
    }

    @Override
    public void onCardButtonPress(CardButtonModel cardButtonModel) {
        cardButtonModel.visit(new CardButton.Visitor() {
            @Override
            public void onVisitUrl(String target) {
                if (mView != null) {
                    if (!Strings.isNullOrEmpty(target)) {
                        mView.openUrl(target);
                    }
                }
            }

            @Override
            public void onVisitMessage(String target) {
                sendMessage(target, true);
            }
        });
    }

    @Override
    public void onClearButtonPress() {
        mMessageList.clear();
        inputText = null;
        attachView(mView);
        sendMessage(RESTART_COMMAND, false);
    }

    @Override
    public void onRestartButtonPress() {
        sendMessage(RESTART_COMMAND, true);
    }

    private void sendMessage(String input, final boolean showOnUi) {

        // new message came in, so flush out any old quick replies!
        clearQuickReplies();

        final UserInputConversationBubble sentMessage = new UserInputConversationBubble().setText(input);
        sentMessage.setLocalId(generateId()).setTimeStamp(DateTime.now()).setSecondaryOrder(0);

        if (showOnUi) {
            addToMessages(sentMessage);
        }

        messageBot.execute(new MessageBot.RequestValue.Builder()
                        .setApiKey(botInfo.getApiKey())
                        .setMessage(input.trim())
                        .setBotId(botInfo.getId())
                        .setUserInfo(userInfo)
                        .build(),
                new Subscriber<BotResponse>() {

                    int totalCount = 0;

                    @Override
                    public void onCompleted() {

                        if (mQuickReplies != null) {
                            addToMessages(mQuickReplies.setSecondaryOrder(totalCount));
                        }
                        conversationBubbleVisitor.reset();
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof EndOfConversationException) {
                            addToMessages(new EndOfConversationBubble());

                        } else if (e instanceof RetryException) {
                            if (mView != null) {
                                mView.showErrorMessage();
                            }

                            if (showOnUi) {
                                removeFromMessages(sentMessage);
                                inputText = sentMessage.getText();
                                if (mView != null) {
                                    mView.setInputText(inputText);
                                    mView.removeMessage(sentMessage);
                                }
                            }
                        }
                    }

                    @Override
                    public void onNext(BotResponse botResponse) {

                        BotResponseModel botResponseModel = botResponseModelMapper.map(botResponse, botInfo.getColor());

                        for (MessageModel message : botResponseModel.getMessageModelList()) {
                            addToMessages(message
                                    .setMessageBackgroundColor(botInfo.getColor())
                                    .setSecondaryOrder(totalCount++)
                            );
                            message.accept(conversationBubbleVisitor);
                        }

                        if (botResponseModel.getCardModelsList().getList().size() > 0) {
                            addToMessages(
                                    botResponseModel.getCardModelsList()
                                            .setSecondaryOrder(totalCount++)
                            );
                        }

                        if (botResponseModel.getQuickReplyModelsList().getList().size() > 0) {
                            mQuickReplies = botResponseModel.getQuickReplyModelsList();
                        }

                    }
                });
    }

    private void clearQuickReplies() {
        if (mQuickReplies != null) {
            if (mView != null) {
                mView.removeMessage(mQuickReplies);
            }
            mMessageList.remove(mQuickReplies);
            mQuickReplies = null;
        }
    }


    private void applyTextInput() {
        if (mView != null) {
            mView.setInputText(inputText);
        }
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private void addToMessages(ConversationBubble conversationBubble) {
        mMessageList.add(conversationBubble);
        if (mView != null) {
            mView.appendMessage(conversationBubble);
        }
    }

    private void removeFromMessages(ConversationBubble conversationBubble) {
        mMessageList.remove(conversationBubble);
        if (mView != null) {
            mView.removeMessage(conversationBubble);
        }
    }

    @Override
    public void attachView(@NonNull ConversationContract.View view) {
        this.mView = view;
        mView.setMessages(mMessageList);
        mView.setInputText(inputText);

        mView.setColorScheme(botInfo.getColor());
        mView.setTitle(botInfo.getName());

        applySendButton();
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void onDestroyed() {
        mMessageList = null;
    }
}
