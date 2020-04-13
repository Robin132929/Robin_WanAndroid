package com.robin.robin_wanandroid.base;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpActivity;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.util.SettingUtil;
import com.robin.robin_wanandroid.util.statusbarUtil.StatusBarUtil;
import com.robin.robin_wanandroid.util.statusbarUtil.SystemBarTintManager;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class MyBaseActivity<P extends BasePresenter> extends BaseMvpActivity<P> {
    private Unbinder unBinder;

    public void initColor(@ColorInt int color){
        StatusBarUtil.setStatusBarColor(this, color);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);

//        //设置系统状态栏沉浸 目前仅支持5.0以上 其他版本有待适配
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(getResources().getColor(R.color.ALIZARIN));
//        }
        initColor(getResources().getColor(SettingUtil.getColor()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }


}
