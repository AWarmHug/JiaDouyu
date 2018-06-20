package com.warm.livelive.eyepetizer.data.http.retrofit;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：warm
 * 时间：2018-06-11 13:01
 * 描述：
 */
public class EyeCommonInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("udid", "d24d0fa723e4450cb436e27d28e7dcebd350141b")
                .addQueryParameter("vc","341")
                .addQueryParameter("vn","3.19")
                .addQueryParameter("deviceModel","MI%205")
                .addQueryParameter("first_channel","eyepetizer_xiaomi_market")
                .addQueryParameter("last_channel","eyepetizer_xiaomi_market")
                .addQueryParameter("system_version_code","24")
                .build();

        request = request.newBuilder()
                .url(url)
                .build();

        return chain.proceed(request);
    }
}