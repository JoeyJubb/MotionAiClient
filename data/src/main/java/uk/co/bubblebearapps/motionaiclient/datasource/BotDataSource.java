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

package uk.co.bubblebearapps.motionaiclient.datasource;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public interface BotDataSource {


    String BASE_URL = "https://api.motion.ai/1.0/";

    @GET("messageBot")
    Observable<ResponseEntity> messageBot(
            @Query("key") String apiKey,
            @Query("bot") int botId,
            @Query("msg") String message,
            @Query("session") String sessionId,
            @Query("from") String from);

}
