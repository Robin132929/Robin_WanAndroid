package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.HomeContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.TopArticleBean;
import com.robin.robin_wanandroid.mvp.model.cache.CommonCache;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

public class HomeModel extends CommonModel implements HomeContract.Model {
    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BannerBean> requestBanner() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getBanner())
                .flatMap(new Function<Observable<BannerBean>, ObservableSource<BannerBean>>() {
                    @Override
                    public ObservableSource<BannerBean> apply(Observable<BannerBean> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getBanner(listObservable)
                                .map(listReply -> listReply.getData());
                    }
                });
    }

    @Override
    public Observable<MainArticleBean> requestArticle(int page, boolean isSave) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getMainArt(page))
                .flatMap(new Function<Observable<MainArticleBean>, ObservableSource<MainArticleBean>>() {
                    @Override
                    public ObservableSource<MainArticleBean> apply(Observable<MainArticleBean> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getMainArticle(listObservable
                                        ,new DynamicKey(page)
                                        ,new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public Observable<TopArticleBean> requestTopArticle() {
        return null;
    }
}
