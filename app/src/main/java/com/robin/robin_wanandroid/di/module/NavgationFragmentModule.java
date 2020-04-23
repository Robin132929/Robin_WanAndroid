package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.NavgationAdapter;
import com.robin.robin_wanandroid.entity.NavgationSection;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.NavgationContract;
import com.robin.robin_wanandroid.ui.wanandroid.fragment.NavigationFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
@Module
abstract class NavgationFragmentModule {
    @Provides
    static NavgationContract.View provideView(NavigationFragment fragment) {
        return fragment;
    }

//    @Provides
//    static NavgationContract.Model provideModel(NavgationModel homeModel) {
//        return homeModel;
//    }

    @Provides
    static NavgationAdapter provider(List<NavgationSection> data){return new NavgationAdapter(R.layout.item_section_content, R.layout.navgation_section_head,data);}

    @Provides
    static List<NavgationSection> providerListNavgationSection(){
        return  new ArrayList<>();
    }
}
