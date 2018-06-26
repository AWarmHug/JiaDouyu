package com.warm.livelive.utils.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.warm.livelive.utils.DisplayUtil;

/**
 * 作者：warm
 * 时间：2018-06-12 17:34
 * 描述：
 */
public class GlideImageLoader implements ImageLoader {


    @Override
    public void loadImage(Context context, View view, Object object) {
        GlideRequest<Drawable> glide = GlideApp.with(context)
                .load(object)
                .transition(DrawableTransitionOptions.withCrossFade(300));
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }

    @Override
    public void loadImage(Context context, View view, Object object, int roundDp) {
        GlideRequest<Drawable> glide = GlideApp.with(context)
                .load(object)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new RoundedCorners(DisplayUtil.dp2px(view.getContext(), roundDp)));
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }


    @Override
    public void loadImage(Fragment fragment, View view, Object object) {
        GlideRequest<Drawable> glide = GlideApp.with(fragment)
                .load(object)
                .transition(DrawableTransitionOptions.withCrossFade(300));
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }


    @Override
    public void loadImage(Fragment fragment, View view, Object object, int roundDp) {
        GlideRequest<Drawable> glide = GlideApp.with(fragment)
                .load(object)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new RoundedCorners(DisplayUtil.dp2px(view.getContext(), roundDp)));
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
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
