package com.warm.livelive.base.actiivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.View;

import com.warm.livelive.R;
import com.warm.livelive.base.BaseView;
import com.warm.livelive.error.CustomException;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ProgressDialog pDialog;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());

        //防止字体被修改
        reviseFont();

        //禁止屏幕旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        unbinder = ButterKnife.bind(this);
    }

    private void reviseFont() {
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        config.fontScale = 1.0f;// 默认字体尺寸值为1.0f
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    @Override
    protected void onResume() {
        super.onResume();
        reviseFont();
    }


    @LayoutRes
    public abstract int layoutResID();

    @NonNull
    public ProgressDialog getPDialog() {
        if (pDialog == null) {
            pDialog = new ProgressDialog(this, R.style.AppTheme_Dialog_Progress);
            pDialog.setMessage("请稍后...");
            pDialog.setCancelable(false);
            pDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }
                        onBackPressed();
                        return true;
                    }
                    return false;
                }
            });
        }
        return pDialog;
    }

    @Override
    public void onShowLoad() {
        if (!getPDialog().isShowing()) {
            getPDialog().show();
        }
    }


    @Override
    public final void onDismissLoad() {
        if (getPDialog().isShowing()) {
            getPDialog().dismiss();
        }
    }


    public void onTakeException(@NonNull final CustomException error) {
        Snackbar.make(findViewById(R.id.toolbar) == null ? getWindow().getDecorView() : findViewById(R.id.toolbar)
                , error.getMessage()
                , Snackbar.LENGTH_LONG)
                .setAction(error.getActionName() == null ? getString(R.string.i_know) : error.getActionName()
                        , new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (error.getListener() != null) {
                                    error.getListener().errorAction();
                                }
                            }
                        })
                .show();
    }


    @Override
    public Context getBVContext() {
        return this;
    }

    @Override
    public FragmentActivity getBVActivity() {
        return this;
    }

    /**
     * @return statuBar高度
     */
    public int getStateBarHeight() {
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight = 0;

        if (Build.VERSION.SDK_INT >= 21) {
            //获取status_bar_height资源的ID
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        }
        return statusBarHeight;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
