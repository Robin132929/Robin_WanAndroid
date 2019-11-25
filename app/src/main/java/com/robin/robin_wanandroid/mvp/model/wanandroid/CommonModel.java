package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.CommonContract;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class CommonModel extends BaseModel implements CommonContract.Model {
    public CommonModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<AddCollectBean> addCollectArticle(int id) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).addCollectArticle(id))
                .flatMap(new Function<Observable<AddCollectBean>, ObservableSource<AddCollectBean>>() {
                    @Override
                    public ObservableSource<AddCollectBean> apply(Observable<AddCollectBean> addCollectBeanObservable) throws Exception {
                        return addCollectBeanObservable;
                    }
                });
    }

    @Override
    public Observable<AddCollectBean> cancelCollectArticle(int id) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).cancelCollectArticle(id))
                .flatMap(new Function<Observable<AddCollectBean>, ObservableSource<AddCollectBean>>() {
                    @Override
                    public ObservableSource<AddCollectBean> apply(Observable<AddCollectBean> addCollectBeanObservable) throws Exception {
                        return addCollectBeanObservable;
                    }
                });
    }

    @Override
    public Observable<AddCollectBean> addFootPrint(String name, String link) {
        Logger.i("add footprint modle"+name+link);

        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).addFootPrint(name, link))
                .flatMap(new Function<Observable<AddCollectBean>, ObservableSource<AddCollectBean>>() {
                    @Override
                    public ObservableSource<AddCollectBean> apply(Observable<AddCollectBean> addCollectBeanObservable) throws Exception {
                        Logger.i("add footprint return");

                        return addCollectBeanObservable;
                    }
                });
    }
}
