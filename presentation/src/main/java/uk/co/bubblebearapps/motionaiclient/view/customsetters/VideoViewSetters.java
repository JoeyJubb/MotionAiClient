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
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.common.base.Strings;

/**
 * Created by joefr_000 on 14/10/2016.
 */
public class VideoViewSetters {

    private VideoViewSetters() {
    }

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
