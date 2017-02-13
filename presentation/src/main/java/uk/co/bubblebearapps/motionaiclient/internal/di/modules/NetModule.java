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

package uk.co.bubblebearapps.motionaiclient.internal.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.bubblebearapps.motionaiclient.datasource.BotDataSource;
import uk.co.bubblebearapps.motionaiclient.deserializer.QuickReplyListDeserializer;
import uk.co.bubblebearapps.motionaiclient.entity.QuickReplyEntity;
import uk.co.bubblebearapps.motionaiclient.mapper.ResponseEntityMapper;
import uk.co.bubblebearapps.motionaiclient.repository.ConversationsDataRepository;
import uk.co.bubblebearapps.motionaiclient.repository.ConversationsRepository;

@Module
public class NetModule {

    private static final String TAG = "NetModule";

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {

        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }


    @Provides
    @Singleton
    Gson provideGson(QuickReplyListDeserializer quickReplyListDeserializer) {
        return new GsonBuilder()
                .registerTypeAdapter(QuickReplyEntity[].class, quickReplyListDeserializer)
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(BotDataSource.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    BotDataSource provideConversationsService(Retrofit retrofit) {
        return retrofit.create(BotDataSource.class);
    }

    @Provides
    @Singleton
    ConversationsRepository provideConversationsRepository(BotDataSource moviesDataSource, ResponseEntityMapper moviesEntityMapper) {
        return new ConversationsDataRepository(moviesDataSource, moviesEntityMapper);
    }


}