package uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter;

import android.support.annotation.LayoutRes;

import uk.co.bubblebearapps.motionaiclient.R;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.view.DataBindingAdapter;

/**
 * Created by joefr_000 on 24/01/2017.
 */

public class QuickReplyAdapterCallback implements DataBindingAdapter.AdapterCallback<QuickReplyModel> {
    private Object actionhandler;

    public QuickReplyAdapterCallback(Object actionhandler) {
        this.actionhandler = actionhandler;
    }

    @Override
    public int getLayoutRes(QuickReplyModel item) {
        return R.layout.conversation_listitem_quick_reply;
    }

    @Override
    public Object getActionHandler(@LayoutRes int viewType) {
        return actionhandler;
    }
}
