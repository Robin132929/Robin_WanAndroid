package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.CollectContract;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class CollectModel extends CommonModel implements CollectContract.Model {
    @Inject
    public CollectModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<GetCollectBean> getCollectList(int page, boolean isRefresh) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getCollectList(page))
                .flatMap(new Function<Observable<GetCollectBean>, ObservableSource<GetCollectBean>>() {
                    @Override
                    public ObservableSource<GetCollectBean> apply(Observable<GetCollectBean> getCollectBeanObservable) throws Exception {
                        return getCollectBeanObservable;
                    }
                });
    }

    @Override
    public Observable<AddCollectBean> removeCollectState(int id, int originId) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).removeCollectArticle(id, originId))
                .flatMap(new Function<Observable<AddCollectBean>, ObservableSource<AddCollectBean>>() {
                    @Override
                    public ObservableSource<AddCollectBean> apply(Observable<AddCollectBean> addCollectBeanObservable) throws Exception {
                        return addCollectBeanObservable;
                    }
                });
    }

}
