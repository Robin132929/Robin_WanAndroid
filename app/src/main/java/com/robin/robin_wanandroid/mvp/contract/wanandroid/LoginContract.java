package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.LoginDataBean;

import io.reactivex.Observable;


public interface LoginContract {
    interface Model extends IModel {
       Observable<LoginDataBean> login(String name, String password);
    }

    interface View extends IView {
        void login(String name,String password);
    }

    interface Presenter extends IPresenter {
        void login(String name,String password);
    }
}
