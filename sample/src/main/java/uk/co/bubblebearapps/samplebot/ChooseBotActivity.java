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

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.google.common.base.Strings;

import uk.co.bubblebearapps.motionaiclient.model.BotInfoModel;
import uk.co.bubblebearapps.samplebot.databinding.ActivityChooseBotBinding;

/**
 * A login screen that offers login via email/password.
 */
public class ChooseBotActivity extends AppCompatActivity {

    private ActivityChooseBotBinding mViewDataBinding;
    private ChooseBotViewModel chooseBotViewModel;
    private ChooseBotModel chooseBotModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        chooseBotModel = new ChooseBotModel(PreferenceManager.getDefaultSharedPreferences(this));

        chooseBotViewModel = new ChooseBotViewModel();
        checkBotColor();
        checkModelStats();

        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_bot);

        // set initial values
        mViewDataBinding.textApiKey.setText(chooseBotModel.getApiKey());
        mViewDataBinding.textId.setText(chooseBotModel.getId());
        mViewDataBinding.textBotName.setText(chooseBotModel.getName());
        mViewDataBinding.textBotColor.setText(chooseBotModel.getColor());
        mViewDataBinding.cbRemember.setChecked(chooseBotModel.isRemember());


        mViewDataBinding.setViewModel(chooseBotViewModel);
        mViewDataBinding.setModel(chooseBotModel);
        mViewDataBinding.setActionHandler(this);


        chooseBotModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                onModelChanged();
            }
        });

    }

    private void onModelChanged() {
        checkModelStats();
    }

    private void checkModelStats() {

        boolean allOK = checkFields();
        chooseBotViewModel.setAllFieldsComplete(allOK);

    }

    private boolean checkFields() {
        boolean allOK = true;

        if (!HexValidator.validate(chooseBotModel.getColor())) {
            chooseBotViewModel.setColorError(getString(R.string.color_error));
            allOK = false;
        } else {
            chooseBotViewModel.setColorError(null);
        }

        if (Strings.isNullOrEmpty(chooseBotModel.getId())) {
            allOK = false;
        }
        if (Strings.isNullOrEmpty(chooseBotModel.getApiKey())) {
            allOK = false;
        }
        if (Strings.isNullOrEmpty(chooseBotModel.getName())) {
            allOK = false;
        }

        return allOK;
    }


    private void checkBotColor() {
        chooseBotViewModel.setColorError(HexValidator.validate(chooseBotModel.getColor()) ? null : getString(R.string.color_error));
    }

    public void onDoneButtonPressed() {
        if (!checkFields()) {
            chooseBotViewModel.setAllFieldsComplete(false);
            return;
        }


        BotInfoModel botInfo = new BotInfoModel(
                chooseBotModel.getApiKey(),
                chooseBotModel.getId(),
                chooseBotModel.getName(),
                Color.parseColor(chooseBotModel.getColor()));

        startActivity(MainActivity.getCallingIntent(this, botInfo));
        finish();

    }

}

