package com.warm.livelive.eyepetizer.data.http.api;

import com.warm.livelive.eyepetizer.data.bean.PageData;
import com.warm.livelive.eyepetizer.data.bean.TabInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 作者：warm
 * 时间：2018-06-19 10:25
 * 描述：
 */
public interface Api {

    @GET
    Observable<PageData> getPageData(@Url String url, @Query("page") int page);

    @GET("api/v5/index/tab/list")
    Observable<TabInfo> loadData();

}
