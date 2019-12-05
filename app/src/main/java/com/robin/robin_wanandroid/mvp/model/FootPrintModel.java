package com.robin.robin_wanandroid.mvp.model;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
import com.robin.robin_wanandroid.mvp.model.bean.FootPrintBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class FootPrintModel extends BaseModel implements FootPrintContract.Model {
    @Inject
    public FootPrintModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<FootPrintBean> getFootPrintList(int page, boolean isRefresh) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getFootPrintList())
                .flatMap(new Function<Observable<FootPrintBean>, ObservableSource<FootPrintBean>>() {
                    @Override
                    public ObservableSource<FootPrintBean> apply(Observable<FootPrintBean> footPrintBeanObservable) throws Exception {
                        return footPrintBeanObservable;
                    }
                });
    }


}
