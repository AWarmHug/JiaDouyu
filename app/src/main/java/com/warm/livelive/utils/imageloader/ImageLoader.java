package com.warm.livelive.utils.imageloader;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * 作者：warm
 * 时间：2018-06-12 13:45
 * 描述：
 */
public interface ImageLoader {

    void loadImage(Context context, View view, Object object);

    void loadImage(Context context, View view, Object object, int roundDp);

    void loadImage(Fragment fragment, View view, Object object);

    void loadImage(Fragment fragment, View view, Object object, int roundDp);

    void resume(Context context);

    void pause(Context context);

}
