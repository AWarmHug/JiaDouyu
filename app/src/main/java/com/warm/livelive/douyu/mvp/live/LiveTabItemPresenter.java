package com.warm.livelive.douyu.mvp.live;

import com.warm.livelive.LiveApp;
import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.douyu.data.DataManager;
import com.warm.livelive.douyu.data.bean.douyu.Cate3;
import com.warm.livelive.douyu.data.bean.douyu.Component;
import com.warm.livelive.douyu.data.bean.douyu.Promotion;
import com.warm.livelive.douyu.data.bean.douyu.Slide;
import com.warm.livelive.douyu.data.bean.douyu.TabCate2;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveRoomItem;
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

import static com.warm.livelive.douyu.mvp.live.LiveTabItemContract.ListView;
import static com.warm.livelive.douyu.mvp.live.LiveTabItemContract.Presenter;
import static com.warm.livelive.douyu.mvp.live.LiveTabItemContract.TabItemView;

/**
 * 作者：warm
 * 时间：2018-06-13 13:16
 * 描述：
 */
public class LiveTabItemPresenter extends RxPresenter<TabItemView> implements Presenter {
    private TabItemView mTabView;
    private ListView mListView;
    private DataManager mDataManager;

    public LiveTabItemPresenter() {
        mDataManager = LiveApp.getInstance().getDataManager();
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

        Disposable disposable = mDataManager.getAllComponentList(cate2_id)
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
        Disposable disposable = mDataManager.getPromo(cate2_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<Promotion, ObservableSource<Promotion>>) promotion -> {
                    mTabView.showPromotions(promotion);
                    return Observable.just(promotion);
                })
                .observeOn(Schedulers.io())
                .flatMap((Function<Promotion, ObservableSource<List<Slide>>>) promotion -> {
                    if (promotion == null || promotion.getId() == 0) {
                        return mDataManager.getSlideLists(cate2_id);
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

        Disposable disposable = mDataManager.getThreeCate(cate2_id)
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
        Disposable disposable = mDataManager.getTabCate2List(tab_id)
                .map(tabCate2s -> {
                    List<Cate3> cate3s = new ArrayList<>(tabCate2s.size());
                    for (TabCate2 tabCate2 : tabCate2s) {
                        Cate3 cate3 = new Cate3();
                        cate3.setCate_id(tabCate2.getCate_id());
                        cate3.setName(tabCate2.getCate2_name());
                    }
                    return cate3s;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cate3s -> mTabView.showThreeCate(cate3s), new ThrowableConsumer(mTabView));
        addDisposable(disposable);
    }

    @Override
    public void getActivityList(int cid2) {
        Disposable disposable = mDataManager.getActivityList(cid2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(activities -> mTabView.showActivityList(activities), new ThrowableConsumer(mTabView));
        addDisposable(disposable);
    }

    @Override
    public void getRoomList(int level, int cate_id, int offset, int limit) {
        Observable<List<LiveRoomItem>> observable;
        if (cate_id == -3) {
            observable = mDataManager.getSportLiveRoom(offset, limit);
        } else {
            observable = mDataManager
                    .getRoomList(level, cate_id, offset, limit);
        }
        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveRoomItems -> CheckUtil.checkNotNull(mListView).showRoomList(liveRoomItems)
                        , new ThrowableConsumer(mListView));
        addDisposable(disposable);
    }
}
