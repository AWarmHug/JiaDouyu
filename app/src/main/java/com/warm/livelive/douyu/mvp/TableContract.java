package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.BaseView;
import com.warm.livelive.base.IBasePresenter;
import com.warm.livelive.douyu.data.bean.SubChannel;
import com.warm.livelive.douyu.data.bean.TabCate;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-01-02 16:58
 * 描述：
 */

public interface TableContract {

    interface View extends BaseView {

        void getTabCate(List<TabCate> tabCates);

        void getSubChannel(List<SubChannel> subChannels);

    }

    interface Presenter extends IBasePresenter<View> {

        void getTabCate();

        void getSubChannel(String tag);

    }


}
