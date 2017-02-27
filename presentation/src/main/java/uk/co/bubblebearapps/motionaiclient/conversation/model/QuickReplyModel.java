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

import uk.co.bubblebearapps.motionaiclient.QuickReply;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class QuickReplyModel {

    private String textContent;

    private String id;

    private QuickReply.QuickReplyType quickReplyType;

    private String payload;


    public String getTextContent() {
        return textContent;
    }

    public QuickReplyModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getId() {
        return id;
    }

    public QuickReplyModel setId(String id) {
        this.id = id;
        return this;
    }

    public QuickReply.QuickReplyType getQuickReplyType() {
        return quickReplyType;
    }

    public QuickReplyModel setQuickReplyType(QuickReply.QuickReplyType quickReplyType) {
        this.quickReplyType = quickReplyType;
        return this;
    }

    public String getPayload() {
        return payload;
    }

    public QuickReplyModel setPayload(String payload) {
        this.payload = payload;
        return this;
    }
}
