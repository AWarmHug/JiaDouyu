package com.warm.livelive.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.warm.livelive.error.KnownException;


/**
 * Created by warm on 17/6/17.
 */

public interface BaseView {

    void emptyLoad();

    void dismissEmptyLoad();

    void toast(@NonNull KnownException error);

    void toast(@NonNull String msg);

    Context getBVContext();

    FragmentActivity getBVActivity();


}
