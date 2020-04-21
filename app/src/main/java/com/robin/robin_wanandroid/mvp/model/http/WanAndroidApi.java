package com.robin.robin_wanandroid.mvp.model.http;

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

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WanAndroidApi {
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

    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginDataBean> getLoginData(@Field("username") String username, @Field("password") String password);

    @GET("user/logout/json")
    Observable<LogoutResultBean> logout();

    @POST("lg/collect/{id}/json")
    Observable<AddCollectBean> addCollectArticle(@Path("id") int id);

    @POST("lg/uncollect_originId/{id}/json")
    Observable<AddCollectBean> cancelCollectArticle(@Path("id") int id );
    /**
     * 收藏列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect/2805/json
     * @param id
     * @param originId
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<AddCollectBean> removeCollectArticle(@Path("id") int id,
                                                    @Field("originId")int originId);


    @GET("lg/collect/list/{page}/json")
    Observable<GetCollectBean> getCollectList(@Path("page") int  page);

    @POST("lg/collect/add/json")
    Observable<AddCollectBean> addExternalLink(@Query("title") String title,@Query("author") String author,@Query("link") String link);


    @POST("lg/collect/addtool/json")
    Observable<AddCollectBean> addFootPrint(@Query("name") String name,@Query("link") String link);



    @GET("wenda/list/{page}/json")
    Observable<wendaBean> getWendaList(@Path("page")int page);

    @GET("lg/collect/usertools/json")
    Observable<FootPrintBean> getFootPrintList();

    //面试集锦https://www.wanandroid.com/article/list/0/json?cid=73
    //积分榜 https://www.wanandroid.com/coin/rank/1/json
    //个人积分https://www.wanandroid.com/lg/coin/userinfo/json
    //个人积分获取详情https://www.wanandroid.com//lg/coin/list/1/json

    //常用网址https://www.wanandroid.com/friend/json
}
