package com.robin.robin_wanandroid.mvp.model.cache;

import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatContentBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatTitleBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.DynamicKeyGroup;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

public interface CommonCache {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<MainArticleBean>> getMainArticle(Observable<MainArticleBean> article, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<BannerBean>> getBanner(Observable<BannerBean> banner);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<NavgationBean>> getNavgationData(Observable<NavgationBean> banner);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<KnowledgeStructureBean>> getKnowledgeStructure(Observable<KnowledgeStructureBean> bean);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<KnowledgeArticleBean>> getKnowledgeStructureItem(Observable<KnowledgeArticleBean> article, DynamicKeyGroup idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<WechatTitleBean>> getWechatTitle(Observable<WechatTitleBean> bean);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<WechatContentBean>> getWechatContent(Observable<WechatContentBean> article, DynamicKeyGroup idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<ProjectCategoryBean>> getProjectCategory(Observable<ProjectCategoryBean> bean);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<ProjectItemBean>> getProjectItem(Observable<ProjectItemBean> article, DynamicKeyGroup idLastUserQueried, EvictProvider evictProvider);

}
