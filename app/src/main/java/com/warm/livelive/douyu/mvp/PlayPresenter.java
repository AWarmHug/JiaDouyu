package com.warm.livelive.douyu.mvp;

import android.util.Log;

import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.douyu.config.DouyuConfig;
import com.warm.livelive.douyu.data.DataManager;
import com.warm.livelive.douyu.data.bean.RtmpUrl;
import com.warm.livelive.douyu.data.bean.live.LiveRoomItem;
import com.warm.livelive.douyu.data.socket.netty.Douyu;
import com.warm.livelive.douyu.data.socket.netty.NettyClient;
import com.warm.livelive.douyu.data.socket.netty.OnLoadListener;
import com.warm.livelive.error.KnownException;
import com.warm.livelive.utils.rx.RxUtils;
import com.warm.livelive.utils.rx.ThrowableConsumer;

import java.net.InetSocketAddress;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 作者：warm
 * 时间：2018-06-19 17:46
 * 描述：
 */
public class PlayPresenter extends RxPresenter<PlayContract.View> implements PlayContract.Presenter {
    private static final String TAG = "PlayPresenter";
    private DataManager mDataManager;
    private PlayContract.View mView;
    private NettyClient mClient;

    public PlayPresenter() {
        mDataManager = DataManager.getInstance();
        mClient = NettyClient.getInstance();
    }

    @Override
    public void playPrepare(LiveRoomItem item) {

        Disposable disposable = mDataManager.getRtmpUrl(item.getRoom_id())
                .compose(RxUtils.ioToMain())
                .subscribe(new Consumer<RtmpUrl>() {
                    @Override
                    public void accept(RtmpUrl rtmpUrl) throws Exception {
                        if (mView != null) {
                            mView.playPrepared(rtmpUrl);
                            loadDanmu(rtmpUrl);
                        }
                    }
                }, new ThrowableConsumer(mView));
        addDisposable(disposable);


    }

    @Override
    public void loadDanmu(RtmpUrl rtmpUrl) {
        Disposable disposable = Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        InetSocketAddress inetSocketAddress = new InetSocketAddress(DouyuConfig.DANMU_HOST, DouyuConfig.DANMU_PORT);

                        mClient.startConnect(inetSocketAddress, new OnLoadListener() {
                            @Override
                            public void onLoading() {
                                Log.d(TAG, "onLoading: ");
                            }

                            @Override
                            public void onError(KnownException e) {
                                Log.d(TAG, "onError: " + e.getMessage());
                            }

                            @Override
                            public void onSuccess() {
                                Log.d(TAG, "onSuccess: ");
                                mClient.send(Douyu.getInstance().loadRoom(String.valueOf(rtmpUrl.getRoom_id())));
                                mClient.send(Douyu.getInstance().loadGroup(String.valueOf(rtmpUrl.getRoom_id()), "-9999"));
                            }

                            @Override
                            public void onMessage(String type, String msg) {
                                e.onNext(new String[]{type, msg});
                            }
                        });
                    }
                })
                .compose(RxUtils.ioToMain())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        String[] strings = (String[]) o;
                        mView.showDanmu(strings[0], strings[1]);

                    }
                }, new ThrowableConsumer(mView));
        addDisposable(disposable);
    }

    @Override
    public void attach(PlayContract.View view) {
        this.mView = view;
    }

    @Override
    public void detach() {
        super.detach();
        mClient.send(Douyu.getInstance().loginOut());
//        mClient.endConnect();
        mView = null;
    }
}
