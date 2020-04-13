package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.ConvenientContract;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class ConvenientModel extends BaseModel implements ConvenientContract.Model {
   @Inject
    public ConvenientModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<wendaBean> requestWenda(int page) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getWendaList(page))
                .flatMap(new Function<Observable<wendaBean>, ObservableSource<wendaBean>>() {
                    @Override
                    public ObservableSource<wendaBean> apply(Observable<wendaBean> addCollectBeanObservable) throws Exception {
                        return addCollectBeanObservable;
                    }
                });
    }
}
