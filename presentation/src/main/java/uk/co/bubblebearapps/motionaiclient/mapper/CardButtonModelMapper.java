package uk.co.bubblebearapps.motionaiclient.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.Card;
import uk.co.bubblebearapps.motionaiclient.CardButton;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardButtonModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@PerActivity
public class CardButtonModelMapper {

    @Inject
    public CardButtonModelMapper(){

    }

    public CardButtonModel map(CardButton card) {
        return new CardButtonModel()
                .setId(card.getId())
                .setLabel(card.getLabel())
                .setType(card.getType())
                .setTarget(card.getTarget());

    }

    public List<CardButtonModel> map(List<CardButton> cardButtons) {

        List<CardButtonModel> result = new ArrayList<>();
        for(CardButton cardButton : cardButtons){
            result.add(map(cardButton));
        }

        return result;

    }
}
