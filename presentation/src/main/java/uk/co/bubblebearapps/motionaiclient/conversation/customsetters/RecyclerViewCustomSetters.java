package uk.co.bubblebearapps.motionaiclient.conversation.customsetters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.common.base.Strings;

import java.util.List;

import uk.co.bubblebearapps.motionaiclient.Card;
import uk.co.bubblebearapps.motionaiclient.QuickReply;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.CardsAdapterCallback;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.QuickReplyAdapterCallback;
import uk.co.bubblebearapps.motionaiclient.view.DataBindingAdapter;

/**
 * Created by joefr_000 on 14/10/2016.
 */
public class RecyclerViewCustomSetters {

    private RecyclerViewCustomSetters(){}


    @BindingAdapter(value={"cards", "adapterCallback"})
    public static void setupCardsRecycler(RecyclerView recyclerView, List<CardModel> itemList, CardsAdapterCallback adapterCallback) {
        DataBindingAdapter<CardModel> adapter = new DataBindingAdapter<>(adapterCallback);
        adapter.setItemList(itemList);
        recyclerView.setAdapter(adapter);

    }
    @BindingAdapter(value={"quickReplies", "adapterCallback"})
    public static void setupQuikcRepliesRecycler(RecyclerView recyclerView, List<QuickReplyModel> itemList, QuickReplyAdapterCallback adapterCallback) {
        DataBindingAdapter<QuickReplyModel> adapter = new DataBindingAdapter<>(adapterCallback);
        adapter.setItemList(itemList);
        recyclerView.setAdapter(adapter);

    }

}
