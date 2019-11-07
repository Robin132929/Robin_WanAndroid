package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.WechatContract;
import com.robin.robin_wanandroid.mvp.model.bean.WechatContentBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatTitleBean;
import com.robin.robin_wanandroid.mvp.model.cache.CommonCache;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKeyGroup;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

public class WechatModel extends BaseModel implements WechatContract.Model {
    @Inject
    public WechatModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<WechatTitleBean> requestWechatTitle() {
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
    public Observable<WechatContentBean> requestWechatContent(int id, int page, boolean isRefresh) {
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
}
