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

/**
 * Created by joefr_000 on 23/01/2017.
 */
public abstract class ConversationBubble {

    public String contentsHash;

    public String localId;

    public DateTime timeStamp;

    public int secondaryOrder;

    public String getContentsHash() {
        return contentsHash;
    }

    public ConversationBubble setContentsHash(String contentsHash) {
        this.contentsHash = contentsHash;
        return this;
    }

    public String getLocalId() {
        return localId;
    }

    public ConversationBubble setLocalId(String localId) {
        this.localId = localId;
        return this;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public ConversationBubble setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public int getSecondaryOrder() {
        return secondaryOrder;
    }

    public ConversationBubble setSecondaryOrder(int secondaryOrder) {
        this.secondaryOrder = secondaryOrder;
        return this;
    }

    public abstract void accept(ConversationBubbleVisitor bubbleVisitor);
}
