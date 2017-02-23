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
 * Created by joefr_000 on 31/01/2017.
 */
public class Message extends BotResponse {

    private final Type type;

    private final String payload;

    private Message(String sessionId, DateTime timeStamp, Type type, String payload) {
        super(sessionId, timeStamp);
        this.type = type;
        this.payload = payload;
    }

    public Type getType() {
        return type;
    }


    public String getPayload() {
        return payload;
    }

    @Override
    public void accept(Observer observer) {
        observer.visit(this);
    }

    /**
     * Created by joefr_000 on 02/02/2017.
     */
    public enum Type {
        IMAGE, TEXT, VIDEO, UNKNOWN, YOUTUBE
    }

    public static class Builder {
        private String sessionId;
        private DateTime timeStamp;
        private Type type;
        private String payload;

        public Builder setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder setTimeStamp(DateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public Builder setPayload(String payload) {
            this.payload = payload;
            return this;
        }

        public Message build() {
            return new Message(sessionId, timeStamp, type, payload);
        }
    }
}
