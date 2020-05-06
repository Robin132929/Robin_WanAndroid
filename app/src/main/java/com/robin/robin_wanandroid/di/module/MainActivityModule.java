package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.mvp.contract.common.MainContract;
import com.robin.robin_wanandroid.ui.common.activty.WanAndroidMainActivity;
import com.robin.robin_wanandroid.mvp.model.wanandroid.MainModel;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.ui.wanandroid.home.fragment.WanAndroidMainFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

@Module
abstract class MainActivityModule {

    @Provides
    static List<Fragment> providFragmentList(){
        return new ArrayList<>();
    }

    @Provides
    static MainArticleBean provideDatebean(){
        return new MainArticleBean();

    }
    @Provides
    static WanAndroidMainFragment providMainFragment(){
        return new WanAndroidMainFragment();
    }
    @Provides
    static MainContract.View provideView(WanAndroidMainActivity activity) {
        return activity;
    }
//    @Binds
//    abstract MainContract.Model bindUserModel(MainModel model);
    @Provides
static MainContract.Model provideModel(MainModel homeModel) {
    return homeModel;
}
//    @Provides
//    static MainActivity provideMainActivtyView(MainActivity activity) {
//        return activity;
//    }

//
//    @Binds
//    abstract IRepositoryManager bindRepositoryManagers(RepositoryManager repositoryManager);
////    @Provides
//    static RxErrorHandler provideRxError(){
//        return  RxErrorHandler
//                .builder()
//                .with()
//                .responseErrorListener(new ResponseErrorListener() {
//                    @Override
//                    public void handleResponseError(Context context, Throwable t) {
//
//                    }
//                })
//                .build();
//    }
//    @Binds
//    @Named("ActivityLifecycle")
//    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycle(abc activityLifecycle);
//
//    @Binds
//    @Named("ActivityLifecycleForRxLifecycle")
//    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycleForRxLifecycle(abcd activityLifecycleForRxLifecycle);

}
