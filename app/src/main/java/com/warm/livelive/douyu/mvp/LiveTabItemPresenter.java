package com.warm.livelive.douyu.mvp;

import com.warm.livelive.MyApp;
import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.douyu.data.http.HttpManager;
import com.warm.livelive.douyu.data.bean.Cate3;
import com.warm.livelive.douyu.data.bean.Component;
import com.warm.livelive.douyu.data.bean.Promotion;
import com.warm.livelive.douyu.data.bean.Slide;
import com.warm.livelive.douyu.data.bean.live.LiveRoomItem;
import com.warm.livelive.error.KnownException;
import com.warm.livelive.utils.CheckUtil;
import com.warm.livelive.utils.rx.ThrowableConsumer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.warm.livelive.douyu.mvp.LiveTabItemContract.ListView;
import static com.warm.livelive.douyu.mvp.LiveTabItemContract.Presenter;
import static com.warm.livelive.douyu.mvp.LiveTabItemContract.TabItemView;

/**
 * 作者：warm
 * 时间：2018-06-13 13:16
 * 描述：
 */
public class LiveTabItemPresenter extends RxPresenter<TabItemView> implements Presenter {
    private TabItemView mTabView;
    private ListView mListView;
    private HttpManager mHttpManager;

    public LiveTabItemPresenter() {
        mHttpManager = MyApp.getInstance().getHttpManager();
    }

    @Override
    public void attach(TabItemView view) {
        this.mTabView = view;
    }

    @Override
    public void attachListView(ListView view) {
        this.mListView = view;
    }


    @Override
    public void getAllComponentList(int cate2_id) {

        Disposable disposable = mHttpManager.getAllComponentList(cate2_id)
                .map(components -> {
                    components.add(0, Component.LIVE);
                    List<Component> components1 = new ArrayList<>();
                    for (Component item : components) {
                        if (item.getIs_tab() == 1 && !components1.contains(item)) {
                            components1.add(item);
                        }
                    }
                    return components1;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(components -> mTabView.showAllComponentList(components), new ThrowableConsumer(mTabView));
        addDisposable(disposable);
    }

    @Override
    public void getPromo(int cate2_id) {
        Disposable disposable = mHttpManager.getPromo(cate2_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<Promotion, ObservableSource<Promotion>>) promotion -> {
                    mTabView.showPromotions(promotion);
                    return Observable.just(promotion);
                })
                .observeOn(Schedulers.io())
                .flatMap((Function<Promotion, ObservableSource<List<Slide>>>) promotion -> {
                    if (promotion == null || promotion.getId() == 0) {
                        return mHttpManager.getSlideLists(cate2_id);
                    } else {
                        return Observable.just(new ArrayList<>());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(slides -> mTabView.showSlideLists(slides), new ThrowableConsumer(mTabView));
        addDisposable(disposable);
    }

    @Override
    public void getThreeCate(int cate2_id) {

        Disposable disposable = mHttpManager.getThreeCate(cate2_id)
                .map(new Function<List<Cate3>, List<Cate3>>() {
                    @Override
                    public List<Cate3> apply(List<Cate3> cate3s) throws Exception {
                        cate3s.add(0, Cate3.ALL);
                        return cate3s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cate3s -> mTabView.showThreeCate(cate3s), new ThrowableConsumer(mTabView));
        addDisposable(disposable);
    }

    @Override
    public void getTabCate2List(int tab_id) {
        Disposable disposable = mHttpManager.getTabCate2List(tab_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cate3s -> mTabView.showTabCate2List(cate3s), new ThrowableConsumer(mTabView));
        addDisposable(disposable);
    }

    @Override
    public void getActivityList(int cid2) {
        Disposable disposable = mHttpManager.getActivityList(cid2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(activities -> mTabView.showActivityList(activities), new ThrowableConsumer(mTabView));
        addDisposable(disposable);
    }

    @Override
    public void getRoomList(int level, int cate_id, int offset, int limit) {
        Observable<List<LiveRoomItem>> observable;
        if (cate_id == -3) {
            observable = mHttpManager.getSportLiveRoom(offset, limit);
        } else {
            observable = mHttpManager
                    .getRoomList(level, cate_id, offset, limit);
        }
        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveRoomItems -> CheckUtil.checkNotNull(mListView).showRoomList(liveRoomItems)
                        , new ThrowableConsumer(mListView){
                            @Override
                            public void handle(KnownException exception) {
//                                super.handle(exception);
                                mListView.showError(exception);
                            }
                        });
        addDisposable(disposable);
    }
}
