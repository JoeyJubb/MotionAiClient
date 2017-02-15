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

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.Card;
import uk.co.bubblebearapps.motionaiclient.CardButton;
import uk.co.bubblebearapps.motionaiclient.CardList;
import uk.co.bubblebearapps.motionaiclient.entity.CardEntity;
import uk.co.bubblebearapps.motionaiclient.entity.CardEntityButton;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;

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

    public CardList map(ResponseEntity responseEntity) {
        if (responseEntity == null || responseEntity.getCards() == null || responseEntity.getCards().length == 0) {
            return null;
        }
        List<Card> cardsList = new ArrayList<>();
        for (CardEntity cardEntity : responseEntity.getCards()) {
            cardsList.add(map(cardEntity));
        }
        return new CardList(responseEntity.getSession(), DateTime.now(), cardsList);

    }
}
