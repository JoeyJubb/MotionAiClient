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


/**
 * Created by joefr_000 on 23/01/2017.
 */
public abstract class BotResponse {

    private final String sessionId;
    private final DateTime timeStamp;

    protected BotResponse(String sessionId, DateTime timeStamp) {
        this.sessionId = sessionId;
        this.timeStamp = timeStamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public abstract void accept(Observer observer);

    public interface Observer {
        void visit(Message message);

        void visit(CardList cardList);

        void visit(QuickReplyList quickReplyList);
    }
}
