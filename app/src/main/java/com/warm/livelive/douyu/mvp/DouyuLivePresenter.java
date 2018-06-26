package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.douyu.data.http.HttpManager;
import com.warm.livelive.douyu.data.bean.KeyWord;
import com.warm.livelive.douyu.data.bean.TabCate1;
import com.warm.livelive.utils.rx.RxUtils;
import com.warm.livelive.utils.rx.ThrowableConsumer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：warm
 * 时间：2018-06-21 14:48
 * 描述：
 */
public class DouyuLivePresenter extends RxPresenter<DouyuLiveContract.View> implements DouyuLiveContract.Presenter {

    private HttpManager mHttpManager;
    private DouyuLiveContract.View mView;

    public DouyuLivePresenter() {
        mHttpManager = HttpManager.getInstance();
    }

    @Override
    public void getTabCate1List() {
        Disposable disposable = mHttpManager.getTabCate1List()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(tabCate1s -> {
                    tabCate1s.add(0, TabCate1.TYPE);
                    tabCate1s.add(1, TabCate1.ALL);
                    tabCate1s.add(tabCate1s.size(), TabCate1.SPORT);
                    return tabCate1s;
                })
                .subscribe(tabCate1s -> {
                    mView.getTabCate1List(tabCate1s);
                }, new ThrowableConsumer(mView));
        addDisposable(disposable);
    }

    @Override
    public void getTodayHot() {
        Disposable disposable = mHttpManager.getTodayHotKeyWord()
                .filter(new Predicate<List<KeyWord>>() {
                    @Override
                    public boolean test(List<KeyWord> keyWords) throws Exception {
                        return keyWords.size() != 0;
                    }
                })
                .flatMap(new Function<List<KeyWord>, ObservableSource<KeyWord>>() {
                    @Override
                    public ObservableSource<KeyWord> apply(List<KeyWord> keyWords) throws Exception {
                        return Observable.interval(0, 3, TimeUnit.SECONDS)
                                .map(new Function<Long, KeyWord>() {
                                    @Override
                                    public KeyWord apply(Long aLong) throws Exception {
                                        int position = (int) (aLong % keyWords.size());
                                        return keyWords.get(position);
                                    }
                                });
                    }
                })
                .compose(RxUtils.ioToMain())
                .subscribe(new Consumer<KeyWord>() {
                    @Override
                    public void accept(KeyWord keyWord) throws Exception {
                        mView.showTodayHot(keyWord);
                    }
                }, new ThrowableConsumer(mView));

        addDisposable(disposable);

    }

    @Override
    public void attach(DouyuLiveContract.View view) {
        mView = view;
    }
}
