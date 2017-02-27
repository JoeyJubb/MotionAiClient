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


/**
 * Created by joefr_000 on 23/01/2017.
 */
public class QuickReply {


    private final String textContent;

    private final String id;

    private final QuickReplyType type;
    private final String payload;

    private QuickReply(String id, String textContent, QuickReplyType type, String payload) {
        this.id = id;
        this.textContent = textContent;
        this.type = type;
        this.payload = payload;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public QuickReplyType getType() {
        return type;
    }

    public enum QuickReplyType {
        TEXT, URL, PHONE, CALENDAR, LOCATION
    }

    public static class Builder {
        private String id;
        private String textContent;
        private QuickReplyType type;
        private String payload;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setTextContent(String textContent) {
            this.textContent = textContent;
            return this;
        }

        public QuickReply build() {
            return new QuickReply(id, textContent, type, payload);
        }

        public Builder setType(QuickReplyType type) {
            this.type = type;
            return this;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }
    }
}
