package uk.co.bubblebearapps.motionaiclient.conversation;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.common.base.Strings;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import rx.Subscriber;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.CardButton;
import uk.co.bubblebearapps.motionaiclient.User;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.Cards;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplies;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.UserInputMessageModel;
import uk.co.bubblebearapps.motionaiclient.interactor.MessageBot;
import uk.co.bubblebearapps.motionaiclient.mapper.MessageModelMapper;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public class ConversationPresenter implements ConversationContract.Presenter {

    public static final String RESTART_COMMAND = "Restart";
    private List<MessageModel> mMessageList;

    private ConversationContract.View mView;

    private final MessageBot messageBot;
    private final MessageModelMapper messageModelMapper;
    private final String botId;
    private final User user;

    private String inputText;
    private QuickReplies currentQuickReplies;

    public ConversationPresenter(MessageBot messageBot, MessageModelMapper messageModelMapper, String botId, User user) {
        this.messageBot = messageBot;
        this.messageModelMapper = messageModelMapper;
        this.botId = botId;
        this.user = user;

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

    private void sendMessage(String input, boolean showOnUi) {
        if (showOnUi) {
            addToMessages(new UserInputMessageModel(generateId(), DateTime.now(), input));
        }

        //clear out quick replies
        clearCurrentQuickReply();

        messageBot.execute(new MessageBot.RequestValue()
                        .setMessage(input.trim())
                        .setBotId(botId)
                        .setUser(user),
                new Subscriber<BotResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (mView != null) {
                            mView.showErrorMessage();
                        }
                    }

                    @Override
                    public void onNext(BotResponse botResponse) {
                        clearCurrentQuickReply();

                        MessageModelMapper.BotResponseBundle responseBundle = messageModelMapper.map(botResponse, generateId());
                        // add the response
                        addToMessages(responseBundle.getBotResponse().setSessionId(generateId()));

                        if (responseBundle.getCards() != null && responseBundle.getCards().size() > 0) {
                            String id = generateId();
                            addToMessages(new Cards(
                                    id,
                                    botResponse.getTimeStamp().plusMillis(1),
                                    responseBundle.getCards()
                            ));
                        }

                        if (responseBundle.getQuickReplies() != null && responseBundle.getQuickReplies().size() > 0) {
                            String id = generateId();
                            currentQuickReplies = new QuickReplies(
                                    id,
                                    botResponse.getTimeStamp().plusMillis(2),
                                    responseBundle.getQuickReplies()
                            );
                            addToMessages(currentQuickReplies);
                        }

                    }
                });
    }

    private void clearCurrentQuickReply() {
        if(currentQuickReplies != null){
            mMessageList.remove(currentQuickReplies);
            if(mView != null){
                mView.removeMessage(currentQuickReplies);
            }
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

    private void addToMessages(MessageModel messageModel) {
        mMessageList.add(messageModel);
        if (mView != null) {
            mView.appendMessage(messageModel);
        }
    }

    @Override
    public void attachView(@NonNull ConversationContract.View view) {
        this.mView = view;
        mView.setMessages(mMessageList);
        mView.setInputText(inputText);
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
