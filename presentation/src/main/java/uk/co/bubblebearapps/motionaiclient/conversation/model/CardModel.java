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

package uk.co.bubblebearapps.motionaiclient.conversation.model;

import java.util.List;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class CardModel {

    private String id;

    private String subTitle;

    private String title;
    private String url;
    private String imageUrl;
    private List<CardButtonModel> cardButtons;

    public String getId() {
        return id;
    }

    public CardModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public CardModel setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CardModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CardModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CardModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<CardButtonModel> getCardButtons() {
        return cardButtons;
    }

    public CardModel setCardButtons(List<CardButtonModel> cardButtons) {
        this.cardButtons = cardButtons;
        return this;
    }
}
