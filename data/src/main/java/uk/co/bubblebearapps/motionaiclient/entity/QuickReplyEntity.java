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

import com.google.gson.annotations.SerializedName;

/**
 * Created by joefr_000 on 23/01/2017.
 */
public class QuickReplyEntity {

    @SerializedName("title")
    private String title;

    @SerializedName("_id")
    private String id;

    @SerializedName("payload")
    private Object payload;
    @SerializedName("content_type")
    /**
     * Can be one of "text"
     */
    private String contentType;

    public String getTitle() {
        return title;
    }

    public QuickReplyEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getId() {
        return id;
    }

    public QuickReplyEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Object getPayload() {
        return payload;
    }

    public QuickReplyEntity setPayload(Object payload) {
        this.payload = payload;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public QuickReplyEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

}
