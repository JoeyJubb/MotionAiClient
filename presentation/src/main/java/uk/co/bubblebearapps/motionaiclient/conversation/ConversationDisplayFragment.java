package uk.co.bubblebearapps.motionaiclient.conversation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.AndroidApplication;
import uk.co.bubblebearapps.motionaiclient.R;
import uk.co.bubblebearapps.motionaiclient.base.BaseMvpFragment;
import uk.co.bubblebearapps.motionaiclient.base.PresenterFactory;
import uk.co.bubblebearapps.motionaiclient.conversation.model.BotResponseMessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.Cards;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplies;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.UserInputMessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.CardsAdapterCallback;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.QuickReplyAdapterCallback;
import uk.co.bubblebearapps.motionaiclient.databinding.ConversationFragBinding;
import uk.co.bubblebearapps.motionaiclient.internal.di.components.DaggerConversationThreadComponent;
import uk.co.bubblebearapps.motionaiclient.view.SortedDataBindingAdapter;
import uk.co.bubblebearapps.motionaiclient.view.VerticalSpaceItemDecoration;


/**
 * A placeholder fragment containing a simple view.
 */
public class ConversationDisplayFragment extends BaseMvpFragment<ConversationContract.Presenter, ConversationContract.View> implements ConversationContract.ListItemActionHandler, ConversationContract.View, SortedDataBindingAdapter.AdapterCallback<MessageModel> {

    @Inject
    PresenterFactory<ConversationContract.Presenter> presenterFactory;
    private ConversationFragBinding mViewBinding;
    private SortedDataBindingAdapter<MessageModel> mAdapter;
    private ConversationContract.Presenter mPresenter;


    public ConversationDisplayFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_conversation, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_clear:
                mPresenter.onClearButtonPress();
                return true;
            case R.id.action_restart:
                mPresenter.onRestartButtonPress();
                return true;
        }
        return false;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerConversationThreadComponent.builder()
                .applicationComponent(((AndroidApplication) getActivity().getApplication()).getApplicationComponent())
                .build()
                .inject(this);

        mAdapter = new SortedDataBindingAdapter<>(MessageModel.class, this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewBinding = ConversationFragBinding.inflate(inflater, container, false);
        mViewBinding.recyclerView.setAdapter(mAdapter);
        mViewBinding.recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.default_gap)));

        return mViewBinding.getRoot();
    }

    @NonNull
    @Override
    protected PresenterFactory<ConversationContract.Presenter> getPresenterFactory() {
        return presenterFactory;
    }

    @Override
    protected void onPresenterPrepared(@NonNull ConversationContract.Presenter presenter) {
        this.mPresenter = presenter;
        mViewBinding.setActionHandler(mPresenter);
    }

    @NonNull
    @Override
    protected ConversationContract.View getPresenterView() {
        return this;
    }

    @Override
    public void clearInput() {
        mViewBinding.textInput.setText(null);
    }

    @Override
    public void setMessages(List<MessageModel> messageModelList) {
        mAdapter.getItemList().clear();
        if (messageModelList != null) {
            mAdapter.getItemList().addAll(messageModelList);
        }
        scrollToBottom();
    }

    @Override
    public void appendMessage(MessageModel message) {
        mAdapter.getItemList().add(message);
        scrollToBottom();
    }

    @Override
    public void setInputText(String inputText) {
        mViewBinding.textInput.setText(inputText);
    }

    @Override
    public void setSendButtonEnabled(boolean enabled) {
        mViewBinding.btnSend.setEnabled(enabled);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getContext(), R.string.err_send_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void removeMessage(MessageModel message) {
        mAdapter.getItemList().remove(message);
    }

    @Override
    public int getLayoutRes(MessageModel item) {

        if (item instanceof BotResponseMessageModel) {

            switch (((BotResponseMessageModel) item).getType()) {
                case IMAGE:
                    return R.layout.conversation_listitem_bot_response_image;
                case TEXT:
                    return R.layout.conversation_listitem_bot_response_text;
                case VIDEO:
                    return R.layout.conversation_listitem_bot_response_video;
                case YOUTUBE:
                    return R.layout.conversation_listitem_bot_response_youtube;
                default:
                    return R.layout.conversation_listitem_unknown;
            }

        } else if (item instanceof UserInputMessageModel) {
            return R.layout.conversation_listitem_user_input;
        } else if (item instanceof Cards) {
            return R.layout.conversation_listitem_cards;
        } else if (item instanceof QuickReplies) {
            return R.layout.conversation_listitem_quickreplies;
        } else {
            return R.layout.conversation_listitem_unknown;
        }
    }

    @Override
    public Object getActionHandler(@LayoutRes int viewType) {
        switch (viewType) {
            case R.layout.conversation_listitem_cards:
                return new CardsAdapterCallback(this);
            case R.layout.conversation_listitem_quickreplies:
                return new QuickReplyAdapterCallback(this);
            default:
                return this;

        }
    }

    @Override
    public int compare(MessageModel o1, MessageModel o2) {
        return o1.getTimeStamp().compareTo(o2.getTimeStamp());
    }

    @Override
    public boolean areContentsTheSame(MessageModel oldItem, MessageModel newItem) {
        return oldItem.getContentsHash().equals(newItem.getContentsHash());
    }

    @Override
    public boolean areItemsTheSame(MessageModel item1, MessageModel item2) {
        return item1.getLocalId().equals(item2.getLocalId());
    }

    @Override
    public void onQuickReplyPress(QuickReplyModel quickReply) {

        mPresenter.onQuickReplyPress(quickReply);
    }

    public void scrollToBottom() {
        mViewBinding.recyclerView.scrollToPosition(mViewBinding.recyclerView.getAdapter().getItemCount() - 1);
    }

    @Override
    public void onCardImagePress(CardModel cardModel) {
        mPresenter.onCardImagePress(cardModel);
    }

    @Override
    public void onCardButtonPress(CardButtonModel cardButtonModel) {
        mPresenter.onCardButtonPress(cardButtonModel);


    }

    @Override
    public void onUrlClick(String url) {
        openUrl(url);
    }
}
