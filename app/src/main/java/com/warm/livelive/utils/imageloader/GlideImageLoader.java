package com.warm.livelive.utils.imageloader;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * 作者：warm
 * 时间：2018-06-12 17:34
 * 描述：
 */
public class GlideImageLoader implements ImageLoader {

    @Override
    public void loadImage(Context context, ImageView imageView, Object object) {
        GlideApp.with(context)
                .load(object)
                .into(imageView);
    }


    @Override
    public void loadImage(Fragment fragment, ImageView imageView, Object object) {
        GlideApp.with(fragment)
                .load(object)
                .into(imageView);
    }

    @Override
    public void resume(Context context) {
        GlideApp.with(context).resumeRequests();
    }

    @Override
    public void pause(Context context) {
        GlideApp.with(context).pauseAllRequests();
    }
}
