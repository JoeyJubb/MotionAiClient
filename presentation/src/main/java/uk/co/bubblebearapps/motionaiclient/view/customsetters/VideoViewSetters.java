package uk.co.bubblebearapps.motionaiclient.view.customsetters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.common.base.Strings;

/**
 * Created by joefr_000 on 14/10/2016.
 */
public class VideoViewSetters {

    private VideoViewSetters(){}

    @BindingAdapter("videoUrl")
    public static void setVideoUrl(VideoView videoView, String url) {

        Uri uri = Strings.isNullOrEmpty(url) ? Uri.EMPTY : Uri.parse(url);

        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(videoView.getContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
    }

}
