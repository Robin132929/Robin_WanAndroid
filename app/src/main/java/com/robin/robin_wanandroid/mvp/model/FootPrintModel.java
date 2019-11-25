package com.robin.robin_wanandroid.mvp.model;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
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
    public Observable<GetCollectBean> getFootPrintList(int page, boolean isRefresh) {
        return Observable.just(mRepositoryManager.obtainCacheService(Api.class).getFootPrintList())
                .flatMap(new Function<Observable<GetCollectBean>, ObservableSource<GetCollectBean>>() {
                    @Override
                    public ObservableSource<GetCollectBean> apply(Observable<GetCollectBean> getCollectBeanObservable) throws Exception {
                        return getCollectBeanObservable;
                    }
                });
    }


}
