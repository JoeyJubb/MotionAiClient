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

package uk.co.bubblebearapps.samplebot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.UUID;

import uk.co.bubblebearapps.motionaiclient.conversation.ConversationDisplayFragment;
import uk.co.bubblebearapps.motionaiclient.model.BotInfoModel;
import uk.co.bubblebearapps.motionaiclient.model.UserInfoModel;

public class MainActivity extends AppCompatActivity implements ConversationDisplayFragment.Callback {

    private static final String TAG_CONVO = "uk.co.bubblebearapps.motionaiclient.TAG_CONVO";
    private static final String EXTRA_BOT_INFO = "uk.co.bubblebearapps.motionaiclient.EXTRA_BOT_INFO";

    public static Intent getCallingIntent(Context context, BotInfoModel botInfo) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_BOT_INFO, botInfo);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.conversation_act);


        if (savedInstanceState == null) {

            UserInfoModel userInfo = new UserInfoModel(UUID.randomUUID().toString(), "John Doe");// create some random userInfo credentials for now

            BotInfoModel botInfo = getIntent().getParcelableExtra(EXTRA_BOT_INFO);
            ConversationDisplayFragment conversationDisplayFragment = ConversationDisplayFragment.newInstance(BuildConfig.YOUTUBE_API_KEY, userInfo, botInfo);

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, conversationDisplayFragment, TAG_CONVO).commitNow();
        }
    }

    public void setColorScheme(int colorPrimary) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            float[] hsv = new float[3];
            Color.RGBToHSV(Color.red(colorPrimary), Color.green(colorPrimary), Color.blue(colorPrimary), hsv);
            hsv[2] *= 0.8;

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.HSVToColor(hsv));
        }

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(colorPrimary));
    }
}
