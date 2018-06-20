package com.warm.livelive.douyu.mvp;

import com.warm.livelive.LiveApp;
import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.douyu.data.DataManager;
import com.warm.livelive.douyu.data.bean.live.LiveCate1;
import com.warm.livelive.douyu.data.bean.live.LiveCate2;
import com.warm.livelive.utils.rx.ThrowableConsumer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：warm
 * 时间：2018-06-11 09:55
 * 描述：
 */
public class LiveTypePresenter extends RxPresenter<LiveTypeContract.View> implements LiveTypeContract.Presenter {

    private DataManager mDataManager;
    private LiveTypeContract.View mView;

    public LiveTypePresenter() {
        mDataManager = LiveApp.getInstance().getDataManager();
    }

    @Override
    public void attach(LiveTypeContract.View view) {
        this.mView = view;
    }

    @Override
    public void getLiveCate1List() {

        Disposable disposable = Observable
                .zip(mDataManager.getLiveCate1List()
                        , mDataManager.getLiveRecommendCate2()
                                .map(liveCate2s -> {
                                    List<LiveCate2> newList = new ArrayList<>();
                                    for (int i = 0; i < 8; i++) {
                                        newList.add(liveCate2s.get(i));
                                    }
                                    return newList;
                                })
                        , (liveCate1s, liveCate2s) -> {

                            LiveCate1 liveCate1 = new LiveCate1();
                            liveCate1.setCate1_id(0);
                            liveCate1.setCate_name("推荐分类");
                            liveCate1.setCate2_count(liveCate2s.size());
                            liveCate1.setCate2_list(liveCate2s);
                            liveCate1s.add(0, liveCate1);
                            return liveCate1s;
                        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveCate1s -> {
                    if (mView != null) {
                        mView.showLiveCate1List(liveCate1s);
                    }
                }, new ThrowableConsumer(mView));
        addDisposable(disposable);
    }

    @Override
    public void detach() {
        super.detach();
        mView = null;
    }
}
