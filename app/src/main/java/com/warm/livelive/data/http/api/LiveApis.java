package com.warm.livelive.data.http.api;

import com.warm.livelive.data.bean.BaseBean;
import com.warm.livelive.data.bean.LiveRoom;
import com.warm.livelive.data.bean.SubChannel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 作者：warm
 * 时间：2017-12-25 16:37
 * 描述：
 */

public interface LiveApis {

    @GET("getColumnDetail")
    Observable<BaseBean<List<SubChannel>>> getSubChannel(@Query("shortName") String shortName);

    @GET("live/{tagId}")
    Observable<BaseBean<List<LiveRoom>>> getLiveRooms(@Path("tagId") String tagId, @Query("limit") int limit,@Query("offset") int offset);

}
