package com.robin.robin_wanandroid.mvp.model.common;

import com.robin.rbase.MVP.integration.IRepositoryManager;
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

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
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
        return null;
    }

    @Override
    public Observable<KnowledgeStructureBean> getKnowledgeStructure() {
        return null;
    }

    @Override
    public Observable<KnowledgeArticleBean> getKnowledgeStructureItem(int page, int cid) {
        return null;
    }

    @Override
    public Observable<WechatTitleBean> getWechatTitle() {
        return null;
    }

    @Override
    public Observable<WechatContentBean> getWechatContent(int id, int page) {
        return null;
    }

    @Override
    public Observable<ProjectCategoryBean> getProjectCategory() {
        return null;
    }

    @Override
    public Observable<ProjectItemBean> getProjectItem(int page, int cid) {
        return null;
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
        return null;
    }

    private static class INSTANCE {
        private static DataManager dataManager = new DataManager(new RepositoryManager());
    }
}
