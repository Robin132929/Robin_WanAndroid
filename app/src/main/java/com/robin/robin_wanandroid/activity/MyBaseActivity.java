package com.robin.robin_wanandroid.activity;

import android.graphics.drawable.ColorDrawable;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpActivity;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.util.statusbarUtil.StatusBarUtil;

import androidx.annotation.ColorInt;

public abstract class MyBaseActivity<P extends BasePresenter> extends BaseMvpActivity<P> {
    public void initColor(@ColorInt int color){
        StatusBarUtil.setStatusBarColor(this, color);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        }
    }
}
