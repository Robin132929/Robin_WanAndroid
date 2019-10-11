package com.robin.robin_wanandroid.mvp.model;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.ProjectContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;
import com.robin.robin_wanandroid.mvp.model.cache.CommonCache;
import com.robin.robin_wanandroid.mvp.model.service.MainArticleService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKeyGroup;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;

public class ProjectModel extends BaseModel implements ProjectContract.Model {
    @Inject
    public ProjectModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<ProjectCategoryBean> getProjectCategory() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MainArticleService.class).getProjectCategory())
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
    public Observable<ProjectItemBean> getProjectitem(int page, int cid, boolean isRefresh) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MainArticleService.class).getProjectItem(page, cid))
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
    public Observable<BannerBean> getBanner() {
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
