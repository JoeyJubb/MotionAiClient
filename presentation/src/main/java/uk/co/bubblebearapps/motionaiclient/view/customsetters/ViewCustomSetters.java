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

package uk.co.bubblebearapps.motionaiclient.view.customsetters;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;

/**
 * Created by joefr_000 on 09/02/2017.
 */
public class ViewCustomSetters {

    private ViewCustomSetters() {
    }

    @BindingAdapter("backgroundTint")
    public static void setBackgroundTint(final View view, int tint) {

        if (view.getBackground() == null) {
            return;
        }

        Drawable wrapped = DrawableCompat.wrap(view.getBackground());
        DrawableCompat.setTint(wrapped, tint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(wrapped);
        } else {
            view.setBackgroundDrawable(wrapped);
        }
    }
}
