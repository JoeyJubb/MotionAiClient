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

import org.joda.time.DateTime;

import java.util.List;


/**
 * Created by joefr_000 on 23/01/2017.
 */
public class BotResponse {

    private String sessionId;
    private DateTime timeStamp;
    private List<Message> messages;
    private List<Card> cards;
    private List<QuickReply> quickReplies;

    public List<Message> getMessages() {
        return messages;
    }

    public BotResponse setMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }

    public List<Card> getCards() {
        return cards;
    }

    public BotResponse setCards(List<Card> cards) {
        this.cards = cards;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public BotResponse setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public List<QuickReply> getQuickReplies() {
        return quickReplies;
    }

    public BotResponse setQuickReplies(List<QuickReply> quickReplies) {
        this.quickReplies = quickReplies;
        return this;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public BotResponse setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }
}
