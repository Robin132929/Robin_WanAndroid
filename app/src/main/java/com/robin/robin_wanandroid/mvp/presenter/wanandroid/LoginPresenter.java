package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.LoginContract;
import com.robin.robin_wanandroid.mvp.model.bean.LoginDataBean;
import com.robin.robin_wanandroid.rx.LoginEvent;
import com.robin.robin_wanandroid.rx.RxBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View> implements LoginContract.Presenter {
    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void login(String name, String password) {
        mModel.login(name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<LoginDataBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(LoginDataBean loginDataBean) {
                     mView.login(loginDataBean.getData().getUsername(),"");
                        RxBus.getInstance().post(new LoginEvent(true,loginDataBean.getData().getUsername()));
                        Logger.i("login "+loginDataBean.toString());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }
}
