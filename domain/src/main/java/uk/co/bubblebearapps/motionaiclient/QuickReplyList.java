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

import org.joda.time.DateTime;

/**
 * Created by joefr_000 on 15/02/2017.
 */

public class QuickReplyList extends BotResponse {

    private final ImmutableList<QuickReply> quickReplyList;

    private QuickReplyList(String sessionId, DateTime timeStamp, ImmutableList<QuickReply> quickReplyList) {
        super(sessionId, timeStamp);
        this.quickReplyList = quickReplyList;
    }

    @Override
    public void accept(Observer observer) {
        observer.visit(this);
    }

    public ImmutableList<QuickReply> getQuickReplyList() {
        return quickReplyList;
    }

    public static class Builder {
        private final ImmutableList.Builder<QuickReply> quickReplyBuilder = new ImmutableList.Builder<>();
        private String sessionId;
        private DateTime timeStamp;

        public Builder setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder setTimeStamp(DateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder addQuickReply(QuickReply quickReply) {
            quickReplyBuilder.add(quickReply);
            return this;
        }

        public QuickReplyList build() {
            return new QuickReplyList(sessionId, timeStamp, quickReplyBuilder.build());
        }
    }
}
