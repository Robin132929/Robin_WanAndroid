package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.HomeContract;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;
import com.robin.robin_wanandroid.mvp.model.common.DataManager;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class HomePresenter extends CommonPresenter<HomeContract.View> implements HomeContract.Presenter {
    private boolean isFirstLoad=true;
    @Inject
    public HomePresenter(DataManager dataManager, HomeContract.View rootView) {
        super(dataManager, rootView);
    }
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    void onCreate() {
//        //自动进行懒加载
//        if (isFirstLoad){
//            requestArticle(0,false);
//         isFirstLoad=false;
//        }
//    }

    @Override
    public void requestArticle(int page, boolean isSave) {
       mModel.getMainArt(page)
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .doOnSubscribe(new Consumer<Disposable>() {
                   @Override
                   public void accept(Disposable disposable) throws Exception {
                       if (page<1) {
                           mView.showLoading();
                       }
                   }
               })
                .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<MainArticleBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(MainArticleBean dataBeans) {
                        Logger.i("get main art is "+dataBeans.toString());
                        mView.hideLoading();
                        mView.setArticle(dataBeans.getData(),isSave);
                    }

           @Override
           public void onComplete() {
               super.onComplete();
           }

           @Override
           public void onError(Throwable t) {
               super.onError(t);
               Logger.e(" wan home is error "+t.getMessage());
               mView.showError();
           }
       });
    }

    @Override
    public void requestTopArticle() {

    }

//    @Override
//    public void requestDailyInterviewQuestion(int page, boolean isRefresh) {
//        mModel.requestDailyInterviewQuestion(page,isRefresh).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxLifecycleUtils.bindToLifecycle(mView))
//                .subscribe(new ErrorHandleSubscriber<wendaBean>(App.getmMyAppComponent().rxErrorHandler()) {
//                    @Override
//                    public void onNext(wendaBean wendaBean) {
//                    }
//                });
//    }


}
