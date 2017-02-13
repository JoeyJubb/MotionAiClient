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

package uk.co.bubblebearapps.motionaiclient.entity;

import android.support.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public class ResponseEntity {

    @Nullable
    @SerializedName("botResponse")
    private String botResponse;

    @SerializedName("result")
    private String result;

    @SerializedName("inReplyTo")
    private String inReplyTo;

    @SerializedName("module")
    private int module;

    @SerializedName("cards")
    private CardEntity[] cards;

    @SerializedName("session")
    private String session;

    @SerializedName("quickReplies")
    private QuickReplyEntity[] quickReplies;

    @SerializedName("immediatelyGoToNext")
    private Boolean autoReply;

    @SerializedName("code")

    private int responseCode;

    public ResponseEntity() {
    }

    @Nullable
    public String getBotResponse() {

        return botResponse;
    }

    public String getResult() {
        return result;
    }

    public String getInReplyTo() {
        return inReplyTo;
    }

    public int getModule() {
        return module;
    }

    public ResponseEntity setModule(int module) {
        this.module = module;
        return this;
    }

    public CardEntity[] getCards() {
        return cards;
    }

    public String getSession() {
        return session;
    }


    public QuickReplyEntity[] getQuickReplies() {
        return quickReplies;
    }

    /**
     * @return HTTP status code for response
     * @see <a href="https://en.wikipedia.org/wiki/List_of_HTTP_status_codes">HTTP Status Codes</a>
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @return true if the client should automatically fetch the next module, false otherwise
     */
    public Boolean isAutoReply() {
        return autoReply;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("botResponse", botResponse)
                .add("result", result)
                .add("inReplyTo", inReplyTo)
                .add("module", module)
                .add("cards", cards)
                .add("session", session)
                .add("quickReplies", quickReplies)
                .add("responseCode", responseCode)
                .add("autoReply", autoReply)
                .toString();
    }
}
