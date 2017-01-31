package uk.co.bubblebearapps.motionaiclient.internal.di.modules;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.bubblebearapps.motionaiclient.MessageEntityDeserializer;
import uk.co.bubblebearapps.motionaiclient.data.BuildConfig;
import uk.co.bubblebearapps.motionaiclient.datasource.BotDataSource;
import uk.co.bubblebearapps.motionaiclient.entity.QuickReplyEntity;
import uk.co.bubblebearapps.motionaiclient.entity.ResponseEntity;
import uk.co.bubblebearapps.motionaiclient.mapper.ResponseEntityMapper;
import uk.co.bubblebearapps.motionaiclient.repository.ConversationsDataRepository;
import uk.co.bubblebearapps.motionaiclient.repository.ConversationsRepository;

@Module
public class NetModule {

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Interceptor provideApiInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();

                HttpUrl modifiedUrl = originalUrl.newBuilder()
                        .addQueryParameter("key", BuildConfig.API_KEY)
                        .build();
                Request newRequest = originalRequest
                        .newBuilder()
                        .url(modifiedUrl)
                        .build();


                return chain.proceed(newRequest);
            }
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
    }


    @Provides
    @Singleton
    Gson provideGson(MessageEntityDeserializer messageEntityDeserializer) {
        return new GsonBuilder()
                .registerTypeAdapter(ResponseEntity.class, messageEntityDeserializer)
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
    ConversationsRepository provideConversationsRepository(Context context, BotDataSource moviesDataSource, ResponseEntityMapper moviesEntityMapper){
        return new ConversationsDataRepository(context, moviesDataSource, moviesEntityMapper);
    }


}