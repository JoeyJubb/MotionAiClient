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
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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


    @BindingAdapter(value = {"imageUrl", "cornerRadius"}, requireAll = false)
    public static void setImageUrl(final ImageView imageView, String url, final float cornerRadius) {

        if (Strings.isNullOrEmpty(url)) {
            imageView.setImageBitmap(null);
        } else if (url.endsWith("gif")) {
            Glide.with(
                    imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);

        } else {
            if (cornerRadius > 0) {
                Glide.with(
                        imageView.getContext())
                        .load(url)
                        .asBitmap()
                        .into(new BitmapImageViewTarget(imageView) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                                circularBitmapDrawable.setCornerRadius(cornerRadius);
                                imageView.setImageDrawable(circularBitmapDrawable);
                            }
                        });

            } else {

                Glide.with(
                        imageView.getContext())
                        .load(url)
                        .into(imageView);
            }
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
