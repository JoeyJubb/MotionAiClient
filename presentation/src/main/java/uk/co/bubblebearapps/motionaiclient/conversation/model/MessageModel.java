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

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import org.joda.time.DateTime;

import uk.co.bubblebearapps.motionaiclient.Message;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class MessageModel extends ConversationBubble {

    public final ObservableField<Juxtaposition> juxtaposition = new ObservableField<>();
    private String target;
    private Message.Type type;
    private int messageBackgroundColor;

    public String getTarget() {
        return target;
    }

    public MessageModel setTarget(String target) {
        this.target = target;
        return this;
    }

    @NonNull
    @Override
    public String getContentsHash() {
        return target;
    }

    @Override
    public String getLocalId() {
        return localId;
    }

    public MessageModel setLocalId(String localId) {
        this.localId = localId;
        return this;
    }

    @Override
    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public MessageModel setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public Message.Type getType() {
        return type;
    }

    public MessageModel setType(Message.Type type) {
        this.type = type;
        return this;
    }

    public int getMessageBackgroundColor() {
        return messageBackgroundColor;
    }

    public ConversationBubble setMessageBackgroundColor(int messageBackgroundColor) {
        this.messageBackgroundColor = messageBackgroundColor;
        return this;
    }

    @Override
    public void accept(ConversationBubbleVisitor bubbleVisitor) {
        bubbleVisitor.visit(this);
    }

    public MessageModel setJuxtaposition(Juxtaposition juxtaposition) {
        this.juxtaposition.set(juxtaposition);
        return this;
    }

    /**
     * Created by joefr_000 on 09/02/2017.
     */
    public enum Juxtaposition {

        TOP, MIDDLE, BOTTOM, ONLY

    }
}
