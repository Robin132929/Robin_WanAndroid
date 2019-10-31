package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.WechatContentBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatTitleBean;

import io.reactivex.Observable;

public interface WechatContract {
    interface Model extends IModel {
        Observable<WechatTitleBean> requestWechatTitle();
        Observable<WechatContentBean> requestWechatContent(int id,int page,boolean isRefresh);
    }

    interface View extends IView {
        void setWechatTitle(WechatTitleBean bean);
        void setWechatContent(WechatContentBean bean,boolean isRefresh);
    }

    interface Presenter extends IPresenter {
        void requestWechatTitle();
        void requestWechatContent(int id,int page,boolean isRefresh);
    }
}
