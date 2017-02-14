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

package uk.co.bubblebearapps.motionaiclient.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import uk.co.bubblebearapps.motionaiclient.BR;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 */
public class SortedDataBindingAdapter<T> extends RecyclerView.Adapter<SortedDataBindingAdapter.DataBindingViewHolder> {

    private final AdapterCallback<T> mAdapterCallback;
    private final SortedList<T> itemList;

    public SortedDataBindingAdapter(Class<T> clazz, @NonNull AdapterCallback<T> adapterCallback) {

        this.mAdapterCallback = checkNotNull(adapterCallback);
        itemList = new SortedList<>(clazz, new SortedList.Callback<T>() {

            @Override
            public int compare(T o1, T o2) {
                return mAdapterCallback.compare(o1, o2);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(T oldItem, T newItem) {
                return mAdapterCallback.areContentsTheSame(oldItem, newItem);
            }

            @Override
            public boolean areItemsTheSame(T item1, T item2) {
                return mAdapterCallback.areItemsTheSame(item1, item2);
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    public SortedList<T> getItemList() {
        return itemList;
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


    /**
     * Created by joefr_000 on 22/09/2016.
     */
    public interface AdapterCallback<T> {

        @LayoutRes
        int getLayoutRes(T item);

        Object getActionHandler(@LayoutRes int viewType);

        int compare(T o1, T o2);

        boolean areContentsTheSame(T oldItem, T newItem);

        boolean areItemsTheSame(T item1, T item2);
    }

    /**
     * Created by joefr_000 on 22/09/2016.
     */
    public static class DataBindingViewHolder<T> extends RecyclerView.ViewHolder {
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
