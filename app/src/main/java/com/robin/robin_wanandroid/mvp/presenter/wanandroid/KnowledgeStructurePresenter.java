package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.KnowledgeStructureContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;
import com.robin.robin_wanandroid.mvp.model.common.DataManager;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class KnowledgeStructurePresenter extends BasePresenter<DataManager,KnowledgeStructureContract.View> implements KnowledgeStructureContract.Presenter {

    @Inject
    public KnowledgeStructurePresenter(DataManager model, KnowledgeStructureContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onCreate() {
        //打开 App 时自动加载列表
//        requestStructureList();
//        requestBanner();
//        requestStructureItem(0,60);
    }
    @Override
    public void requestStructureList() {
        mView.showLoading();
        mModel.getKnowledgeStructure().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<KnowledgeStructureBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(KnowledgeStructureBean knowledgeStructureBean) {
                        Logger.i("konw request ");
                        mView.hideLoading();
                        requestStructureItem(0,knowledgeStructureBean.getData().get(0).getChildren().get(0).getId(),false);
                        mView.setStructureList1st(knowledgeStructureBean);
                        mView.setStructureList2st(knowledgeStructureBean.getData().get(0));

                    }
                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });

    }

    @Override
    public void requestStructureItem(int page,final int cid,boolean More) {
        mModel.getKnowledgeStructureItem(page,cid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
//                        mView.showLoading();
                    }
                })
                .subscribe(new ErrorHandleSubscriber<KnowledgeArticleBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(KnowledgeArticleBean knowledgeArticleBean) {
                        Logger.i("request item"+knowledgeArticleBean.getErrorMsg()+"   " +page+ " "+cid+ " "+knowledgeArticleBean.toString());
//                   mView.hideLoading();
                    mView.setStructureItem(knowledgeArticleBean,knowledgeArticleBean.getData().getCurPage()-1,More);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mView.showError();
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
//                        mView.hideLoading();
                    }
                });

    }

//    @Override
//    public void requestBanner() {
//        mModel.getBanner().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<BannerBean>(App.getmMyAppComponent().rxErrorHandler()) {
//            @Override
//            public void onNext(BannerBean dataBeans) {
//                mView.setBanner(dataBeans);
//            }
//        });
//    }
}
