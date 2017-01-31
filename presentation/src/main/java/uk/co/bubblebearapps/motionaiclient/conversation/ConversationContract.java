package uk.co.bubblebearapps.motionaiclient.conversation;

import java.util.List;

import uk.co.bubblebearapps.motionaiclient.base.BasePresenter;
import uk.co.bubblebearapps.motionaiclient.base.BaseView;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplies;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.QuickReplyAdapterCallback;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public interface ConversationContract {

    interface View extends BaseView {

        void clearInput();

        void setMessages(List<MessageModel> messageModelList);

        void appendMessage(MessageModel message);

        void setInputText(String inputText);

        void setSendButtonEnabled(boolean show);

        void showErrorMessage();

        void openUrl(String url);

        void removeMessage(MessageModel message);
    }

    interface Presenter extends BasePresenter<View> {

        void onInputTextUpdated(String inputText);

        void onSendButtonPress();

        void onQuickReplyPress(QuickReplyModel quickReply);

        void onCardImagePress(CardModel cardModel);

        void onCardButtonPress(CardButtonModel cardButtonModel);

        void onClearButtonPress();

        void onRestartButtonPress();
    }

    interface ListItemActionHandler {

        void onQuickReplyPress(QuickReplyModel quickReply);

        void onCardImagePress(CardModel cardModel);

        void onCardButtonPress(CardButtonModel cardButtonModel);

        void onUrlClick(String url);

    }

}
