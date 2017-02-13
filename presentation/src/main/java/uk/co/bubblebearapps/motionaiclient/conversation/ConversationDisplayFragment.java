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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
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

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.common.primitives.Ints;

import java.util.List;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.AndroidApplication;
import uk.co.bubblebearapps.motionaiclient.R;
import uk.co.bubblebearapps.motionaiclient.base.BaseMvpFragment;
import uk.co.bubblebearapps.motionaiclient.base.PresenterFactory;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModelsList;
import uk.co.bubblebearapps.motionaiclient.conversation.model.ConversationBubble;
import uk.co.bubblebearapps.motionaiclient.conversation.model.EndOfConversationBubble;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModelsList;
import uk.co.bubblebearapps.motionaiclient.conversation.model.UserInputConversationBubble;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.CardsAdapterCallback;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.QuickReplyAdapterCallback;
import uk.co.bubblebearapps.motionaiclient.databinding.ConversationFragBinding;
import uk.co.bubblebearapps.motionaiclient.internal.di.components.DaggerConversationThreadComponent;
import uk.co.bubblebearapps.motionaiclient.internal.di.modules.ConversationThreadModule;
import uk.co.bubblebearapps.motionaiclient.model.BotInfoModel;
import uk.co.bubblebearapps.motionaiclient.model.UserInfoModel;
import uk.co.bubblebearapps.motionaiclient.view.SortedDataBindingAdapter;
import uk.co.bubblebearapps.motionaiclient.view.VerticalSpaceItemDecoration;


/**
 * A placeholder fragment containing a simple view.
 */
public class ConversationDisplayFragment extends BaseMvpFragment<ConversationContract.Presenter, ConversationContract.View> implements ConversationContract.ListItemActionHandler, ConversationContract.View, SortedDataBindingAdapter.AdapterCallback<ConversationBubble> {

    private static final String TAG = "ConvoDisplayFragment";
    private static final String ARG_YOUTUBE_KEY = "uk.co.bubblebearapps.motionaiclient.conversation.ARG_YOUTUBE_KEY";
    private static final int REQ_START_STANDALONE_PLAYER = 23;
    private static final int REQ_RESOLVE_SERVICE_MISSING = 24;
    private static final String ARG_USER_INFO = "uk.co.bubblebearapps.motionaiclient.conversation.ARG_USER_INFO";
    private static final String ARG_BOT_INFO = "uk.co.bubblebearapps.motionaiclient.conversation.ARG_BOT_INFO";
    @Inject
    PresenterFactory<ConversationContract.Presenter> presenterFactory;
    private String youtubeApiKey;
    private Callback callback;
    private ConversationFragBinding mViewBinding;
    private SortedDataBindingAdapter<ConversationBubble> mAdapter;
    private ConversationContract.Presenter mPresenter;
    public ConversationDisplayFragment() {
        setHasOptionsMenu(true);
    }

    public static ConversationDisplayFragment newInstance(String youtubeApiKey, UserInfoModel userInfo, BotInfoModel botInfo) {

        ConversationDisplayFragment conversationDisplayFragment = new ConversationDisplayFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER_INFO, userInfo);
        args.putParcelable(ARG_BOT_INFO, botInfo);
        args.putString(ARG_YOUTUBE_KEY, youtubeApiKey);

