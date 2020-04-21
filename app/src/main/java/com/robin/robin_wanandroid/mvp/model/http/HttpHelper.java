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

/**
 * 网络请求管理类
 */
public interface HttpHelper {

    interface WanAndroidApi {
        Observable<MainArticleBean> getMainArt(int page);

        Observable<BannerBean> getBanner();

        Observable<NavgationBean> getNavgationData();

        Observable<KnowledgeStructureBean> getKnowledgeStructure();

        Observable<KnowledgeArticleBean> getKnowledgeStructureItem(int page, int cid);

        Observable<WechatTitleBean> getWechatTitle();

        Observable<WechatContentBean> getWechatContent(int id,int page);

        Observable<ProjectCategoryBean> getProjectCategory();

        Observable<ProjectItemBean> getProjectItem(int page, int cid);

        Observable<LoginDataBean> getLoginData( String username,  String password);

        Observable<LogoutResultBean> logout();

        Observable<AddCollectBean> addCollectArticle( int id);

        Observable<AddCollectBean> cancelCollectArticle(int id );
        /**
         * 收藏列表中取消收藏文章
         * http://www.wanandroid.com/lg/uncollect/2805/json
         * @param id
         * @param originId
         */
        Observable<AddCollectBean> removeCollectArticle( int id, int originId);

        Observable<GetCollectBean> getCollectList(int  page);

        Observable<AddCollectBean> addExternalLink( String title,String author, String link);

        Observable<AddCollectBean> addFootPrint(String name,String link);

        Observable<wendaBean> getWendaList(int page);

        Observable<FootPrintBean> getFootPrintList();

        //面试集锦https://www.wanandroid.com/article/list/0/json?cid=73
        //积分榜 https://www.wanandroid.com/coin/rank/1/json
        //个人积分https://www.wanandroid.com/lg/coin/userinfo/json
        //个人积分获取详情https://www.wanandroid.com//lg/coin/list/1/json

        //常用网址https://www.wanandroid.com/friend/json
    }
  interface GankApi {
  }

  interface ReadhunApi{

  }
}
