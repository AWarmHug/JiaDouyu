package com.warm.livelive.douyu.data.http;


import com.warm.livelive.douyu.data.bean.Activity;
import com.warm.livelive.douyu.data.bean.BaseBean;
import com.warm.livelive.douyu.data.bean.Cate3;
import com.warm.livelive.douyu.data.bean.Component;
import com.warm.livelive.douyu.data.bean.HlsUrl;
import com.warm.livelive.douyu.data.bean.KeyWord;
import com.warm.livelive.douyu.data.bean.LiveRoom;
import com.warm.livelive.douyu.data.bean.Promotion;
import com.warm.livelive.douyu.data.bean.RtmpUrl;
import com.warm.livelive.douyu.data.bean.Slide;
import com.warm.livelive.douyu.data.bean.SubChannel;
import com.warm.livelive.douyu.data.bean.TabCate;
import com.warm.livelive.douyu.data.bean.TabCate1;
import com.warm.livelive.douyu.data.bean.TabCate2;
import com.warm.livelive.douyu.data.bean.live.LiveCate1;
import com.warm.livelive.douyu.data.bean.live.LiveCate2;
import com.warm.livelive.douyu.data.bean.live.LiveRoomItem;
import com.warm.livelive.douyu.data.bean.search.RecFavor;
import com.warm.livelive.douyu.data.bean.search.SearchData;
import com.warm.livelive.douyu.data.bean.search.Video;
import com.warm.livelive.douyu.data.http.api.apiv2.LiveApis;
import com.warm.livelive.douyu.data.http.api.capi.CApis;
import com.warm.livelive.douyu.data.http.retrofit.RetrofitHelper;
import com.warm.livelive.utils.Md5Util;
import com.warm.livelive.utils.rx.RxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

/**
 * 作者：warm
 * 时间：2017-11-20 10:50
 * 描述：
 */

public class HttpManager {


    private static final HttpManager INSTANCE = new HttpManager();

    private CApis mCApis;
    private LiveApis mLiveApis;

    public static HttpManager getInstance() {
        return INSTANCE;
    }

    private HttpManager() {
        mCApis = RetrofitHelper.provideApi(CApis.class);
        mLiveApis = RetrofitHelper.provideApiV2(LiveApis.class);

    }

    public Observable<List<TabCate>> getTabCate() {
        return mLiveApis.getTabCate().compose(RxUtils.<TabCate>handleListResult());
    }

    public Observable<List<SubChannel>> getSubChannel(String shortName) {

        return mCApis.getSubChannel(shortName,System.currentTimeMillis()/1000).compose(RxUtils.<List<SubChannel>>handleResult());
    }

    public Observable<List<LiveRoom>> getLiveRooms(String tagId, int page) {
        return mCApis.getLiveRooms(tagId, 20, page * 20).compose(RxUtils.<List<LiveRoom>>handleResult());
    }

    public Observable<HlsUrl> getHlsUrl(String roomId) {
        String url = "https://m.douyu.com/html5/live";
        return mCApis.getHlsUrl(url, roomId).compose(RxUtils.handleResult());
    }

    public Observable<RtmpUrl> getRtmpUrl(int roomId) {
//        String url2 = " https://playclient.douyucdn.cn/lapi/live/appGetPlayer/stream/" + roomId + "?token=&rate=-1&cdn=&txdw=0&hevc=0&cptl=0101&csign=9741dc5a78c5a1ad16c30e06906ae9c1&client_sys=android";
        String api_url = "http://www.douyutv.com/api/v1/";
        String args = String.format(Locale.CHINA, "room/%d?aid=wp&client_sys=wp&time=%d", roomId, System.currentTimeMillis());
        String auth_md5 = (args + "zNzMV1y4EMxOHS6I5WKm");
        String auth_str = Md5Util.strToMd5Low32(auth_md5);
        String url3 = String.format("%s%s&auth=%s", api_url, args, auth_str);

        return mLiveApis.getRtmpUrl(url3).compose(RxUtils.<RtmpUrl>handleResult());
    }

    public Observable<List<TabCate1>> getTabCate1List() {
        return mLiveApis.getTabCate1List().compose(RxUtils.handleListResult());
    }

