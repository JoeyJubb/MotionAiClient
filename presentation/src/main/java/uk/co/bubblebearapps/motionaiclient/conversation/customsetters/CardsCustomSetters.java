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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.common.base.Strings;

import java.util.List;
import java.util.Stack;

import uk.co.bubblebearapps.motionaiclient.R;
import uk.co.bubblebearapps.motionaiclient.conversation.ConversationContract;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.view.customsetters.ViewCustomSetters;

/**
 * Created by joefr_000 on 26/01/2017.
 */

public class CardsCustomSetters {

    private static Stack<Button> spareButtons = new Stack<>();

    private CardsCustomSetters() {
    }

    @BindingAdapter(value = {"cardButtons", "cardActionHandler"})
    public static void setCardButtons(ViewGroup viewGroup, List<CardButtonModel> cardButtonModels, final ConversationContract.Presenter listItemActionHandler) {

        recycleButtons(viewGroup);

        for (CardButtonModel cardButtonModel : cardButtonModels) {


            if (Strings.isNullOrEmpty(cardButtonModel.getLabel())) {
                continue;
            }

            Button button = spareButtons.size() > 0 ? spareButtons.pop() : createNewButton(viewGroup);
            bindCardButtonModel(button, listItemActionHandler, cardButtonModel);
            viewGroup.addView(button);
        }


    }

    private static void bindCardButtonModel(Button button, final ConversationContract.Presenter listItemActionHandler, final CardButtonModel cardButtonModel) {

        button.setText(cardButtonModel.getLabel());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemActionHandler.onCardButtonPress(cardButtonModel);
            }
        });

        ViewCustomSetters.setBackgroundTint(button, cardButtonModel.getBotColor());
    }

    private static Button createNewButton(ViewGroup viewGroup) {

        return (Button) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.conversation_listitem_card_button, viewGroup, false);


    }

    private static void recycleButtons(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            if (viewGroup.getChildAt(i) instanceof Button) {
                spareButtons.add((Button) viewGroup.getChildAt(i));
            }
            viewGroup.removeViewAt(i);
        }
    }

}
