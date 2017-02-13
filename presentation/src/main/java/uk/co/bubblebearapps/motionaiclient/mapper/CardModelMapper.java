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

package uk.co.bubblebearapps.motionaiclient.mapper;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.Card;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModel;
import uk.co.bubblebearapps.motionaiclient.conversation.model.CardModelsList;
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

    public CardModel map(Card card, int botColor) {
        return new CardModel()
                .setId(card.getId())
                .setImageUrl(card.getImageUrl())
                .setSubTitle(card.getSubTitle())
                .setTitle(card.getTitle())
                .setUrl(card.getUrl())
                .setCardButtons(cardButtonModelMapper.map(card.getCardButtons(), botColor));
    }

    public CardModelsList map(List<Card> cards, DateTime dateTime, int botColor) {

        if (cards == null) {
            return null;
        } else {
            List<CardModel> result = new ArrayList<>();
            for (Card card : cards) {
                result.add(map(card, botColor));
            }

            return new CardModelsList(UUID.randomUUID().toString(), dateTime, result);
        }
    }


}
