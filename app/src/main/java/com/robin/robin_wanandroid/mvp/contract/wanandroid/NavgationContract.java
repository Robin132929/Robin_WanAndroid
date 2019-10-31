package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;

import io.reactivex.Observable;

public interface NavgationContract {
    interface Model extends IModel {
        Observable<NavgationBean> requestNavgationData();
    }

    interface View extends IView {
        void setNavgationData(NavgationBean navgationData);
    }

    interface Presenter extends IPresenter {
        void requestNavgationData();
    }
}
