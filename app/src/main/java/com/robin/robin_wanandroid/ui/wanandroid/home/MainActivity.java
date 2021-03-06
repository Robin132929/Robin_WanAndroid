package com.robin.robin_wanandroid.ui.wanandroid.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.robin.rbase.CommonBase.Activity.BaseActivity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.ui.gank.GankMainFragment;
import com.robin.robin_wanandroid.ui.readhub.ReadhubMainFragment;
import com.robin.robin_wanandroid.ui.wanandroid.explore.ExploreFragment;
import com.robin.robin_wanandroid.ui.wanandroid.history.HistoryFragment;
import com.robin.robin_wanandroid.ui.wanandroid.home.fragment.WanAndroidMainFragment;
import com.robin.robin_wanandroid.ui.wanandroid.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView  mBottomNavigationView;
    @BindView(R.id.fl_content_container)
    FrameLayout flContentContainer;

    protected List<Fragment> fragments=new ArrayList<>();
    private static int lastPos;
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
     initBottomNavigationView();
     getSupportFragmentManager().beginTransaction().add(R.id.fl_content_container,fragments.get(0)).show(fragments.get(0)).commit();
    }

    private void initBottomNavigationView() {
        initFragments();
        mBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int ViewId = item.getItemId();
                switch (ViewId) {
                    case R.id.action_home:
                        //首页
                        conversionPage(0);
                        break;
                    case R.id.action_explore:
                        //探索
                        conversionPage(1);
                        break;
                    case R.id.action_history:
                        //足迹
                        conversionPage(2);
                        break;
                    case R.id.action_mine:
                        //我的
                        conversionPage(3);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    private void conversionPage(int position) {
        if (position>=fragments.size()){
            return;
        }
        if (position == lastPos) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = fragments.get(position);
        Fragment lastFg = fragments.get(lastPos);
        lastPos = position;
        if (lastFg!=null){
            ft.hide(lastFg);
        }else {
            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            if (fragmentList != null) {
                for (Fragment fragment : fragmentList) {
                    if (fragment != null && fragment != fragments.get(position)) {
                        ft.hide(fragment);
                    }
                }
            }
        }
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.fl_content_container, targetFg,targetFg.getClass().getName());
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    private void initFragments() {
        fragments.add(new WanAndroidMainFragment());
        fragments.add(new ExploreFragment());
        fragments.add(new HistoryFragment());
        fragments.add(new MineFragment());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fl_container;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean useFragment() {
        return true;
    }
}
