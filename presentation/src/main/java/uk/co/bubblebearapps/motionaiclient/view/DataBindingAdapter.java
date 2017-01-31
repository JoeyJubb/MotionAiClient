package uk.co.bubblebearapps.motionaiclient.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


import uk.co.bubblebearapps.motionaiclient.BR;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by joefr_000 on 22/09/2016.
 */
public class DataBindingAdapter<T> extends RecyclerView.Adapter<DataBindingAdapter.DataBindingViewHolder> {

    private static final String TAG = "DataBindingAdapter";
    private final AdapterCallback<T> mAdapterCallback;

    private List<T> itemList;

    public DataBindingAdapter(@NonNull AdapterCallback<T> adapterCallback) {
        this.mAdapterCallback = checkNotNull(adapterCallback);
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                viewType,
                parent,
                false);

        viewDataBinding.setVariable(BR.actionHandler, mAdapterCallback.getActionHandler(viewType));

        return new DataBindingViewHolder(
                viewDataBinding
        );

    }

    @Override
    public int getItemViewType(int position) {
        return mAdapterCallback.getLayoutRes(getItem(position));
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {

        holder.viewDataBinding.setVariable(BR.item, getItem(position));

    }

    public T getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void appendItem(T message) {
        if(itemList == null){
            itemList = new ArrayList<>();
        }

        int oldCount = itemList.size();
        itemList.add(message);
        notifyItemInserted(oldCount);
    }

    /**
     * Created by joefr_000 on 22/09/2016.
     */
    public interface AdapterCallback<T> {

        @LayoutRes
        int getLayoutRes(T item);

        Object getActionHandler(@LayoutRes int viewType);
    }

    /**
     * Created by joefr_000 on 22/09/2016.
     */
    protected static class DataBindingViewHolder<T> extends RecyclerView.ViewHolder {
        protected final ViewDataBinding viewDataBinding;

        public DataBindingViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());

            this.viewDataBinding = viewDataBinding;
        }

        public void setItem(T item) {
            viewDataBinding.setVariable(BR.item, item);
        }

        public ViewDataBinding getBinding() {
            return viewDataBinding;
        }
    }
}
