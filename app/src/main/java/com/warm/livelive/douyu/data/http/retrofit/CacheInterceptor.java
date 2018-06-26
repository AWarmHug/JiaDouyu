package com.warm.livelive.douyu.data.http.retrofit;

import android.util.Log;

import com.warm.livelive.MyApp;
import com.warm.livelive.utils.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者: 51hs_android
 * 时间: 2017/7/24
 * 简介:
 */

public class CacheInterceptor implements Interceptor {



    @Override
    public Response intercept(Chain chain) throws IOException {

        // 有网络时 设置缓存超时时间1个小时
        int maxAge = 60*60;
        // 无网络时，设置超时为1天
        int maxStale = 60 * 60 * 24;

        Request request = chain.request();

        if (NetworkUtil.isConnected(MyApp.getInstance())) {
            //有网络时只从网络获取
            request = request.newBuilder()
//                    .header("User-Agent","YIXIProject/1.2 ( picsize=iphone6+ ; android 6.0; Scale/2.625)")
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            Log.d("okhttp", "intercept: 有网络时");
        } else {
            //无网络时只从缓存中读取
            request = request.newBuilder()
//                    .header("User-Agent","YIXIProject/1.2 ( picsize=iphone6+ ; android 6.0; Scale/2.625)")
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.d("okhttp", "intercept: 无网络时");
//                Toast.makeText(App.getInstance(), "没有网络", Toast.LENGTH_SHORT).show();
        }

        Response response = chain.proceed(request);


        if (NetworkUtil.isConnected(MyApp.getInstance())) {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();

        }
        return response;
    }
}