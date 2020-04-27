package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;

public interface WanAndroidMainFragmentContract {

    interface View extends IView {
        void showBanner(BannerBean banner);
    }

    interface Presenter extends IPresenter {
        void getBannerData();
    }
}
