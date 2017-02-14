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
