package com.robin.robin_wanandroid.adapter.wanandroid;

import com.robin.robin_wanandroid.ui.wanandroid.explore.KnowledgeStructureFragment;
import com.robin.robin_wanandroid.ui.wanandroid.explore.NavigationFragment;
import com.robin.robin_wanandroid.ui.wanandroid.explore.ProjectFragment;
import com.robin.robin_wanandroid.ui.wanandroid.explore.WechatFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Adapter extends FragmentStateAdapter {

    public ViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new KnowledgeStructureFragment();
        }
        if (position == 1) {
            return new WechatFragment();
        }
        if (position == 2) {
            return new WechatFragment();
        }
        if (position == 3) {
            return new ProjectFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