    public Observable<List<TabCate2>> getTabCate2List(int tab_id) {
        return mLiveApis.getTabCate2List(tab_id).compose(RxUtils.handleResult())
                .map(tabCate2List -> {
                    if (tabCate2List.getCate2_list() != null) {
                        return tabCate2List.getCate2_list();
                    } else {
                        return new ArrayList<>();
                    }
                });
    }


    public Observable<List<LiveCate1>> getLiveCate1List() {
        return mLiveApis.getLiveCate1List()
                .compose(RxUtils.handleResult())
                .map(liveCate1List -> {
                    if (liveCate1List.getCate1_list() != null) {
                        return liveCate1List.getCate1_list();
                    } else {
                        return new ArrayList<>();
                    }
                });
    }

    public Observable<List<LiveCate2>> getLiveRecommendCate2() {
        return mLiveApis.getLiveRecommendCate2()
                .compose(RxUtils.handleResult())
                .map(liveCate2ByCate1 -> {
                    if (liveCate2ByCate1.getCate2_list() != null) {
                        return liveCate2ByCate1.getCate2_list();
                    } else {
                        return new ArrayList<>();
                    }
                });
    }

    public Observable<List<LiveCate2>> getLiveCate2ByCate1(int cate1_id, int limit, int offset) {
        return mLiveApis.getLiveCate2ByCate1(cate1_id, limit, offset)
                .compose(RxUtils.handleResult())
                .map(liveCate2ByCate1 -> {
                    if (liveCate2ByCate1.getCate2_list() != null) {
                        return liveCate2ByCate1.getCate2_list();
                    } else {
                        return new ArrayList<>();
                    }
                });
    }

    public Observable<List<LiveRoomItem>> getRoomList(int level, int cate_id, int offset, int limit) {
        return mLiveApis.getRoomList(level, cate_id, offset, limit)
                .compose(RxUtils.handleResult())
                .map(liveRoomList -> liveRoomList.getList() != null ? liveRoomList.getList() : new ArrayList<>());
    }

    public Observable<List<Component>> getAllComponentList(int cate2_id) {
        return mLiveApis.getAllComponentList(cate2_id)
                .compose(RxUtils.handleListResult());
    }

    public Observable<List<Slide>> getSlideLists(int cate_id) {
        return mLiveApis.getSlideLists(cate_id, LiveApis.APP_VER)
                .compose(RxUtils.handleResult())
                .map(slideList -> slideList.getCount() != 0 ? slideList.getSlide_list() : new ArrayList<>());
    }

    public Observable<List<Cate3>> getThreeCate(int tag_id) {
        return mCApis.getThreeCate(tag_id)
                .compose(RxUtils.handleListResult());
    }

    public Observable<List<Activity>> getActivityList(int cid2) {
        return mLiveApis.getActivityList(cid2)
                .compose(RxUtils.handleResult())
                .map(activityList -> activityList.getTotal() != 0 ? activityList.getList() : new ArrayList<>());
    }

    public Observable<Promotion> getPromo(int cate2_id) {
        return mLiveApis.getPromo(cate2_id)
                .compose(RxUtils.handleResult());
    }

    public Observable<List<LiveRoomItem>> getSportLiveRoom(int offset, int limit) {
        return mCApis.getSportLiveRoom(offset, limit, System.currentTimeMillis() / 1000)
                .compose(RxUtils.handleListResult());
    }

    public Observable<List<KeyWord>> getTodayHotKeyWord() {
        return mLiveApis.getTodayHot("").compose(RxUtils.handleListResult());
    }

    public Observable<List<RecFavor>> getRecFavor() {
        return mLiveApis.getRecFavor("").compose(RxUtils.handleListResult());
    }


    public Observable<List<Video>> getVideoSearchData(String sk, int sort, int offset, int limit) {
        return mLiveApis.getVideoSearchData(sk, sort, offset, limit)
                .compose(RxUtils.handleResult())
                .map(searchData -> searchData.getVideo());
    }

    public Observable<SearchData> mobileSearch(int sort1, int sort2, String sk, int offset, int limit) {
        Observable<BaseBean<SearchData>> observable;
        if (sort1 == CApis.SORT1_VIDEO) {
            observable = mLiveApis.getVideoSearchData(sk, sort2, offset, limit);
        } else {
            observable = mCApis.mobileSearch(sort1, sort2, sk, offset, limit);
        }
        return observable.compose(RxUtils.handleResult());
    }


}
