package uk.co.bubblebearapps.motionaiclient.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.Card;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;

/**
 * Created by joefr_000 on 23/01/2017.
 */
@PerActivity
public class CardModelMapper {

    private final CardButtonModelMapper cardButtonModelMapper;

    @Inject
    public CardModelMapper(CardButtonModelMapper cardButtonModelMapper) {
        this.cardButtonModelMapper = cardButtonModelMapper;
    }

    public CardModel map(Card card) {
        return new CardModel()
                .setId(card.getId())
                .setImageUrl(card.getImageUrl())
                .setSubTitle(card.getSubTitle())
                .setTitle(card.getTitle())
                .setUrl(card.getUrl())
                .setCardButtons(cardButtonModelMapper.map(card.getCardButtons()));
    }

    public List<CardModel> map(List<Card> cards) {

        if (cards == null) {
            return null;
        } else {
            List<CardModel> result = new ArrayList<>();
            for (Card card : cards) {
                result.add(map(card));
            }
            return result;
        }
    }
}
