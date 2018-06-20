package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.BasePresenter;
import com.warm.livelive.base.BaseView;
import com.warm.livelive.douyu.data.bean.live.LiveCate1;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-11 09:56
 * 描述：
 */
public interface LiveTypeContract {


    interface Presenter extends BasePresenter<View> {

        void getLiveCate1List();

    }

    interface View extends BaseView {

        void showLiveCate1List(List<LiveCate1> liveCate1s);

    }

}
