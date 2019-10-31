package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.KnowledgeStructureContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;
import com.robin.robin_wanandroid.mvp.model.cache.CommonCache;
import com.robin.robin_wanandroid.mvp.model.service.MainArticleService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKeyGroup;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

public class KnowledgeStructureModel extends BaseModel implements KnowledgeStructureContract.Model {
    @Inject
    public KnowledgeStructureModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<KnowledgeStructureBean> requestStructureList() {

        return Observable.just(mRepositoryManager.obtainRetrofitService(MainArticleService.class).getKnowledgeStructure())
                .flatMap(new Function<Observable<KnowledgeStructureBean>, ObservableSource<KnowledgeStructureBean>>() {
                    @Override
                    public ObservableSource<KnowledgeStructureBean> apply(Observable<KnowledgeStructureBean> knowledgeStructureBeanObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getKnowledgeStructure(knowledgeStructureBeanObservable)
                                .map(new Function<Reply<KnowledgeStructureBean>, KnowledgeStructureBean>() {
                                    @Override
                                    public KnowledgeStructureBean apply(Reply<KnowledgeStructureBean> knowledgeStructureBeanReply) throws Exception {
                                        return knowledgeStructureBeanReply.getData();
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<KnowledgeArticleBean> requestStructureItem(int page, int cid) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MainArticleService.class).getKnowledgeStructureItem(page, cid))
                .flatMap(new Function<Observable<KnowledgeArticleBean>, ObservableSource<KnowledgeArticleBean>>() {
                    @Override
                    public ObservableSource<KnowledgeArticleBean> apply(Observable<KnowledgeArticleBean> knowledgeArticleBeanObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getKnowledgeStructureItem(knowledgeArticleBeanObservable,new DynamicKeyGroup(page,cid),new EvictDynamicKey(false))
                                .map(new Function<Reply<KnowledgeArticleBean>, KnowledgeArticleBean>() {
                                    @Override
                                    public KnowledgeArticleBean apply(Reply<KnowledgeArticleBean> knowledgeArticleBeanReply) throws Exception {
                                        return knowledgeArticleBeanReply.getData();
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<BannerBean> requestBanner() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MainArticleService.class).getBanner())
                .flatMap(new Function<Observable<BannerBean>, ObservableSource<BannerBean>>() {
                    @Override
                    public ObservableSource<BannerBean> apply(Observable<BannerBean> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getBanner(listObservable)
                                .map(listReply -> listReply.getData());
                    }
                });
    }

}
