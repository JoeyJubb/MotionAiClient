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

package uk.co.bubblebearapps.motionaiclient.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Loader to supply a presenter to an activty or fragment. Using a loader allows the presenter to
 * be retained during configuration changes
 *
 * @param <T> the presenter class this loader will deliver
 */
public class PresenterLoader<T extends BasePresenter> extends Loader<T> {

    private final PresenterFactory<T> factory;
    private T presenter;

    /**
     * @param context used to retrieve the application context.
     * @param factory used to provide a presenter
     */
    public PresenterLoader(Context context, @NonNull PresenterFactory<T> factory) {
        super(context);
        this.factory = checkNotNull(factory);
    }

    // Constructor...

    @Override
    protected void onStartLoading() {

        // If we already own an instance, simply deliver it.
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        // Create the Presenter using the Factory
        presenter = factory.providePresenter();

        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        presenter.onDestroyed();
        presenter = null;
    }

}