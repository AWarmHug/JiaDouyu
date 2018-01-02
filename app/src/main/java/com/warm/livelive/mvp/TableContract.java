package com.warm.livelive.mvp;

import android.support.annotation.StringDef;

import com.warm.livelive.base.BaseView;
import com.warm.livelive.base.IBasePresenter;
import com.warm.livelive.data.bean.SubChannel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * 作者：warm
 * 时间：2018-01-02 16:58
 * 描述：
 */

public interface TableContract {

    interface View extends BaseView {

        void getSubChannel(List<SubChannel> subChannels);

    }

    interface Presenter extends IBasePresenter<View> {

        void getSubChannel(@Tag String tag);

    }


    public static final String GAME = "game";
    public static final String SJYX = "sjyx";
    public static final String KTYX = "ktyx";
    public static final String KJ = "kj";
    public static final String YZ = "yz";
    public static final String YL = "yl";
    public static final String ZNL = "znl";

    @StringDef({GAME, SJYX, KTYX, KJ, YZ, YL, ZNL})
    @Retention(RetentionPolicy.CLASS)
    public @interface Tag {
    }

}