        conversationDisplayFragment.setArguments(args);
        return conversationDisplayFragment;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement %s", context.getClass().getName(), Callback.class.getName()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_conversation, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == R.id.action_clear) {
            mPresenter.onClearButtonPress();
            return true;
        } else if (i == R.id.action_restart) {
            mPresenter.onRestartButtonPress();
            return true;
        }
        return false;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        BotInfoModel botInfoModel = getArguments().getParcelable(ARG_BOT_INFO);
        UserInfoModel userInfoModel = getArguments().getParcelable(ARG_USER_INFO);

        youtubeApiKey = getArguments().getString(ARG_YOUTUBE_KEY);


        DaggerConversationThreadComponent.builder()
                .applicationComponent(((AndroidApplication) getActivity().getApplication()).getApplicationComponent())
                .conversationThreadModule(new ConversationThreadModule(botInfoModel, userInfoModel))
                .build()
                .inject(this);

        mAdapter = new SortedDataBindingAdapter<>(ConversationBubble.class, this);
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
    public void setMessages(List<ConversationBubble> conversationBubbleList) {
        mAdapter.getItemList().clear();
        if (conversationBubbleList != null) {
            mAdapter.getItemList().addAll(conversationBubbleList);
        }
        scrollToBottom();
    }

    @Override
    public void appendMessage(ConversationBubble message) {
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
    public void removeMessage(ConversationBubble message) {
        mAdapter.getItemList().remove(message);
    }

    @Override
    public void setColorScheme(int colorPrimary) {
        callback.setColorScheme(colorPrimary);
    }

    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

    @Override
    public int getLayoutRes(ConversationBubble item) {

        if (item instanceof MessageModel) {

            switch (((MessageModel) item).getType()) {
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

        } else if (item instanceof UserInputConversationBubble) {
            return R.layout.conversation_listitem_user_input;
        } else if (item instanceof CardModelsList) {
            return R.layout.conversation_listitem_cards;
        } else if (item instanceof QuickReplyModelsList) {
            return R.layout.conversation_listitem_quickreplies;
        } else if (item instanceof EndOfConversationBubble) {
            return R.layout.conversation_listitem_endmarker;
        } else {
            return R.layout.conversation_listitem_unknown;
        }
    }

    @Override
    public Object getActionHandler(@LayoutRes int viewType) {
        if (viewType == R.layout.conversation_listitem_cards) {
            return new CardsAdapterCallback(this);
        } else if (viewType == R.layout.conversation_listitem_quickreplies) {
            return new QuickReplyAdapterCallback(this);
        } else if (viewType == R.layout.conversation_listitem_endmarker) {
            return mPresenter;
        } else {
            return this;
        }
    }

    @Override
    public int compare(ConversationBubble o1, ConversationBubble o2) {
        int compare = o1.getTimeStamp().compareTo(o2.getTimeStamp());

        if (compare == 0) {
            return Ints.compare(o1.getSecondaryOrder(), o2.getSecondaryOrder());
        } else {
            return compare;
        }
    }

    @Override
    public boolean areContentsTheSame(ConversationBubble oldItem, ConversationBubble newItem) {
        return oldItem.getContentsHash().equals(newItem.getContentsHash());
    }

    @Override
    public boolean areItemsTheSame(ConversationBubble item1, ConversationBubble item2) {
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


    @Override
    public void onYouTubeThumbnailTapped(String youtubeId) {

        Intent intent = YouTubeStandalonePlayer.createVideoIntent(
                getActivity(),
                youtubeApiKey,
                youtubeId,//video id
                100,     //after this time, video will start automatically
                true,    //autoplay or not
                false);  //lightbox mode or not; show the video in a small box


        if (canResolveIntent(intent)) {
            startActivity(intent);
        } else {
            YouTubeInitializationResult.SERVICE_MISSING
                    .getErrorDialog(getActivity(), REQ_RESOLVE_SERVICE_MISSING).show();
        }

    }

    @Override
    public void loadYouTubeVideo(YouTubeThumbnailView youTubeThumbnailView, final String youTubeId) {

        youTubeThumbnailView.initialize(youtubeApiKey, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(youTubeId);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }


    private boolean canResolveIntent(Intent intent) {
        List<ResolveInfo> resolveInfo = getContext().getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_START_STANDALONE_PLAYER && resultCode != Activity.RESULT_OK) {
            YouTubeInitializationResult errorReason =
                    YouTubeStandalonePlayer.getReturnedInitializationResult(data);
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog(getActivity(), 0).show();
            } else {
                String errorMessage =
                        String.format(getString(R.string.error_player), errorReason.toString());
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
            }
        }
    }

    public interface Callback {
        void setColorScheme(int colorPrimary);
    }
}
