package com.warm.playerlib;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * 作者：warm
 * 时间：2017-12-23 14:07
 * 描述：
 */

public class DisplayUtils {


    public static void hideBar(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
//        for (int i=0;i<viewGroup.getChildCount();i++) {
//            viewGroup.getChildAt(i).setVisibility(View.GONE);
//        }
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        fragmentActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fragmentActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        hideNavKey(activity);
    }

    public static void showBar(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
//        for (int i=0;i<viewGroup.getChildCount();i++) {
//            viewGroup.getChildAt(i).setVisibility(View.GONE);
//        }
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        fragmentActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fragmentActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        showNavKey(activity);
    }

    /**
     * 隐藏NavigationBar
     */
    public static void hideNavKey(Activity activity) {
        int flag;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //       设置屏幕始终在前面，不然点击鼠标，重新出现虚拟按键
            flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        } else {
            flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // hide nav
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(flag);
    }

    /**
     * 显示NavigationBar
     */
    public static void showNavKey(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(0);
    }

}
