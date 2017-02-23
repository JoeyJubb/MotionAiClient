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

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class Card {

    private final String id;
    private final String subTitle;
    private final String title;
    private final String url;
    private final String imageUrl;
    private final ImmutableList<CardButton> cardButtons;

    private Card(String id, String subTitle, String title, String url, String imageUrl, ImmutableList<CardButton> cardButtons) {
        this.id = id;
        this.subTitle = subTitle;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.cardButtons = cardButtons;
    }

    public String getId() {
        return id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ImmutableList<CardButton> getCardButtons() {
        return cardButtons;
    }

    public static class Builder {
        private final ImmutableList.Builder<CardButton> cardButtonBuilder = new ImmutableList.Builder<>();
        private String id;
        private String subTitle;
        private String title;
        private String url;
        private String imageUrl;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder addCardButton(CardButton cardButton) {
            this.cardButtonBuilder.add(cardButton);
            return this;
        }

        public Card build() {
            return new Card(id, subTitle, title, url, imageUrl, cardButtonBuilder.build());
        }
    }
}
