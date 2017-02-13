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
 * Created by joefr_000 on 02/02/2017.
 */
public class BotResponseModel {

    private List<MessageModel> messageModelList;
    private CardModelsList cardModelsList;
    private QuickReplyModelsList quickReplyModelsList;

    public List<MessageModel> getMessageModelList() {
        return messageModelList;
    }

    public BotResponseModel setMessageModelList(List<MessageModel> messageModelList) {
        this.messageModelList = messageModelList;
        return this;
    }

    public CardModelsList getCardModelsList() {
        return cardModelsList;
    }

    public BotResponseModel setCardModelsList(CardModelsList cardModelsList) {
        this.cardModelsList = cardModelsList;
        return this;
    }

    public QuickReplyModelsList getQuickReplyModelsList() {
        return quickReplyModelsList;
    }

    public BotResponseModel setQuickReplyModelsList(QuickReplyModelsList quickReplyModelsList) {
        this.quickReplyModelsList = quickReplyModelsList;
        return this;
    }
}
