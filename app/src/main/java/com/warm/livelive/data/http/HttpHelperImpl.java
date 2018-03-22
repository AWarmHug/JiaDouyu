package com.warm.livelive.data.http;

import com.warm.livelive.Md5;
import com.warm.livelive.data.bean.HlsUrl;
import com.warm.livelive.data.bean.LiveRoom;
import com.warm.livelive.data.bean.RtmpUrl;
import com.warm.livelive.data.bean.SubChannel;
import com.warm.livelive.data.bean.TabCate;
import com.warm.livelive.data.http.api.LiveApis;
import com.warm.livelive.data.http.api.apiv2.Api;
import com.warm.livelive.data.http.retrofit.RetrofitHelper;
import com.warm.livelive.utils.rx.RxUtils;

import java.util.List;
import java.util.Locale;

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
        mApi = RetrofitHelper.provideApiV2(Api.class);
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
    public Observable<HlsUrl> getHlsUrl(String roomId) {
//        https://playclient.douyucdn.cn/lapi/live/appGetPlayer/stream/2313688?token=&rate=-1&cdn=&txdw=0&hevc=0&cptl=0101&csign=9741dc5a78c5a1ad16c30e06906ae9c1&client_sys=android
        String url = "https://m.douyu.com/html5/live";
        return mLiveApis.getHlsUrl(url, roomId).compose(RxUtils.<HlsUrl>handleResult());
    }

    @Override
    public Observable<RtmpUrl> getRtmpUrl(String roomId) {
//        String url2 = " https://playclient.douyucdn.cn/lapi/live/appGetPlayer/stream/" + roomId + "?token=&rate=-1&cdn=&txdw=0&hevc=0&cptl=0101&csign=9741dc5a78c5a1ad16c30e06906ae9c1&client_sys=android";

        String api_url = "http://www.douyutv.com/api/v1/";
        String args = String.format(Locale.CHINA,"room/%s?aid=wp&client_sys=wp&time=%d", roomId, System.currentTimeMillis());
        String auth_md5 = (args + "zNzMV1y4EMxOHS6I5WKm");
        String  auth_str = Md5.strToMd5Low32(auth_md5);
        String url3 = String.format("%s%s&auth=%s",api_url, args, auth_str);

        return mLiveApis.getRtmpUrl(url3).compose(RxUtils.<RtmpUrl>handleResult());
    }
}
