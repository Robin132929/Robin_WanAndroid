package com.robin.robin_wanandroid.adapter;

import com.robin.robin_wanandroid.mvp.model.bean.WeChatBean;
import com.robin.robin_wanandroid.mvp.ui.HomeFragment;
import com.robin.robin_wanandroid.mvp.ui.KnowledgeStructureFragment;
import com.robin.robin_wanandroid.mvp.ui.NavigationFragment;
import com.robin.robin_wanandroid.mvp.ui.ProjectFragment;
import com.robin.robin_wanandroid.mvp.ui.WechatFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class myViewPagerAdapter extends FragmentStatePagerAdapter{
   private List<Fragment> fragmnets=new ArrayList<>();
    public myViewPagerAdapter(List<Fragment> fragments, @NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.fragmnets=fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmnets.get(position);
    }

    @Override
    public int getCount() {
        return fragmnets.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return data.getData().get(position).getName();
       if (position==0){
           return "导航";
       }
        if (position==1){
            return "推荐";
        }
        if (position==2){
            return "体系";
        }
        if (position==3){
            return "公众号";
        }
        if (position==4){
            return "项目";
        }
        return null;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}