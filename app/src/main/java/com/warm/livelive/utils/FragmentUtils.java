package com.warm.livelive.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 作者：warm
 * 时间：2018-02-07 15:51
 * 描述：
 */
public class FragmentUtils {
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        CheckUtil.checkNotNull(fragmentManager);
        CheckUtil.checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void addFragmentToBackStack(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        CheckUtil.checkNotNull(fragmentManager);
        CheckUtil.checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();
    }

}
