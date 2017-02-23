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

package uk.co.bubblebearapps.motionaiclient;

import com.google.common.collect.ImmutableList;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class CardList extends BotResponse {

    private final ImmutableList<Card> cardList;

    private CardList(String sessionId, DateTime timeStamp, ImmutableList<Card> cardList) {
        super(sessionId, timeStamp);
        this.cardList = cardList;
    }

    @Override
    public void accept(Observer observer) {
        observer.visit(this);
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public static class Builder {
        private final ImmutableList.Builder<Card> cardBuilder = new ImmutableList.Builder<>();
        private String sessionId;
        private DateTime timeStamp;

        public Builder setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder setTimeStamp(DateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder addCard(Card card) {
            cardBuilder.add(card);
            return this;
        }

        public CardList build() {
            return new CardList(sessionId, timeStamp, cardBuilder.build());
        }
    }
}