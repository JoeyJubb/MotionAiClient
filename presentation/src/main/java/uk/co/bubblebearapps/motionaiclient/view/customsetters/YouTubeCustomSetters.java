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

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import uk.co.bubblebearapps.motionaiclient.conversation.model.YouTubeMessageModel;

/**
 * Created by joefr_000 on 14/10/2016.
 */
public class YouTubeCustomSetters {

    private YouTubeCustomSetters() {
    }

    @BindingAdapter(value = {"youTubeModel"})
    public static void setVideoUrl(YouTubeThumbnailView youTubeThumbnailView, final YouTubeMessageModel youTubeMessageModel) {


        youTubeThumbnailView.initialize(youTubeMessageModel.getYouTubeApiKey(), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(youTubeMessageModel.getTarget());
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }

}
