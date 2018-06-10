package com.warm.livelive.utils.rx;


import com.warm.livelive.base.BaseView;
import com.warm.livelive.douyu.data.bean.BaseBean;
import com.warm.livelive.error.CustomException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者: 51hs_android
 * 时间: 2017/7/27
 * 简介:
 */

public class RxUtils {


    public static <T> ObservableTransformer<T, T> ioToMain() {
        return ioToMain(null);
    }


    /**
     * 处理线程调度,在加载开始和成功时，会自动进行加载动画，用于操作时试用，比如点击某个按钮进行操作，
     */
    public static <T> ObservableTransformer<T, T> ioToMain(final BaseView view) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                if (view != null) {
                    return upstream.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(new Consumer<Disposable>() {
                                @Override
                                public void accept(@NonNull Disposable disposable) throws Exception {
                                    view.onShowLoad();
                                }
                            })
                            .doOnComplete(new Action() {
                                @Override
                                public void run() throws Exception {
                                    view.onDismissLoad();
                                }
                            });
                } else {
                    return upstream
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            }
        };
    }


    /**
     * 处理错误码相关内容
     *
     * @param <Bean>
     * @return ObservableTransformer
     */
    public static <Bean> ObservableTransformer<BaseBean<Bean>, Bean> handleResult() {

        return new ObservableTransformer<BaseBean<Bean>, Bean>() {
            @Override
            public ObservableSource<Bean> apply(@NonNull Observable<BaseBean<Bean>> upstream) {
                return upstream
                        /*.retry(3)*/
                        .flatMap(new Function<BaseBean<Bean>, ObservableSource<Bean>>() {
                            @Override
                            public ObservableSource<Bean> apply(@NonNull BaseBean<Bean> tBaseEntity) throws Exception {
                                return doHandleResult(tBaseEntity);
                            }
                        });
            }
        };
    }

    /**
     * 处理错误码相关内容
     * 先针对返回是否==200和Data是否为空做处理
     *
     * @param <Bean>
     * @return ObservableTransformer
     */
    public static <Bean> Observable<Bean> doHandleResult(final BaseBean<Bean> b) {
        return Observable.create(new ObservableOnSubscribe<Bean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bean> e) throws Exception {
                if (b.getError() == 0 && b.getData() != null) {
                    e.onNext(b.getData());
                    e.onComplete();
                } else if (b.getError() == 0 && b.getData() == null) {
                    e.onError(new CustomException(CustomException.NO_DATA, "加载成功，暂时没有数据"));
                } else {
                    e.onError(new CustomException(b.getError(), (String) b.getData()));
                }
            }
        });
    }


    /**
     * 处理错误码相关内容,但是如果STATUS==200,但是list中没有数据，主要为了处理刷新，下拉加载列表相关操作，
     * 可能存在一种场景，在上一次刷新时，获取到数据，但是在下一次刷新时，里面的data为空,
     * 如果继续使用{@link #handleResult()},那么此时会被认为没有返回值，而出错。
     *
     * @param <T>
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseBean<List<T>>, List<T>> handleListResult() {

        return new ObservableTransformer<BaseBean<List<T>>, List<T>>() {
            @Override
            public ObservableSource<List<T>> apply(@NonNull Observable<BaseBean<List<T>>> upstream) {
                return upstream.flatMap(new Function<BaseBean<List<T>>, ObservableSource<List<T>>>() {
                    @Override
                    public ObservableSource<List<T>> apply(@NonNull BaseBean<List<T>> tBaseEntity) throws Exception {
                        return doHandleListResult(tBaseEntity);
                    }
                });
            }
        };
    }

    public static <T> Observable<List<T>> doHandleListResult(final BaseBean<List<T>> b) {
        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<T>> e) throws Exception {
                if (b.getError() == 0 && b.getData() != null) {
                    e.onNext(b.getData());
                    e.onComplete();
                } else if (b.getError() == 0 && b.getData() == null) {
                    e.onNext(new ArrayList<T>());
                    e.onComplete();
                } else {
                    e.onError(new CustomException(b.getError()));
                }
            }
        });
    }


}