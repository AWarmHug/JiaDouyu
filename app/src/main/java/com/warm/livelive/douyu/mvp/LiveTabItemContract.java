package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.BasePresenter;
import com.warm.livelive.base.BaseView;
import com.warm.livelive.douyu.data.bean.Activity;
import com.warm.livelive.douyu.data.bean.Cate3;
import com.warm.livelive.douyu.data.bean.Component;
import com.warm.livelive.douyu.data.bean.Promotion;
import com.warm.livelive.douyu.data.bean.Slide;
import com.warm.livelive.douyu.data.bean.TabCate2;
import com.warm.livelive.douyu.data.bean.live.LiveRoomItem;
import com.warm.livelive.error.KnownException;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-13 11:53
 * 描述：
 */
public interface LiveTabItemContract {

    //一个Presenter对应多个View
    interface Presenter extends BasePresenter<TabItemView> {
        void getAllComponentList(int cate2_id);
        void getPromo(int cate2_id);
        void getThreeCate(int cate2_id);
        void getTabCate2List(int tab_id);
        void getActivityList(int cid2);
        void attachListView(ListView view);
        void getRoomList(int level, int cate_id,int offset, int limit);
    }


    interface TabItemView extends BaseView {
        void showAllComponentList(List<Component> components);
        void showSlideLists(List<Slide> slides);
        void showPromotions(Promotion promotion);
        void showTabCate2List(List<TabCate2> tabCate2s);
        void showThreeCate(List<Cate3> cate3s);
        void showActivityList(List<Activity> activities);

    }


    interface ListView extends BaseView {
        void showRoomList(List<LiveRoomItem> liveRoomItems);
        void showError(KnownException exception);
    }
}
