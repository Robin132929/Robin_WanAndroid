package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;

import io.reactivex.Observable;


public interface CommonContract {
    interface Model extends IModel {
        Observable<AddCollectBean> addCollectArticle(int id);
        Observable<AddCollectBean> cancelCollectArticle(int id);
        Observable<AddCollectBean> addFootPrint(String name,String link);
    }
    interface View extends IView{

    }
    interface Presenter extends IPresenter {
        void  addCollectArticle(int id);
        void  cancelCollectArticle(int id);

        void addFootPrint(String name,String link);
    }
}
