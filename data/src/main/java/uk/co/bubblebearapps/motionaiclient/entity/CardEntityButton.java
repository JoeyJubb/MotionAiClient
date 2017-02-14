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
public class CardEntityButton {

    @SerializedName("_id")
    private String id;

    @SerializedName("target")
    private String target;

    @SerializedName("buttonType")
    private String type;
    @SerializedName("buttonText")
    private String label;

    public String getId() {
        return id;
    }

    public String getTarget() {
        return target;
    }

    /**
     * Can be one of "module" or "url"
     */
    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

}
