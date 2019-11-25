package com.robin.robin_wanandroid.mvp.contract;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;

import io.reactivex.Observable;

public interface FootPrintContract {
    interface Model extends IModel {
        Observable<GetCollectBean> getFootPrintList(int page ,boolean isRefresh);
    }

    interface View extends IView {
//        void getFootPrintList(int page ,boolean isRefresh);
        void setFootPrintList(GetCollectBean bean);
    }

    interface Presenter extends IPresenter {
        void getFootPrintList(int page ,boolean isRefresh);
    }
}
