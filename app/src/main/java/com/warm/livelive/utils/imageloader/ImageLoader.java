package com.warm.livelive.utils.imageloader;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * 作者：warm
 * 时间：2018-06-12 13:45
 * 描述：
 */
public interface ImageLoader {

    void loadImage(Context context, ImageView imageView,Object object);

    void loadImage(Fragment fragment, ImageView imageView, Object object);

    void resume(Context context);

    void pause(Context context);

}
