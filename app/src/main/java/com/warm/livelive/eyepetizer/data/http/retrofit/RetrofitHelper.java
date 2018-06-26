package com.warm.livelive.eyepetizer.data.http.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.warm.livelive.BuildConfig;
import com.warm.livelive.MyApp;
import com.warm.livelive.eyepetizer.config.EyeConfig;
import com.warm.livelive.eyepetizer.data.bean.data.BaseData;
import com.warm.livelive.eyepetizer.data.bean.data.FollowCardData;
import com.warm.livelive.eyepetizer.data.bean.data.ItemCollectionData;
import com.warm.livelive.eyepetizer.data.bean.data.TextCardData;
import com.warm.livelive.eyepetizer.data.bean.data.UcgVideoBeanData;
import com.warm.livelive.eyepetizer.data.bean.data.UgcPictureBeanData;
import com.warm.livelive.eyepetizer.data.bean.data.VideoBeanForClientData;
import com.warm.livelive.eyepetizer.data.http.adapter.RuntimeTypeAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：warm
 * 时间：2017-11-20 11:12
 * 描述：
 */

public class RetrofitHelper {
    private static OkHttpClient eyeClient;
    private static Retrofit eyeRetrofit;

    public static void init() {
        if (eyeClient == null) {
            synchronized (RetrofitHelper.class) {
                if (eyeClient == null) {
                    eyeClient = provideOkHttpClient();
                }
            }
        }
        if (eyeRetrofit == null) {
            synchronized (RetrofitHelper.class) {
                if (eyeRetrofit == null) {
                    eyeRetrofit = provideRetrofit(eyeClient);
                }
            }
        }
    }

    public static OkHttpClient provideOkHttpClient() {
        //设置Http缓存
        Cache cache = new Cache(new File(MyApp.getInstance().getExternalCacheDir(),"cache_http"), 1024 * 1024 * 10);


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .cache(cache)
//                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new EyeCommonInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    public static Retrofit provideRetrofit(OkHttpClient client) {
        RuntimeTypeAdapterFactory<BaseData> factory=RuntimeTypeAdapterFactory
                .of(BaseData.class,"dataType")
                .registerSubtype(FollowCardData.class,FollowCardData.DATA_TYPE_FOLLOW_CARD)
                .registerSubtype(ItemCollectionData.class,ItemCollectionData.DATA_TYPE_ITEM_COLLECTION)
                .registerSubtype(TextCardData.class,TextCardData.DATA_TYPE_TEXT_CARD)
                .registerSubtype(UgcPictureBeanData.class,UgcPictureBeanData.DATA_TYPE_UGC_PICTURE_BEAN)
                .registerSubtype(UcgVideoBeanData.class,UgcPictureBeanData.DATA_TYPE_UGC_VIDEO_BEAN)
                .registerSubtype(VideoBeanForClientData.class,VideoBeanForClientData.DATA_TYPE_VIDEO_BEAN_FOR_CLIENT);

        Gson gson=new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(factory)
                .create();

        return new Retrofit.Builder()
                .baseUrl(EyeConfig.HTTP)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static <T> T provideApi(Class<T> clazz) {
        if (eyeRetrofit == null) {
            init();
        }
        return eyeRetrofit.create(clazz);
    }



}
