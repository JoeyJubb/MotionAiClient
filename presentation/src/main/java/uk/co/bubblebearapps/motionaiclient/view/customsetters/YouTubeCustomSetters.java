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

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.databinding.BindingAdapter;
import android.view.View;

import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import uk.co.bubblebearapps.motionaiclient.conversation.ConversationContract;

/**
 * Created by joefr_000 on 14/10/2016.
 */
public class YouTubeCustomSetters {

    private YouTubeCustomSetters() {
    }

    @BindingAdapter(value = {"youTubeId", "actionHandler"})
    public static void setVideoUrl(YouTubeThumbnailView youTubeThumbnailView, final String youTubeId, final ConversationContract.ListItemActionHandler listItemActionHandler) {


        listItemActionHandler.loadYouTubeVideo(youTubeThumbnailView, youTubeId);

        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemActionHandler.onYouTubeThumbnailTapped(youTubeId);
            }
        });

    }


    private static boolean canResolveIntent(Context context, Intent intent) {
        List<ResolveInfo> resolveInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }


}
