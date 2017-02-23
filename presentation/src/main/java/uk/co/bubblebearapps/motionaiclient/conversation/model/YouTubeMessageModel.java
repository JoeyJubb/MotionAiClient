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

/**
 * Created by joefr_000 on 21/02/2017.
 */
public class YouTubeMessageModel extends MessageModel {

    private String youTubeApiKey;

    public static YouTubeMessageModel wrap(MessageModel messageModel, String youtubeApiKey) {
        return (YouTubeMessageModel) new YouTubeMessageModel()
                .setYouTubeApiKey(youtubeApiKey)
                .setTarget(messageModel.getTarget())
                .setJuxtaposition(messageModel.juxtaposition.get())
                .setType(messageModel.getType())
                .setMessageBackgroundColor(messageModel.getMessageBackgroundColor())
                .setContentsHash(messageModel.getContentsHash())
                .setLocalId(messageModel.getLocalId())
                .setSecondaryOrder(messageModel.getSecondaryOrder())
                .setTimeStamp(messageModel.getTimeStamp());
    }

    public String getYouTubeApiKey() {
        return youTubeApiKey;
    }

    public YouTubeMessageModel setYouTubeApiKey(String youTubeApiKey) {
        this.youTubeApiKey = youTubeApiKey;
        return this;
    }
}
