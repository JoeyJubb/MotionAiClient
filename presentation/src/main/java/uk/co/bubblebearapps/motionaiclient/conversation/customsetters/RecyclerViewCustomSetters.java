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

package uk.co.bubblebearapps.motionaiclient.conversation.customsetters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.QuickReplyModel;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.CardsAdapterCallback;
import uk.co.bubblebearapps.motionaiclient.conversation.nestedadapter.QuickReplyAdapterCallback;
import uk.co.bubblebearapps.motionaiclient.view.DataBindingAdapter;

/**
 * Created by joefr_000 on 14/10/2016.
 */
public class RecyclerViewCustomSetters {

    private RecyclerViewCustomSetters() {
    }

    @BindingAdapter(value = {"cards", "adapterCallback"})
    public static void setupCardsRecycler(RecyclerView recyclerView, List<CardModel> itemList, CardsAdapterCallback adapterCallback) {
        DataBindingAdapter<CardModel> adapter = new DataBindingAdapter<>(adapterCallback);
        adapter.setItemList(itemList);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter(value = {"quickReplies", "adapterCallback"})
    public static void setupQuikcRepliesRecycler(RecyclerView recyclerView, List<QuickReplyModel> itemList, QuickReplyAdapterCallback adapterCallback) {
        DataBindingAdapter<QuickReplyModel> adapter = new DataBindingAdapter<>(adapterCallback);
        adapter.setItemList(itemList);
        recyclerView.setAdapter(adapter);
    }

}
