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
 * Created by joefr_000 on 02/02/2017.
 */

public class BotInfo {

    private final String id;
    private final String name;
    private final String apiKey;
    private final int color;

    public BotInfo(String apiKey, String id, String name, int color) {
        this.apiKey = apiKey;
        this.id = id;
        this.name = name;

        this.color = color;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public String getApiKey() {
        return apiKey;
    }
}
