package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.common.MainContract;
import com.robin.robin_wanandroid.mvp.model.bean.LogoutResultBean;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<LogoutResultBean> logout() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).logout())
                .flatMap(new Function<Observable<LogoutResultBean>, ObservableSource<LogoutResultBean>>() {
                    @Override
                    public ObservableSource<LogoutResultBean> apply(Observable<LogoutResultBean> logoutResultBeanObservable) throws Exception {
                        return logoutResultBeanObservable;
                    }
                });
    }


//    @Override
//    public Observable<MainArticleBean> getMainArticle(int page) {
//        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getMainArt(page))
//                .flatMap(new Function<Observable<MainArticleBean>, ObservableSource<MainArticleBean>>() {
//                    @Override
//                    public ObservableSource<MainArticleBean> apply(Observable<MainArticleBean> listObservable) throws Exception {
//                        return mRepositoryManager.obtainCacheService(CommonCache.class)
//                                .getMainArticle(listObservable
//                                        ,new DynamicKey(page)
//                                        ,new EvictDynamicKey(true))
//                                .map(listReply -> listReply.getData());
//                    }
//                });
//    }
//
//    @Override
//    public Observable<LogoutResultBean> logout() {
//        return null;
//    }
}
