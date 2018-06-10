package com.warm.livelive.douyu.data.http.api.apiv2;

import com.warm.livelive.douyu.data.bean.BaseBean;
import com.warm.livelive.douyu.data.bean.TabCate;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者：warm
 * 时间：2018-03-07 10:06
 * 描述：
 */
public interface Api {
    /**
     * https://apiv2.douyucdn.cn/live/TabCate/custom?client_sys=android
     * @return
     */
    @GET("live/TabCate/custom?client_sys=android")
    Observable<BaseBean<List<TabCate>>> getTabCate();



}
