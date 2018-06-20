package com.warm.livelive.eyepetizer.data;

import com.warm.livelive.eyepetizer.data.bean.PageData;
import com.warm.livelive.eyepetizer.data.bean.TabInfo;
import com.warm.livelive.eyepetizer.data.http.api.Api;
import com.warm.livelive.eyepetizer.data.http.retrofit.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 作者：warm
 * 时间：2018-06-19 10:29
 * 描述：
 */
public class DataManager {
    private Api api;

    private static DataManager INSTANCE = new DataManager();

    public static DataManager getInstance() {
        return INSTANCE;
    }

    private DataManager() {
        api = RetrofitHelper.provideApi(Api.class);
    }

    public Observable<PageData> getPageData(String url, int page) {
        return api.getPageData(url, page);
    }

    public Observable<TabInfo.TabInfoBean> loadData() {
        return api.loadData().map(new Function<TabInfo, TabInfo.TabInfoBean>() {
            @Override
            public TabInfo.TabInfoBean apply(TabInfo tabInfo) throws Exception {
                return tabInfo.getTabInfo();
            }
        });
    }

}
