package com.robin.robin_wanandroid.mvp.contract;

import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.HotSearchBean;

import io.reactivex.Observable;

public interface SearchContract {
    interface Model extends IModel {
        Observable<HotSearchBean>  getHotSearchKey();
    }

    interface View extends IView {
       void  setHotSearchKey(HotSearchBean bean);
    }

    interface Presenter extends IPresenter {
       void  getHotSearchKey();
    }
}
