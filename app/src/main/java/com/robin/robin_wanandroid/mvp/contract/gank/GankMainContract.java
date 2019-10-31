package com.robin.robin_wanandroid.mvp.contract.gank;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.GankAndroidBean;

import java.util.List;

import io.reactivex.Observable;

public interface GankMainContract {
    interface Model extends IModel {
        Observable<GankAndroidBean> getAndroidData(int count,int page,boolean isRefresh);
    }

    interface View extends IView {
        void setAndroidData(GankAndroidBean bean,boolean isRefresh);
    }

    interface Presenter extends IPresenter {
        void getAndroidData(int count,int page,boolean isRefresh);
    }
}
