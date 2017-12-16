package com.warm.baselib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.warm.baselib.error.ComException;


/**
 * Created by warm on 17/6/17.
 */

public interface BaseView {

    void onDialogShow();

    void onDialogHide(@Nullable ComException error);

    void onActionDialogShow();

    void onActionDialogHide(@Nullable ComException error);

    Context getBVContext();

    FragmentActivity getBVActivity();


}
