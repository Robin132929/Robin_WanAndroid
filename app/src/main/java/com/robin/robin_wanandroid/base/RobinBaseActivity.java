package com.robin.robin_wanandroid.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.robin.rbase.MVP.MvpBase.BaseMvpActivity;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.util.SettingUtil;
import com.robin.robin_wanandroid.util.loading.Gloading;
import com.robin.robin_wanandroid.util.statusbarUtil.StatusBarUtil;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import butterknife.Unbinder;

public abstract class RobinBaseActivity<P extends BasePresenter> extends BaseMvpActivity<P> {
//    private Unbinder unBinder;
    protected Gloading.Holder mHolder;
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


    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(this).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    protected void onLoadRetry() {
        // override this method in subclass to do retry task
    }

    public void showLoading() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoading();
    }

    public void showLoadSuccess() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadSuccess();
    }

    public void showLoadFailed() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadFailed();
    }

    public void showEmpty() {
        initLoadingStatusViewIfNeed();
        mHolder.showEmpty();
    }

}
