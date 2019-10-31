package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.NavgationContract;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;
import com.robin.robin_wanandroid.mvp.model.cache.CommonCache;
import com.robin.robin_wanandroid.mvp.model.service.MainArticleService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.Reply;

public class NavgationModel extends BaseModel implements NavgationContract.Model {
    @Inject
    public NavgationModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<NavgationBean> requestNavgationData() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MainArticleService.class).getNavgationData())
                .flatMap(new Function<Observable<NavgationBean>, ObservableSource<NavgationBean>>() {
                    @Override
                    public ObservableSource<NavgationBean> apply(Observable<NavgationBean> navgationBeanObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getNavgationData(navgationBeanObservable)
                                .map(new Function<Reply<NavgationBean>, NavgationBean>() {
                                    @Override
                                    public NavgationBean apply(Reply<NavgationBean> navgationBeanReply) throws Exception {
                                        return navgationBeanReply.getData();
                                    }
                                });
                    }
                });

    }

}
