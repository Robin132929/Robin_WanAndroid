package com.robin.robin_wanandroid.ui.wanandroid.explore;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.ViewPager2Adapter;
import com.robin.robin_wanandroid.base.RobinBaseFragment;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;

public class ExploreFragment extends BaseFragment {
    @BindView(R.id.tl_explore)
    TabLayout tlExplore;
    @BindView(R.id.vp_explore)
    ViewPager2 vpExplore;

    private ViewPager2Adapter viewPager2Adapter;
    private List<String> title = Arrays.asList("体系", "公众号", "常用网站", "项目");

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPager2Adapter = new ViewPager2Adapter(this);
        vpExplore.setAdapter(viewPager2Adapter);

        new TabLayoutMediator(tlExplore, vpExplore, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setCustomView(R.layout.layout_custom_tabview);
                ((TextView) tab.getCustomView()).setText(title.get(position));
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.Grey800));

            }
        }).attach();

        tlExplore.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextSize(16f);
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.Black));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView()).setTextSize(14f);
                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.Grey800));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_explore;
    }
}
