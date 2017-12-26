package com.warm.livelive.data.http;

import com.warm.livelive.data.bean.LiveRoom;
import com.warm.livelive.data.bean.SubChannel;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者：warm
 * 时间：2017-12-25 17:11
 * 描述：
 */

public interface HttpHelper {
    Observable<List<SubChannel>> getSubChannel(String shortName);

    Observable<List<LiveRoom>> getLiveRooms(String tagId, int page);


}
