package com.warm.livelive.douyu.data.http.retrofit;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：warm
 * 时间：2018-06-11 13:01
 * 描述：&udid=d24d0fa723e4450cb436e27d28e7dcebd350141b&vc=341&vn=3.19&deviceModel=MI%205&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=24
 */
public class DouyuCommonInterceptor implements Interceptor {
    private static final String TAG = "DouyuCommonInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        if (url.url().getFile().contains("png") || url.url().getFile().contains("jpg") || url.url().getFile().contains("gif") || url.host().equals("www.douyutv.com")) {
            return chain.proceed(request);
        }

        url = request.url().newBuilder()
                .addQueryParameter("client_sys", "android")
                .build();

        request = request.newBuilder()
                .url(url)
//                    .header("User-Device", "YzQ5ZDkxODdjMGM3MzlmZWRkOWU2M2E4MzA1MDUxMTF8djQuMi4w")
                .header("User-Agent", "android/4.2.0 (android 7.0; ; MI+5)")
                .header("time", String.valueOf(System.currentTimeMillis() / 1000))
                .header("channel", "30")
                .header("aid", "android1")
//                    .header("Cookie", "acf_did=c49d9187c0c739fedd9e63a830505111")
                .build();
//        Log.d(TAG, "intercept: "+url);
        return chain.proceed(request);
    }
}