package com.warm.livelive.mvp;

import com.warm.livelive.base.BaseView;
import com.warm.livelive.base.IBasePresenter;

/**
 * 作者：warm
 * 时间：2017-12-27 13:02
 * 描述：
 */

public interface PlayerContract {
    interface View extends BaseView{

    }

    interface Presenter extends IBasePresenter<View> {

        void loadPrepare(String roomId,String groupId);

    }
}
