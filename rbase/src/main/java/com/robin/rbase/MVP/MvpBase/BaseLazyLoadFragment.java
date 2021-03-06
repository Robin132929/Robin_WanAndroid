package com.robin.rbase.MVP.MvpBase;

import android.os.Bundle;

import com.robin.rbase.CommonUtils.Logger.Logger;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * 子类覆写{@link BaseLazyLoadFragment}lazyLoadData可快速实现Fragment懒加载
 */
public abstract class BaseLazyLoadFragment<P extends IPresenter> extends BaseMvpFragment<P> {

    private boolean isViewCreated; // 界面是否已创建完成
    private boolean isVisibleToUser; // 是否对用户可见
    private boolean isDataLoaded; // 数据是否已请求

    /**
     * 第一次可见时触发调用,此处实现具体的数据请求逻辑
     */
    protected abstract void lazyLoadData();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.isVisibleToUser = !hidden;
        Logger.i("status: hidden " + isVisibleToUser + hidden);
        tryLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;

        tryLoadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        Logger.i("status:" +
                "isVisibleToUser " + isVisibleToUser + "\n" + "isViewCreated :" + isViewCreated
                + "\n" + "isDataLoaded " + isDataLoaded + "\n" + "isParentVisable " + isParentVisible());
        tryLoadData();
    }

    /**
     * ViewPager场景下，判断父fragment是否可见
     */
    private boolean isParentVisible() {
        Fragment fragment = getParentFragment();
        if (fragment != null) {
            Logger.i("paerent is " + fragment.getClass().getSimpleName() + (fragment instanceof BaseLazyLoadFragment && ((BaseLazyLoadFragment) fragment).getUserVisibleHint() && !fragment.isHidden()));
        } else {
            Logger.i("paerent is null");
        }
        return fragment == null || (fragment instanceof BaseLazyLoadFragment && ((BaseLazyLoadFragment) fragment).getUserVisibleHint() && !fragment.isHidden());
    }

    /**
     * ViewPager场景下，当前fragment可见时，如果其子fragment也可见，则让子fragment请求数据
     */
    private void dispatchParentVisibleState() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment child : fragments) {
            if (child instanceof BaseLazyLoadFragment && ((BaseLazyLoadFragment) child).isVisibleToUser) {
                ((BaseLazyLoadFragment) child).tryLoadData();
            }
        }
    }

    public void tryLoadData() {
        Logger.i(getClass().getSimpleName() + " status:" +
                "isVisibleToUser " + isVisibleToUser + " isViewCreated :" + isViewCreated
                + " isDataLoaded " + isDataLoaded + " isParentVisable " + isParentVisible());
        if (isViewCreated && isVisibleToUser && isParentVisible() && !isDataLoaded) {
            lazyLoadData();
            isDataLoaded = true;
            //通知子Fragment请求数据
            dispatchParentVisibleState();
        }
    }

}
