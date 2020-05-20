package com.robin.robin_wanandroid.ui.wanandroid.history;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.HistoryViewPager2Adapter;
import com.robin.robin_wanandroid.base.RobinBaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;

public class HistoryFragment extends BaseFragment {
    @BindView(R.id.tl_history)
    TabLayout tlHistory;
    @BindView(R.id.vp_history)
    ViewPager2 vpHistory;

    private HistoryViewPager2Adapter adapter;
    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    adapter=new HistoryViewPager2Adapter(this);
    vpHistory.setAdapter(adapter);
    new TabLayoutMediator(tlHistory, vpHistory, new TabLayoutMediator.TabConfigurationStrategy() {
        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            tab.setText("足迹"+position);
        }
    }).attach();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_history;
    }
}
