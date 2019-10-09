package com.robin.robin_wanandroid.mvp.model.service;

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

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainArticleService {
    @GET("article/list/{pageNum}/json")
    Observable<MainArticleBean> getMainArt(@Path("pageNum")int page);

    @GET("banner/json")
    Observable<BannerBean> getBanner();

    @GET("navi/json")
    Observable<NavgationBean> getNavgationData();

    @GET("tree/json")
    Observable<KnowledgeStructureBean> getKnowledgeStructure();

    @GET("article/list/{pageNum}/json")
    Observable<KnowledgeArticleBean> getKnowledgeStructureItem(@Path("pageNum")int page, @Query("cid")int cid);

    @GET("wxarticle/chapters/json ")
    Observable<WechatTitleBean> getWechatTitle();

    @GET("wxarticle/list/{id}/{pageNum}/json")
    Observable<WechatContentBean> getWechatContent(@Path("id")int id, @Path("pageNum")int page);

    @GET("project/tree/json")
    Observable<ProjectCategoryBean> getProjectCategory();

    @GET("project/list/{pageNum}/json")
    Observable<ProjectItemBean> getProjectItem(@Path("pageNum")int page, @Query("cid")int cid);
}
