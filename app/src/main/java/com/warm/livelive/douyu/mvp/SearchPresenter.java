package com.warm.livelive.douyu.mvp;

import com.warm.livelive.MyApp;
import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.douyu.data.dao.DaoManager;
import com.warm.livelive.douyu.data.http.HttpManager;
import com.warm.livelive.utils.rx.RxUtils;
import com.warm.livelive.utils.rx.ThrowableConsumer;

import io.reactivex.disposables.Disposable;

/**
 * 作者：warm
 * 时间：2018-06-22 09:53
 * 描述：
 */
public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.Presenter {
    private SearchContract.View mView;
    private SearchContract.ResultView mResultView;
    private HttpManager mHttpManager;
    private DaoManager mDaoManager;

    public SearchPresenter() {
        mHttpManager = MyApp.getInstance().getHttpManager();
        mDaoManager = MyApp.getInstance().getDaoManager();

    }

    @Override
    public void attach(SearchContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void attachResultView(SearchContract.ResultView resultView) {
        mResultView = resultView;
    }


    @Override
    public void getSearchRecAndHot() {

        Disposable disposable = mDaoManager.getKeyWord()
                .compose(RxUtils.ioToMain())
                .subscribe(historyKeyWords -> mView.showHistory(historyKeyWords)
                        , new ThrowableConsumer(mView));
        addDisposable(disposable);

        Disposable disposable2 =  mHttpManager.getTodayHotKeyWord()
                .compose(RxUtils.ioToMain())
                .subscribe(keyWords -> mView.showHot(keyWords)
                        , new ThrowableConsumer(mView));
        addDisposable(disposable2);

        Disposable disposable3 =  mHttpManager.getRecFavor()
                .compose(RxUtils.ioToMain())
                .subscribe(recFavors -> mView.showRecFavor(recFavors)
                        , new ThrowableConsumer(mView));
        addDisposable(disposable3);

    }

    @Override
    public void mobileSearch(int sort1, int sort2, String sk, int offset, int limit) {

        Disposable disposable = mHttpManager.mobileSearch(sort1, sort2, sk, offset, limit)
                .compose(RxUtils.ioToMain())
                .subscribe(searchData -> mResultView.showSearchResult(searchData)
                        , new ThrowableConsumer(mResultView));
        addDisposable(disposable);
    }


    @Override
    public void detach() {
        super.detach();
        mView = null;
        mResultView = null;

    }
}
