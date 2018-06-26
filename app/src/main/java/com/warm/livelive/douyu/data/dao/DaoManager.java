package com.warm.livelive.douyu.data.dao;

import com.warm.livelive.douyu.data.bean.dao.HistoryKeyWord;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 作者：warm
 * 时间：2018-06-22 10:58
 * 描述：
 */
public class DaoManager {
    private static final DaoManager INSTANCE = new DaoManager();

    private DaoManager() {
    }

    public static DaoManager getInstance() {
        return INSTANCE;
    }

    public Observable<List<HistoryKeyWord>> getKeyWord(){
        return Observable.just(new ArrayList<>());
    }

}
