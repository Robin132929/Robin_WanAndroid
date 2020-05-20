package com.robin.robin_wanandroid.adapter.wanandroid;

import com.robin.robin_wanandroid.ui.wanandroid.history.CollectFragment;
import com.robin.robin_wanandroid.ui.wanandroid.history.FootPrintFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HistoryViewPager2Adapter extends FragmentStateAdapter {
    public HistoryViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FootPrintFragment();
        }
        if (position == 1) {
            return new CollectFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
