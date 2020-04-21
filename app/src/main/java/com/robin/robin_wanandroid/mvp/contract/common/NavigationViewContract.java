package com.robin.robin_wanandroid.mvp.contract.common;

import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;

public interface NavigationViewContract {

    interface  View extends IView {
        void  showCollectList();
        void  showBrowsingHistory();
    }

    interface Presenter extends IPresenter {
        void getCollectList(int page,boolean isRefresh);
        void getBrowsingHistory(int page ,boolean isRefresh);
    }
}
