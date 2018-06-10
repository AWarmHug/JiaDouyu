package com.warm.livelive.douyu.data;


import com.warm.livelive.douyu.data.bean.HlsUrl;
import com.warm.livelive.douyu.data.bean.LiveRoom;
import com.warm.livelive.douyu.data.bean.SubChannel;
import com.warm.livelive.douyu.data.bean.TabCate;
import com.warm.livelive.douyu.data.http.HttpHelper;
import com.warm.livelive.douyu.data.http.HttpHelperImpl;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者：warm
 * 时间：2017-11-20 10:50
 * 描述：
 */

public class DataManager implements HttpHelper {

    private HttpHelperImpl mHttpHelper;
    public static final DataManager INSTANCE = new DataManager();

    public static DataManager newInstance() {
        return INSTANCE;
    }

    private DataManager() {
        this.mHttpHelper = HttpHelperImpl.newInstance();
    }

    @Override
    public Observable<List<TabCate>> getTabCate() {
        return mHttpHelper.getTabCate();
    }

    @Override
    public Observable<List<SubChannel>> getSubChannel(String tag) {
        return mHttpHelper.getSubChannel(tag);
    }

    @Override
    public Observable<List<LiveRoom>> getLiveRooms(String tagId, int page) {
        return mHttpHelper.getLiveRooms(tagId, page);
    }

    @Override
    public Observable<HlsUrl> getHlsUrl(String roomId) {
        return mHttpHelper.getHlsUrl(roomId);
    }
}
