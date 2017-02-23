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

package uk.co.bubblebearapps.motionaiclient.repository;

import com.google.common.util.concurrent.RateLimiter;

import rx.Observable;
import rx.functions.Func1;
import uk.co.bubblebearapps.motionaiclient.BotResponse;
import uk.co.bubblebearapps.motionaiclient.UserInfo;
import uk.co.bubblebearapps.motionaiclient.datasource.BotDataSource;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;
import uk.co.bubblebearapps.motionaiclient.mapper.ResponseEntityMapper;

/**
 * Created by joefr_000 on 23/01/2017.
 */

public class ConversationsDataRepository implements ConversationsRepository {

    private static final String TAG = "ConvoRepo";

    final RateLimiter rateLimiter = RateLimiter.create(1);

    private final BotDataSource moviesDataSource;
    private final ResponseEntityMapper entityMapper;

    public ConversationsDataRepository(BotDataSource moviesDataSource, ResponseEntityMapper entityMapper) {
        this.moviesDataSource = moviesDataSource;
        this.entityMapper = entityMapper;
    }

    @Override
    public Observable<BotResponse> messageBot(final String apiKey, final String botId, String message, final UserInfo userInfo) {
        return moviesDataSource.messageBot(apiKey, Integer.valueOf(botId), message, userInfo.getId(), userInfo.getTitle())
                .flatMap(new Func1<ResponseEntity, Observable<BotResponse>>() {
                    @Override
                    public Observable<BotResponse> call(ResponseEntity responseEntity) {

                        Observable<BotResponse> responseObservable = entityMapper.map(responseEntity);

                        if (responseEntity.isAutoReply()) {
                            rateLimiter.acquire();
                            return responseObservable.concatWith(
                                    messageBot(apiKey, botId, " ", userInfo)
                            );
                        } else {
                            return responseObservable;
                        }

                    }
                });

    }
}
