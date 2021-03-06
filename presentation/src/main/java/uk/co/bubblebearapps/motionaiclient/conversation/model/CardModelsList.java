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

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by joefr_000 on 24/01/2017.
 */

public class CardModelsList extends ConversationBubble {

    private List<CardModel> list;


    public CardModelsList(String localId, DateTime timeStamp, List<CardModel> list) {
        this.localId = localId;
        this.timeStamp = timeStamp;
        this.list = list;
    }

    @Override
    public String getContentsHash() {
        return getLocalId();
    }

    @Override
    public void accept(ConversationBubbleVisitor bubbleVisitor) {
        bubbleVisitor.visit(this);
    }

    public List<CardModel> getList() {
        return list;
    }
}