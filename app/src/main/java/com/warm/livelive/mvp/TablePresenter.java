package com.warm.livelive.mvp;

import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.data.bean.SubChannel;
import com.warm.livelive.utils.rx.CustomObserver;
import com.warm.livelive.utils.rx.RxUtils;

import java.util.List;

;

/**
 * 作者：warm
 * 时间：2018-01-02 16:58
 * 描述：
 */

public class TablePresenter extends RxPresenter<TableContract.View> implements TableContract.Presenter {

    @Override
    public void getSubChannel(@TableContract.Tag final String tag) {
        mDataManager.getSubChannel(tag)
                .compose(RxUtils.<List<SubChannel>>ioToMain(mView))
                .subscribe(new CustomObserver<List<SubChannel>>(mCompositeDisposable, mView) {
                    @Override
                    public void onNext(List<SubChannel> subChannels) {
                        mView.getSubChannel(subChannels);
                    }
                });
    }
}
