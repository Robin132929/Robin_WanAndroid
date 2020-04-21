package com.robin.robin_wanandroid.mvp.contract.common;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.LogoutResultBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;

import io.reactivex.Observable;

public interface MainContract {
    interface Model extends IModel {
        Observable<LogoutResultBean> logout();
    }

    interface View extends IView {
       void  showLogoutSuccess(boolean success);
    }

    interface Presenter extends IPresenter {
       void  logout();
    }
}
