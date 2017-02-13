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
import android.view.View;

import uk.co.bubblebearapps.motionaiclient.R;
import uk.co.bubblebearapps.motionaiclient.conversation.model.MessageModel;

/**
 * Created by joefr_000 on 09/02/2017.
 */

public class ConversationBubbleCustomSetters {

    private ConversationBubbleCustomSetters() {
    }


    @BindingAdapter("juxtaposition")
    public static void setBackgroundTint(final View view, MessageModel.Juxtaposition juxtaposition) {


        switch (juxtaposition) {
            case TOP:
                view.setBackgroundResource(R.drawable.bg_chat_top);
                break;
            case MIDDLE:
                view.setBackgroundResource(R.drawable.bg_chat_middle);
                break;
            case BOTTOM:
                view.setBackgroundResource(R.drawable.bg_chat_bottom);
                break;
            case ONLY:
                view.setBackgroundResource(R.drawable.bg_chat_only);
                break;
        }

    }

}
