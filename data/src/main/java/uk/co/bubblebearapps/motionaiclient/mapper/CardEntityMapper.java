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

    private Card map(CardEntity cardEntity) {

        Card.Builder builder = new Card.Builder()
                .setId(cardEntity.getId())
                .setSubTitle(cardEntity.getSubTitle())
                .setTitle(cardEntity.getTitle())
                .setUrl(cardEntity.getUrl())
                .setImageUrl(cardEntity.getImageUrl());

        if (cardEntity.getButtons() != null) {
            for (CardEntityButton cardEntityButton : cardEntity.getButtons()) {
                builder.addCardButton(new CardButton.Builder()
                        .setId(cardEntityButton.getId())
                        .setTarget(cardEntityButton.getTarget())
                        .setType(cardEntityButton.getType().equals("url") ? CardButton.ButtonType.URL : CardButton.ButtonType.MESSAGE)
                        .setLabel(cardEntityButton.getLabel())
                        .build()
                );
            }
        }

        return builder.build();

    }

    CardList map(ResponseEntity responseEntity) {
        if (responseEntity == null || responseEntity.getCards() == null || responseEntity.getCards().length == 0) {
            return null;
        }

        CardList.Builder builder = new CardList.Builder()
                .setSessionId(responseEntity.getSession())
                .setTimeStamp(DateTime.now());

        for (CardEntity cardEntity : responseEntity.getCards()) {
            builder.addCard(map(cardEntity));
        }

        return builder.build();

    }
}
