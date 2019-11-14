package com.robin.robin_wanandroid.mvp.contract.wanandroid;

import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;

import io.reactivex.Observable;

public interface CollectContract extends CommonContract{
    interface Model extends CommonContract.Model {
        Observable<GetCollectBean> getCollectList(int page,boolean isRefresh);
        Observable<AddCollectBean> removeCollectState(int id, int originId);
    }

    interface View extends CommonContract.View{
        void setCollectList(GetCollectBean bean,boolean isRefresh);
        void removeCollectState(boolean success);

    }

    interface Presenter extends CommonContract.Presenter{
        void getCollectList(int page,boolean isRefresh);
        void removeCollectState(int id,int originId);
    }
}
