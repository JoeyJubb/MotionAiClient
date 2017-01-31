package uk.co.bubblebearapps.motionaiclient.conversation.customsetters;

import android.databinding.BindingAdapter;
import android.util.Log;
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

/**
 * Created by joefr_000 on 26/01/2017.
 */

public class CardsCustomSetters {

    private CardsCustomSetters() {
    }

    private static Stack<Button> spareButtons = new Stack<>();

    @BindingAdapter(value = {"cardButtons", "cardActionHandler"})
    public static void setCardButtons(ViewGroup viewGroup, List<CardButtonModel> cardButtonModels, final ConversationContract.ListItemActionHandler listItemActionHandler) {

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

    private static void bindCardButtonModel(Button button, final ConversationContract.ListItemActionHandler listItemActionHandler, final CardButtonModel cardButtonModel) {

        button.setText(cardButtonModel.getLabel());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemActionHandler.onCardButtonPress(cardButtonModel);
            }
        });
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
