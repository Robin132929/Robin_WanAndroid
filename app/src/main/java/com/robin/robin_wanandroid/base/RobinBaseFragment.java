package com.robin.robin_wanandroid.base;

import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.robin_wanandroid.util.loading.Gloading;

public abstract class RobinBaseFragment<P extends IPresenter> extends BaseMvpFragment<P> {
    protected Gloading.Holder mHolder;

    protected abstract void initLoadingStatusViewIfNeed();

    protected abstract void onLoadRetry();

    public void showLoadingView() {
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
