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

package uk.co.bubblebearapps.motionaiclient.deserializer;

import android.support.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.bubblebearapps.motionaiclient.entity.QuickReplyEntity;

/**
 * Created by joefr_000 on 10/02/2017.
 */
@Singleton
public class QuickReplyListDeserializer implements JsonDeserializer<QuickReplyEntity[]> {

    @Inject
    public QuickReplyListDeserializer() {
    }

    @Override
    public QuickReplyEntity[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        ArrayList<QuickReplyEntity> results = new ArrayList<>();

        JsonArray asJsonArray = json.getAsJsonArray();
        final int count = asJsonArray.size();
        for (int i = 0; i < count; i++) {

            QuickReplyEntity quickReplyEntity = deserialize(asJsonArray.get(i));

            if (quickReplyEntity != null) {
                results.add(quickReplyEntity);
            }

        }

        return results.toArray(new QuickReplyEntity[results.size()]);

    }

    @Nullable
    private QuickReplyEntity deserialize(JsonElement json) {
        if (json.isJsonObject()) {

            JsonObject jsonObject = json.getAsJsonObject();

            return new QuickReplyEntity()
                    .setId(jsonObject.get("_id").getAsString())
                    .setContentType(jsonObject.get("content_type").getAsString())
                    .setPayload(jsonObject.get("payload").getAsString())
                    .setTitle(jsonObject.get("title").getAsString())
                    ;

        } else {
            return null;
        }
    }
}
