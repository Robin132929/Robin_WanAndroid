package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.LogoutResultBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;

import io.reactivex.Observable;

public interface MainContract {
    interface Model extends IModel {
//        Observable<MainArticleBean> getMainArticle(int page);
        Observable<LogoutResultBean> logout();
    }

    interface View extends IView {
//        void setHomeArt(MainArticleBean.DataBean dataBean);
       void  showLogoutSuccess(boolean success);
    }

    interface Presenter extends IPresenter {
//        MainArticleBean load(int page);
       void  logout();
    }
}
