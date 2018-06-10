package com.warm.livelive.douyu.data.http;

import com.warm.livelive.douyu.data.bean.HlsUrl;
import com.warm.livelive.douyu.data.bean.LiveRoom;
import com.warm.livelive.douyu.data.bean.SubChannel;
import com.warm.livelive.douyu.data.bean.TabCate;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者：warm
 * 时间：2017-12-25 17:11
 * 描述：
 */

public interface HttpHelper {

    Observable<List<TabCate>> getTabCate();

    Observable<List<SubChannel>> getSubChannel(String shortName);

    Observable<List<LiveRoom>> getLiveRooms(String tagId, int page);

    Observable<HlsUrl> getHlsUrl(String roomId);


}
