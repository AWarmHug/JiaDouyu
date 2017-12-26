package com.warm.livelive.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.warm.livelive.error.CustomException;


/**
 * Created by warm on 17/6/17.
 */

public interface BaseView {

    void onShowLoad();

    void onDismissLoad();

    void onTakeException(@NonNull CustomException error);

    Context getBVContext();

    FragmentActivity getBVActivity();


}
