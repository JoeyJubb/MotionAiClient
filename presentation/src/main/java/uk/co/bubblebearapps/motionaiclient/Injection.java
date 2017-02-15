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

import android.content.Context;

import uk.co.bubblebearapps.motionaiclient.internal.di.components.ApplicationComponent;
import uk.co.bubblebearapps.motionaiclient.internal.di.components.DaggerApplicationComponent;
import uk.co.bubblebearapps.motionaiclient.internal.di.modules.ApplicationModule;


/**
 * Android Main Application
 */
public class Injection {

    private static ApplicationComponent applicationComponent;

    private Injection() {
    }

    public static void initializeInjector(Context context) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(context.getApplicationContext()))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            throw new IllegalStateException("Please call Injection.initializeInjector(Context context) from the onCreate method in your Application class");
        }

        return applicationComponent;
    }


}
