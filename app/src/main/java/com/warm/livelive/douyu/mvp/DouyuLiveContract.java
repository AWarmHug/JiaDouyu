package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.BasePresenter;
import com.warm.livelive.base.BaseView;
import com.warm.livelive.douyu.data.bean.KeyWord;
import com.warm.livelive.douyu.data.bean.TabCate1;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-21 14:41
 * 描述：
 */
public interface DouyuLiveContract {

    interface Presenter extends BasePresenter<View>{
        void getTabCate1List();
        void getTodayHot();
    }

    interface View extends BaseView{
        void getTabCate1List(List<TabCate1> tabCate1s);

        void showTodayHot(KeyWord keyWords);

    }
}
