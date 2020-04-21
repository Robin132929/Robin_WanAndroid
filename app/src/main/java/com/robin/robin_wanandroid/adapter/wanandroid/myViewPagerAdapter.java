package com.robin.robin_wanandroid.adapter.wanandroid;

import com.robin.robin_wanandroid.ui.wanandroid.fragment.WanAndroidHomeFragment;
import com.robin.robin_wanandroid.ui.wanandroid.fragment.KnowledgeStructureFragment;
import com.robin.robin_wanandroid.ui.wanandroid.fragment.NavigationFragment;
import com.robin.robin_wanandroid.ui.wanandroid.fragment.ProjectFragment;
import com.robin.robin_wanandroid.ui.wanandroid.fragment.WechatFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * FragmentStatePagerAdapter在覆写getItem方法中返回的Fragment，不可以使用list中获取的。因为该adapter内部维持一个ArrayList，
 * 每次instantiateItem时，会先从List中取position位置的Fragment返回，如果没有，则会getItem()创建Fragment，存放在List中
 * destroyItem 时，则在List中set(position，null)；list中一直有Fragment的引用导致Fragment无法被回收从而导致内存泄漏，
 * 解决方法就是在getItem中new一个新的Fragment
 * ————————————————
 */
public class myViewPagerAdapter extends FragmentPagerAdapter {

    public myViewPagerAdapter( @NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
           return new NavigationFragment();
        }else if (position==1){
            return new WanAndroidHomeFragment();
        }else if (position==2){
          return new KnowledgeStructureFragment();
        }else if (position==3){
          return new WechatFragment();
        }else if (position==4){
          return new ProjectFragment();
        }
        return new WanAndroidHomeFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
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

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return super.getItemPosition(object);
//    }
}
