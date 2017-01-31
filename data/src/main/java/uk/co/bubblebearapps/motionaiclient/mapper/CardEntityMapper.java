package uk.co.bubblebearapps.motionaiclient.mapper;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.Card;
import uk.co.bubblebearapps.motionaiclient.CardButton;
import uk.co.bubblebearapps.motionaiclient.entity.CardEntity;
import uk.co.bubblebearapps.motionaiclient.entity.CardEntityButton;

/**
 * Created by joefr_000 on 23/01/2017.
 */

@Singleton
public class CardEntityMapper {


    @Inject
    public CardEntityMapper() {

    }

    public Card map(CardEntity cardEntity) {
        Card card = new Card()
                .setId(cardEntity.getId())
                .setTitle(cardEntity.getTitle())
                .setSubTitle(cardEntity.getSubTitle())
                .setImageUrl(cardEntity.getImageUrl())
                .setUrl(cardEntity.getUrl());

        List<CardButton> cardButtonList = new ArrayList<>();
        if (cardEntity.getButtons() != null) {
            for (CardEntityButton cardEntityButton : cardEntity.getButtons()) {
                cardButtonList.add(new CardButton()
                        .setId(cardEntityButton.getId())
                        .setLabel(cardEntityButton.getLabel())
                        .setTarget(cardEntityButton.getTarget())
                        .setType(cardEntityButton.getType().equals("url") ? CardButton.ButtonType.URL : CardButton.ButtonType.MESSAGE)
                );
            }
        }

        card.setCardButtons(cardButtonList);
        return card;
    }

    public List<Card> map(CardEntity[] cards) {
        if(cards == null){
            return Collections.emptyList();
        }
        List<Card> cardsList = new ArrayList<>();
        for(CardEntity cardEntity : cards){
            cardsList.add(map(cardEntity));
        }
        return cardsList;

    }
}
