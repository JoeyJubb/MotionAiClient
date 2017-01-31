package uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter;

import android.support.annotation.LayoutRes;

import uk.co.bubblebearapps.motionaiclient.R;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.view.DataBindingAdapter;

/**
 * Created by joefr_000 on 24/01/2017.
 */

public class CardsAdapterCallback implements DataBindingAdapter.AdapterCallback<CardModel> {
    private Object actionHandler;

    public CardsAdapterCallback(Object actionHandler) {
        this.actionHandler = actionHandler;
    }

    @Override
    public int getLayoutRes(CardModel item) {
        return R.layout.conversation_listitem_card;
    }

    @Override
    public Object getActionHandler(@LayoutRes int viewType) {
        return actionHandler;
    }
}
