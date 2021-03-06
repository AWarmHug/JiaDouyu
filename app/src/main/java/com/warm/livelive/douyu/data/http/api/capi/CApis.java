package com.warm.livelive.douyu.data.http.api.capi;

import com.warm.livelive.douyu.data.bean.BaseBean;
import com.warm.livelive.douyu.data.bean.Cate3;
import com.warm.livelive.douyu.data.bean.HlsUrl;
import com.warm.livelive.douyu.data.bean.LiveRoom;
import com.warm.livelive.douyu.data.bean.SubChannel;
import com.warm.livelive.douyu.data.bean.live.LiveRoomItem;
import com.warm.livelive.douyu.data.bean.search.SearchData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 作者：warm
 * 时间：2017-12-25 16:37
 * 描述：
 */

public interface CApis {

//      获取所有的游戏
//    http://open.douyucdn.cn/api/RoomApi/game


    /**
     * @param shortName 游戏game、客厅游戏ktyx、手机游戏sjyx、颜值yz、科技kj、娱乐yl、文娱课堂wykt、正能量znl、
     * @return
     */
    @GET("getColumnDetail")
    Observable<BaseBean<List<SubChannel>>> getSubChannel(@Query("shortName") String shortName,@Query("time") long time);


    /**
     * 由{@link #getSubChannel(String)}
     *
     * @param tagId  {@link SubChannel#tag_id}
     * @param limit
     * @param offset
     * @return
     */
    @GET("live/{tagId}")
    Observable<BaseBean<List<LiveRoom>>> getLiveRooms(@Path("tagId") String tagId, @Query("limit") int limit, @Query("offset") int offset);


    @GET("searchNew")
    Observable<BaseBean<List<LiveRoom>>> getSearchNew();

    @GET
    Observable<BaseBean<HlsUrl>> getHlsUrl(@Url String url, @Query("roomId") String roomId);

    @Headers({
            "user-agent: Mozilla/5.0 (iPad; CPU OS 8_1_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12B466 Safari/600.1.4"
    })

    //    https://capi.douyucdn.cn/api/v1/getThreeCate?tag_id=350&client_sys=android
    @GET("api/v1/getThreeCate")
    Observable<BaseBean<List<Cate3>>> getThreeCate(@Query("tag_id") int tag_id);

    //获取体育直播
//    https://capi.douyucdn.cn/api/v1/qie?offset=0&limit=20&aid=android1&time=1529034473&client_sys=android

    @GET("api/v1/qie")
    Observable<BaseBean<List<LiveRoomItem>>> getSportLiveRoom(@Query("offset") int offset, @Query("limit") int limit, @Query("time") long time);

    //    https://capi.douyucdn.cn/api/v1/mobileSearch/1/1?sk=skt&offset=0&limit=20&client_sys=android
    public static final int SORT1_ALL=1;
    public static final int SORT1_LIVE=2;
    public static final int SORT1_VIDEO=3;
    public static final int SORT1_ANCHOR=4;

    @GET("/api/v1/mobileSearch/{sort1}/{sort2}")
    Observable<BaseBean<SearchData>> mobileSearch(@Path("sort1") int sort1, @Path("sort2") int sort2, @Query("sk") String sk, @Query("offset") int offset, @Query("limit") int limit);


}
