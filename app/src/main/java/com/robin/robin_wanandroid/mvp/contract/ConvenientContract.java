package com.robin.robin_wanandroid.mvp.contract;

import com.robin.robin_wanandroid.mvp.contract.wanandroid.CommonContract;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.FootPrintBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;

import io.reactivex.Observable;

public interface ConvenientContract extends CommonContract {
    interface Model extends CommonContract.Model {
        Observable<wendaBean> requestWendaList(int page);
        Observable<GetCollectBean> getCollectList(int page, boolean isRefresh);
        Observable<AddCollectBean> removeCollectState(int id, int originId);
        Observable<FootPrintBean> getFootPrintList(int page , boolean isRefresh);

    }

    interface View extends CommonContract.View {
        void setData(Object data,Object... args);
    }

    interface Presenter extends CommonContract.Presenter {
        //问答
        void request_WendaList(int page);
        void loadMore_wenda(int page);
        void refresh_wenda();

        //面试集锦
//        void request

        void getCollectList(int page,boolean isRefresh);
        void removeCollectState(int id,int originId);
        void getFootPrintList(int page ,boolean isRefresh);

    }
}
