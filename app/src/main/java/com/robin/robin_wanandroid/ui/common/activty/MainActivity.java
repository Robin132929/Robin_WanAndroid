package com.robin.robin_wanandroid.ui.common.activty;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.robin.rbase.CommonBase.Activity.BaseActivity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.base.RobinBaseActivity;
import com.robin.robin_wanandroid.ui.gank.GankMainFragment;
import com.robin.robin_wanandroid.ui.readhub.ReadhubMainFragment;
import com.robin.robin_wanandroid.ui.wanandroid.fragment.WanAndroidMainFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

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
     getSupportFragmentManager().beginTransaction().add(R.id.fl_content_container,fragments.get(0)).commit();
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
                        conversionPage(0);
                        break;
                    case R.id.action_knowledge_system:
                        conversionPage(1);
                        break;
                    case R.id.action_navigation:
                        conversionPage(2);
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
        fragments.add(new GankMainFragment());
        fragments.add(new ReadhubMainFragment());
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
