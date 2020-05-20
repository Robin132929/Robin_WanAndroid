package com.robin.robin_wanandroid.mvp.model.common;

import com.robin.rbase.MVP.integration.RepositoryManager;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.FootPrintBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;
import com.robin.robin_wanandroid.mvp.model.bean.LoginDataBean;
import com.robin.robin_wanandroid.mvp.model.bean.LogoutResultBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatContentBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatTitleBean;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;
import com.robin.robin_wanandroid.mvp.model.cache.CommonCache;
import com.robin.robin_wanandroid.mvp.model.db.DbHelper;
import com.robin.robin_wanandroid.mvp.model.db.DbHelperImpl;
import com.robin.robin_wanandroid.mvp.model.http.HttpHelper;
import com.robin.robin_wanandroid.mvp.model.http.HttpHelperImpl;
import com.robin.robin_wanandroid.mvp.model.http.WanAndroidApi;
import com.robin.robin_wanandroid.mvp.model.pref.PreHelperImpl;
import com.robin.robin_wanandroid.mvp.model.pref.PrefHelper;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.DynamicKeyGroup;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;
public class DataManager extends com.robin.rbase.MVP.MvpBase.BaseModel implements HttpHelper.WanAndroidApi, DbHelper, PrefHelper {
    private DbHelper mDbHelper;
    private PrefHelper mPreferenceHelper;

    @Inject
    public DataManager(RepositoryManager repositoryManager) {
        super(repositoryManager);
        this.mDbHelper = mDbHelper;
        this.mPreferenceHelper = mPreferenceHelper;
    }

    public static DataManager getInstance() {
        return INSTANCE.dataManager;
    }

