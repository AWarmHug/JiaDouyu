package com.warm.livelive.douyu.data.http.api.apiv2;

import com.warm.livelive.douyu.data.bean.douyu.ActivityList;
import com.warm.livelive.douyu.data.bean.douyu.BaseBean;
import com.warm.livelive.douyu.data.bean.douyu.Component;
import com.warm.livelive.douyu.data.bean.douyu.Promotion;
import com.warm.livelive.douyu.data.bean.douyu.SlideList;
import com.warm.livelive.douyu.data.bean.douyu.TabCate;
import com.warm.livelive.douyu.data.bean.douyu.TabCate1;
import com.warm.livelive.douyu.data.bean.douyu.TabCate2;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveCate1List;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveCate2ByCate1;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveRoomList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 作者：warm
 * 时间：2018-03-07 10:06
 * 描述：
 */
public interface LiveApis {
    /**
     * https://apiv2.douyucdn.cn/live/TabCate/custom?client_sys=android
     *
     * @return
     */
    @GET("live/TabCate/custom?client_sys=android")
    Observable<BaseBean<List<TabCate>>> getTabCate();


    @GET("live/Cate/getTabCate1List")
    Observable<BaseBean<List<TabCate1>>> getTabCate1List();

    //    https://apiv2.douyucdn.cn/live/Cate/getTabCate2List?tab_id=3&client_sys=android
    @GET("live/Cate/getTabCate2List")
    Observable<BaseBean<List<TabCate2>>> getTabCate2List(@Query("tab_id") int tab_id);


    @GET("live/cate/getLiveCate1List")
    Observable<BaseBean<LiveCate1List>> getLiveCate1List();

    @GET("live/cate/getLiveRecommendCate2")
    Observable<BaseBean<LiveCate2ByCate1>> getLiveRecommendCate2();

    //https://apiv2.douyucdn.cn/live/cate/getLiveCate2ByCate1?cate1_id=1&limit=12&offset=12&client_sys=android
    @GET("live/cate/getLiveCate2ByCate1")
    Observable<BaseBean<LiveCate2ByCate1>> getLiveCate2ByCate1(@Query("cate1_id") int cate1_id, @Query("limit") int limit, @Query("offset") int offset);



    //https://apiv2.douyucdn.cn/gv2api/rkc/roomlist/0_0/0/20/android?client_sys=android

    @GET("gv2api/rkc/roomlist/{level}_{cate_id}/{offset}/{limit}/android")
    Observable<BaseBean<LiveRoomList>> getRoomList(@Path("level") int level, @Path("cate_id") int cate_id, @Path("offset") int offset, @Path("limit") int limit);

    //    https://apiv2.douyucdn.cn/Live/Customcate2/getAllComponentList?cate2_id=350&client_sys=android
    @GET("Live/Customcate2/getAllComponentList")
    Observable<BaseBean<List<Component>>> getAllComponentList(@Query("cate2_id") int cate2_id);


    //    https://apiv2.douyucdn.cn/live/Slide/getSlideLists?cate_id=350&app_ver=10430002&client_sys=android
    public static final String APP_VER = "10430002";

    @GET("live/Slide/getSlideLists")
    Observable<BaseBean<SlideList>> getSlideLists(@Query("cate_id") int cate_id, @Query("app_ver") String app_ver);
    //    https://apiv2.douyucdn.cn/Live/Subactivity/getActivityList?cid2=181&client_sys=android
    @GET("Live/Subactivity/getActivityList")
    Observable<BaseBean<ActivityList>> getActivityList(@Query("cid2") int cid2);

    //    https://apiv2.douyucdn.cn/mgame/cate2promotion/getPromo?cate2_id=181&client_sys=android
    @GET("mgame/cate2promotion/getPromo")
    Observable<BaseBean<Promotion>> getPromo(@Query("cate2_id") int cate2_id);


}
