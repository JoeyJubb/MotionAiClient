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

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by joefr_000 on 14/02/2017.
 */

public class ChooseBotViewModel extends BaseObservable {

    private String colorError;

    private boolean allFieldsComplete;

    private String apiKey;
    private String id;
    private String color;
    private String name;

    @Bindable
    public String getColorError() {
        return colorError;
    }

    public void setColorError(String colorError) {
        this.colorError = colorError;
        notifyPropertyChanged(BR.colorError);
    }

    @Bindable
    public boolean isAllFieldsComplete() {
        return allFieldsComplete;
    }

    public void setAllFieldsComplete(boolean allFieldsComplete) {
        this.allFieldsComplete = allFieldsComplete;
        notifyPropertyChanged(BR.allFieldsComplete);
    }


    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;
    }

    public void setBotId(String id) {

        this.id = id;
    }

    public void setBotColor(String color) {

        this.color = color;
    }

    public void setBotName(String name) {

        this.name = name;
    }
}
