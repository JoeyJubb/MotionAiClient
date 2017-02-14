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

package uk.co.bubblebearapps.samplebot;

import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.util.Log;

/**
 * Created by joefr_000 on 14/02/2017.
 */

public class ChooseBotModel extends BaseObservable {

    private static final String PREF_API = "uk.co.bubblebearapps.samplebot.PREF_API";
    private static final String PREF_YOUTUBE = "uk.co.bubblebearapps.samplebot.PREF_YOUTUBE";
    private static final String PREF_ID = "uk.co.bubblebearapps.samplebot.PREF_ID";
    private static final String PREF_COLOR = "uk.co.bubblebearapps.samplebot.PREF_COLOR";
    private static final String PREF_NAME = "uk.co.bubblebearapps.samplebot.PREF_NAME";
    private static final String PREF_REMEMBER = "uk.co.bubblebearapps.samplebot.PREF_REMEMBER";

    private static final String TAG = "ChooseBotModel";

    private final SharedPreferences sharedPreferences;

    private String apiKey;
    private String id;
    private String color;
    private String name;
    private String youTubeKey;

    private boolean remember;

    public ChooseBotModel(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;

        if (sharedPreferences.getBoolean(PREF_REMEMBER, false)) {

            remember = true;
            apiKey = sharedPreferences.getString(PREF_API, "");
            youTubeKey = sharedPreferences.getString(PREF_YOUTUBE, "");
            id = sharedPreferences.getString(PREF_ID, "");
            color = sharedPreferences.getString(PREF_COLOR, "");
            name = sharedPreferences.getString(PREF_NAME, "");

        }


        addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                switch (propertyId) {
                    case BR.apiKey:
                        if (remember) {
                            savePreference(PREF_API, apiKey);
                        }
                        break;
                    case BR.youTubeKey:
                        if (remember) {
                            savePreference(PREF_YOUTUBE, youTubeKey);
                        }
                        break;
                    case BR.id:
                        if (remember) {
                            savePreference(PREF_ID, id);
                        }
                        break;
                    case BR.color:
                        if (remember) {
                            savePreference(PREF_COLOR, color);
                        }
                        break;
                    case BR.name:
                        if (remember) {
                            savePreference(PREF_NAME, name);
                        }
                        break;
                    case BR.remember:
                        savePreference(PREF_REMEMBER, remember);
                        if (remember) {
                            saveAll();
                        } else {
                            clearAll();
                        }
                        break;
                }
            }
        });
    }

    private void clearAll() {
        sharedPreferences.edit()
                .remove(PREF_API)
                .remove(PREF_ID)
                .remove(PREF_COLOR)
                .remove(PREF_NAME)
                .remove(PREF_REMEMBER)
                .apply();
    }

    private void saveAll() {
        sharedPreferences.edit()
                .putString(PREF_API, apiKey)
                .putString(PREF_ID, id)
                .putString(PREF_COLOR, color)
                .putString(PREF_NAME, name)
                .putBoolean(PREF_REMEMBER, remember)
                .apply();

    }

    private void savePreference(String key, String value) {

        Log.d(TAG, String.format("Saving %s -> %s", key, value));
        sharedPreferences.edit().putString(key, value).apply();
    }

    private void savePreference(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    @Bindable
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;
        notifyPropertyChanged(BR.apiKey);
    }


    @Bindable
    public String getYouTubeKey() {
        return youTubeKey;
    }

    public void setYouTubeKey(String youTubeKey) {
        this.youTubeKey = youTubeKey;
        notifyPropertyChanged(BR.youTubeKey);
    }

    @Bindable
    public String getId() {
        return id;
    }

    @Bindable
    public String getColor() {
        return color;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setBotId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public void setBotColor(String color) {
        this.color = color;
        notifyPropertyChanged(BR.color);
    }

    public void setBotName(String name) {

        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
        notifyPropertyChanged(BR.remember);
    }

}
