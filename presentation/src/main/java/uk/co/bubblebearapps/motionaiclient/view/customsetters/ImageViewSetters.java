package uk.co.bubblebearapps.motionaiclient.view.customsetters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.common.base.Strings;

/**
 * Created by joefr_000 on 14/10/2016.
 */
@BindingMethods({
        @BindingMethod(type = ImageView.class,
                attribute = "srcCompat",
                method = "setImageDrawable")})
public class ImageViewSetters {


    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {

        if (Strings.isNullOrEmpty(url)) {
            imageView.setImageBitmap(null);}
        else if(url.endsWith("gif")){
            Glide.with(
                    imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);

        } else {
            Glide.with(
                    imageView.getContext())
                    .load(url)
                    .into(imageView);
        }
    }

    @BindingAdapter("circleImageUrl")
    public static void setCircularImageUrl(final ImageView imageView, String url) {
        if (Strings.isNullOrEmpty(url)) {
            imageView.setImageBitmap(null);
        } else {
            Glide.with(imageView.getContext()).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.getResources(), resource);
                    roundedBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(roundedBitmapDrawable);
                }
            });
        }
    }
}
