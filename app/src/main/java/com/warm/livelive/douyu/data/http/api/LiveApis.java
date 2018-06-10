package com.warm.livelive.douyu.data.http.api;

import com.warm.livelive.douyu.data.bean.BaseBean;
import com.warm.livelive.douyu.data.bean.HlsUrl;
import com.warm.livelive.douyu.data.bean.LiveRoom;
import com.warm.livelive.douyu.data.bean.SubChannel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 作者：warm
 * 时间：2017-12-25 16:37
 * 描述：
 */

public interface LiveApis {

//      获取所有的游戏
//    http://open.douyucdn.cn/api/RoomApi/game


    /**
     * @param shortName 游戏game、客厅游戏ktyx、手机游戏sjyx、颜值yz、科技kj、娱乐yl、文娱课堂wykt、正能量znl、
     * @return
     */
    @GET("getColumnDetail")
    Observable<BaseBean<List<SubChannel>>> getSubChannel(@Query("shortName") String shortName);

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


}
