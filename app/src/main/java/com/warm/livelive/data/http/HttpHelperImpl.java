package com.warm.livelive.data.http;

import com.warm.livelive.data.bean.HlsUrl;
import com.warm.livelive.data.bean.LiveRoom;
import com.warm.livelive.data.bean.SubChannel;
import com.warm.livelive.data.bean.TabCate;
import com.warm.livelive.data.http.api.LiveApis;
import com.warm.livelive.data.http.api.apiv2.Api;
import com.warm.livelive.data.http.retrofit.RetrofitHelper;
import com.warm.livelive.utils.rx.RxUtils;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者：warm
 * 时间：2017-12-25 17:11
 * 描述：
 */

public class HttpHelperImpl implements HttpHelper {

    private static final HttpHelperImpl ourInstance = new HttpHelperImpl();

    public static HttpHelperImpl newInstance() {
        return ourInstance;
    }

    private LiveApis mLiveApis;
    private Api mApi;


    public HttpHelperImpl() {
        mLiveApis = RetrofitHelper.provideApi(LiveApis.class);
        mApi=RetrofitHelper.provideApiV2(Api.class);
    }

    @Override
    public Observable<List<TabCate>> getTabCate() {
        return mApi.getTabCate().compose(RxUtils.<TabCate>handleListResult());
    }

    @Override
    public Observable<List<SubChannel>> getSubChannel(String shortName) {
        return mLiveApis.getSubChannel(shortName).compose(RxUtils.<List<SubChannel>>handleResult());
    }

    @Override
    public Observable<List<LiveRoom>> getLiveRooms(String tagId, int page) {
        return mLiveApis.getLiveRooms(tagId, 20, page * 20).compose(RxUtils.<List<LiveRoom>>handleResult());
    }

    @Override
    public Observable<HlsUrl> getHlsUrl( String roomId) {
        String url="https://m.douyu.com/html5/live";
        return mLiveApis.getHlsUrl(url,roomId).compose(RxUtils.<HlsUrl>handleResult());
    }
}
