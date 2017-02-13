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

package uk.co.bubblebearapps.motionaiclient.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joefr_000 on 02/02/2017.
 */

public class BotInfoModel implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BotInfoModel> CREATOR = new Parcelable.Creator<BotInfoModel>() {
        @Override
        public BotInfoModel createFromParcel(Parcel in) {
            return new BotInfoModel(in);
        }

        @Override
        public BotInfoModel[] newArray(int size) {
            return new BotInfoModel[size];
        }
    };
    private final String id;
    private final String name;
    private final String apiKey;
    private final int color;

    public BotInfoModel(String apiKey, String id, String name, int color) {
        this.apiKey = apiKey;
        this.id = id;
        this.name = name;
        this.color = color;
    }

    protected BotInfoModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        apiKey = in.readString();
        color = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(apiKey);
        dest.writeInt(color);
    }
}