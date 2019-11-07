package com.robin.robin_wanandroid.mvp.model.gank;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.gank.GankMainContract;
import com.robin.robin_wanandroid.mvp.model.bean.GankAndroidBean;
import com.robin.robin_wanandroid.mvp.model.cache.CommonCache;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;

public class GankMainModel extends BaseModel implements GankMainContract.Model {
    @Inject
    public GankMainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<GankAndroidBean> getAndroidData(int count, int page,boolean isRefresh) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getGankAndroidData(page))
                .flatMap(new Function<Observable<GankAndroidBean>, ObservableSource<GankAndroidBean>>() {
                    @Override
                    public ObservableSource<GankAndroidBean> apply(Observable<GankAndroidBean> gankAndroidBeanObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getGankAndroidData(gankAndroidBeanObservable,new DynamicKey(page),new EvictProvider(isRefresh))
                                .map(Reply::getData);
                    }
                });

    }
}
