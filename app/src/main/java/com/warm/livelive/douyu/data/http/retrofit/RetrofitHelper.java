package com.warm.livelive.douyu.data.http.retrofit;


import com.warm.livelive.BuildConfig;
import com.warm.livelive.LiveApp;
import com.warm.livelive.douyu.config.DouyuConfig;

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
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit,retrofitV2;

    public static void init() {
        if (okHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (okHttpClient == null) {
                    okHttpClient = provideOkHttpClient();
                }
            }
        }
        if (retrofit == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofit == null) {
                    retrofit = provideRetrofit(okHttpClient);
                }
            }
        }
        if (retrofitV2==null){
            synchronized (RetrofitHelper.class){
                if (retrofitV2==null){
                    retrofitV2=provideRetrofitV2(okHttpClient);
                }
            }
        }
    }

    public static OkHttpClient provideOkHttpClient() {
        //设置Http缓存
        Cache cache = new Cache(new File(LiveApp.getInstance().getExternalCacheDir(),"cache_http"), 1024 * 1024 * 10);


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .cache(cache)
//                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new CommonInterceptor())
                .sslSocketFactory(SSLManager.createSSLSocketFactory(),new SSLManager.TrustAllManager())
                .hostnameVerifier(new SSLManager.TrustAllHostnameVerifier())
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
        return new Retrofit.Builder()
                .baseUrl(DouyuConfig.DOUYU_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Retrofit provideRetrofitV2(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(DouyuConfig.DOUYU_APIV2)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static <T> T provideApi(Class<T> clazz) {
        if (retrofit == null) {
            init();
        }
        return retrofit.create(clazz);
    }

    public static <T> T provideApiV2(Class<T> clazz) {
        if (retrofitV2 == null) {
            init();
        }
        return retrofitV2.create(clazz);
    }


}
