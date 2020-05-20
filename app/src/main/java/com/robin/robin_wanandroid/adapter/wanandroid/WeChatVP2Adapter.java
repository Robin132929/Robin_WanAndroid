package com.robin.robin_wanandroid.adapter.wanandroid;

import com.robin.robin_wanandroid.ui.wanandroid.explore.WechatArticleListFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class WeChatVP2Adapter extends FragmentStateAdapter {
    private int itemCount;
    public WeChatVP2Adapter(@NonNull Fragment fragment,int itemCount) {
        super(fragment);
        this.itemCount=itemCount;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new WechatArticleListFragment();
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }
}
