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
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.google.common.base.Strings;

import javax.inject.Singleton;

/**
 * Created by joefr_000 on 25/01/2017.
 */
@Singleton
public class TextViewSetters {


    @BindingAdapter("htmlText")
    public static void setImageUrl(final TextView textView, String htmlText) {

        if (Strings.isNullOrEmpty(htmlText)) {
            textView.setText(null);
            return;
        }

        Spanned html;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            html = Html.fromHtml(htmlText, Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV);
        } else {
            //noinspection deprecation
            html = Html.fromHtml(htmlText);
        }

        textView.setText(html);
    }


}