    @Override
    public Observable<MainArticleBean> getMainArt(int page) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(WanAndroidApi.class).getMainArt(page))
                .flatMap((Function<Observable<MainArticleBean>, ObservableSource<MainArticleBean>>)
                        mainArticleBeanObservable -> mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getMainArticle(mainArticleBeanObservable, new DynamicKey(page), new EvictDynamicKey(false))
                                .map(Reply::getData));
    }

    @Override
    public Observable<BannerBean> getBanner() {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(WanAndroidApi.class).getBanner())
                .flatMap((Function<Observable<BannerBean>, ObservableSource<BannerBean>>)
                        bannerBeanObservable -> mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getBanner(bannerBeanObservable)
                                .map(Reply::getData));
    }

    @Override
    public Observable<NavgationBean> getNavgationData() {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(WanAndroidApi.class).getNavgationData())
                .flatMap((Function<Observable<NavgationBean>, ObservableSource<NavgationBean>>)
                        navgationBeanObservable -> mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getNavgationData(navgationBeanObservable)
                                .map(Reply::getData));
    }

    @Override
    public Observable<KnowledgeStructureBean> getKnowledgeStructure() {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class).getKnowledgeStructure())
                .flatMap((Function<Observable<KnowledgeStructureBean>, ObservableSource<KnowledgeStructureBean>>)
                        knowledgeStructureBeanObservable -> mRepositoryManager.obtainCacheService(CommonCache.class)
                        .getKnowledgeStructure(knowledgeStructureBeanObservable)
                        .map(Reply::getData));
    }

    @Override
    public Observable<KnowledgeArticleBean> getKnowledgeStructureItem(int page, int cid) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class).getKnowledgeStructureItem(page, cid))
                .flatMap((Function<Observable<KnowledgeArticleBean>, ObservableSource<KnowledgeArticleBean>>)
                        knowledgeArticleBeanObservable -> mRepositoryManager.obtainCacheService(CommonCache.class)
                        .getKnowledgeStructureItem(knowledgeArticleBeanObservable,
                                new DynamicKeyGroup(page,cid),new EvictDynamicKey(false))
                        .map(Reply::getData));
    }

    @Override
    public Observable<WechatTitleBean> getWechatTitle() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getWechatTitle())
                .flatMap(new Function<Observable<WechatTitleBean>, ObservableSource<WechatTitleBean>>() {
                    @Override
                    public ObservableSource<WechatTitleBean> apply(Observable<WechatTitleBean> wechatTitleBeanObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getWechatTitle(wechatTitleBeanObservable)
                                .map(new Function<Reply<WechatTitleBean>, WechatTitleBean>() {
                                    @Override
                                    public WechatTitleBean apply(Reply<WechatTitleBean> wechatTitleBeanReply) throws Exception {
                                        return wechatTitleBeanReply.getData();
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<WechatContentBean> getWechatContent(int id, int page) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getWechatContent(id, page))
                .flatMap(new Function<Observable<WechatContentBean>, ObservableSource<WechatContentBean>>() {
                    @Override
                    public ObservableSource<WechatContentBean> apply(Observable<WechatContentBean> wechatContentBeanObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getWechatContent(wechatContentBeanObservable,new DynamicKeyGroup(page,id),new EvictDynamicKey(false))
                                .map(new Function<Reply<WechatContentBean>, WechatContentBean>() {
                                    @Override
                                    public WechatContentBean apply(Reply<WechatContentBean> wechatContentBeanReply) throws Exception {
                                        return wechatContentBeanReply.getData();
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<ProjectCategoryBean> getProjectCategory() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getProjectCategory())
                .flatMap(new Function<Observable<ProjectCategoryBean>, ObservableSource<ProjectCategoryBean>>() {
                    @Override
                    public ObservableSource<ProjectCategoryBean> apply(Observable<ProjectCategoryBean> projectCategoryBeanObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class).getProjectCategory(projectCategoryBeanObservable)
                                .map(new Function<Reply<ProjectCategoryBean>, ProjectCategoryBean>() {
                                    @Override
                                    public ProjectCategoryBean apply(Reply<ProjectCategoryBean> projectCategoryBeanReply) throws Exception {
                                        return projectCategoryBeanReply.getData();
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<ProjectItemBean> getProjectItem(int page, int cid) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getProjectItem(page, cid))
                .flatMap(new Function<Observable<ProjectItemBean>, ObservableSource<ProjectItemBean>>() {
                    @Override
                    public ObservableSource<ProjectItemBean> apply(Observable<ProjectItemBean> projectItemBeanObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getProjectItem(projectItemBeanObservable,new DynamicKeyGroup(page,cid),new EvictProvider(false))
                                .map(new Function<Reply<ProjectItemBean>, ProjectItemBean>() {
                                    @Override
                                    public ProjectItemBean apply(Reply<ProjectItemBean> projectItemBeanReply) throws Exception {
                                        return projectItemBeanReply.getData();
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<LoginDataBean> getLoginData(String username, String password) {
        return null;
    }

    @Override
    public Observable<LogoutResultBean> logout() {
        return null;
    }

    @Override
    public Observable<AddCollectBean> addCollectArticle(int id) {
        return null;
    }

    @Override
    public Observable<AddCollectBean> cancelCollectArticle(int id) {
        return null;
    }

    @Override
    public Observable<AddCollectBean> removeCollectArticle(int id, int originId) {
        return null;
    }

    @Override
    public Observable<GetCollectBean> getCollectList(int page) {
        return null;
    }

    @Override
    public Observable<AddCollectBean> addExternalLink(String title, String author, String link) {
        return null;
    }

    @Override
    public Observable<AddCollectBean> addFootPrint(String name, String link) {
        return null;
    }

    @Override
    public Observable<wendaBean> getWendaList(int page) {
        return null;
    }

    @Override
    public Observable<FootPrintBean> getFootPrintList() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getFootPrintList())
                .flatMap(new Function<Observable<FootPrintBean>, ObservableSource<FootPrintBean>>() {
            @Override
            public ObservableSource<FootPrintBean> apply(Observable<FootPrintBean> footPrintBeanObservable) throws Exception {
                return footPrintBeanObservable;
            }
        });
    }

    private static class INSTANCE {
        private static DataManager dataManager = new DataManager(new RepositoryManager());
    }
}
