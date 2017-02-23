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

import uk.co.bubblebearapps.motionaiclient.UserInfo;
import uk.co.bubblebearapps.motionaiclient.internal.di.PerActivity;
import uk.co.bubblebearapps.motionaiclient.model.UserInfoModel;

/**
 * Created by joefr_000 on 13/02/2017.
 */
@PerActivity
public class UserInfoModelMapper {

    @Inject
    public UserInfoModelMapper() {

    }

    public UserInfo map(UserInfoModel userInfoModel) {

        return new UserInfo.Builder()
                .setId(userInfoModel.getId())
                .setTitle(userInfoModel.getTitle())
                .build();

    }

}
