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

package uk.co.bubblebearapps.motionaiclient.mapper;

import javax.inject.Inject;

import uk.co.bubblebearapps.motionaiclient.BotInfo;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;
import uk.co.bubblebearapps.motionaiclient.model.BotInfoModel;

/**
 * Created by joefr_000 on 13/02/2017.
 */
@PerActivity
public class BotInfoModelMapper {

    @Inject
    public BotInfoModelMapper() {

    }

    public BotInfo map(BotInfoModel botInfoModel) {

        return new BotInfo.Builder().setApiKey(botInfoModel.getApiKey()).setId(botInfoModel.getId()).setName(botInfoModel.getName()).setColor(botInfoModel.getColor()).build();

    }

}
