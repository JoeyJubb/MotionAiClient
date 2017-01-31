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

        if(Strings.isNullOrEmpty(htmlText)){
            textView.setText(null);
            return;
        }

        Spanned html;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            html = Html.fromHtml(htmlText, Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV);
        }else{
            html = Html.fromHtml(htmlText);
        }

        textView.setText(html);
    }




}
