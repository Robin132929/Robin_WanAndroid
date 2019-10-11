package com.robin.robin_wanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpActivity;
import com.robin.robin_wanandroid.adapter.HomeAdapter;
import com.robin.robin_wanandroid.mvp.contract.MainContract;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.presenter.MainPresenter;
import com.robin.robin_wanandroid.mvp.ui.BlankFragment;
import com.robin.robin_wanandroid.mvp.ui.HomeFragment;
import com.robin.robin_wanandroid.mvp.ui.MainFragment;
import com.robin.robin_wanandroid.util.statusbarUtil.StatusBarUtil;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    private BottomNavigationView mBottomNavigationView;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private HomeFragment mHomeFragment;
    private MainFragment mMainFragment;
    private BlankFragment mBlankFragment;
    private CoordinatorLayout mCoordinatorLayout;
    private FragmentTransaction fm;
    private Button search_btn;
    private TextView nav_username;

    @Override
    public void initView(Bundle savedInstanceState) {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        Immersive();
        mToolbar = findViewById(R.id.toolbar);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mFloatingActionButton = findViewById(R.id.float_action_btn);
        mNavigationView = findViewById(R.id.nv_view);
        mDrawerLayout = findViewById(R.id.main_dl);
        mCoordinatorLayout = findViewById(R.id.cl_layout);
        mCoordinatorLayout.setPadding(0, statusBarHeight, 0, 0);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
       FrameLayout.LayoutParams layoutParams=new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -1);
    }

    private void Immersive() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
//        StatusBarUtil.setStatusBarColor(this,R.color.colorPrimary);

        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        fm = getSupportFragmentManager().beginTransaction();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        search_btn = mToolbar.findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
            }
        });
        mBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
       nav_username= mNavigationView.getHeaderView(0).findViewById(R.id.tv_username);
       nav_username.setText(mNavigationView.getMenu().findItem(R.id.nav_logout).isVisible() ? "abc":"登陆");
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_collect:
                        Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_todo:
                        Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_night_mode:
                        Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_about_us:
                        Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        showFragment(0);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeFragment.scollerToTop();
            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int ViewId = item.getItemId();
                switch (ViewId) {
                    case R.id.action_home:
                        showFragment(0);
                        break;
                    case R.id.action_knowledge_system:
                        showFragment(1);
                        break;
                    case R.id.action_navigation:
                        showFragment(2);
                        break;
                    case R.id.action_wechat:
                        showFragment(3);
                        break;
                    case R.id.action_project:
                        showFragment(4);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void showFragment(int index) {
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        hideFragments(fm);
        if (index == 1) {
            if (mHomeFragment==null) {
                mHomeFragment = HomeFragment.newInstance();
                fm.add(R.id.fl_content_container, mHomeFragment, "home");
            } else {
                fm.show(mHomeFragment);
            }
        }
        if (index == 0) {
            if (mMainFragment==null) {
                mMainFragment = MainFragment.newInstance();
                fm.add(R.id.fl_content_container, mMainFragment, "main");
            } else {
                fm.show(mMainFragment);
            }
        }
        if (index == 2) {
            if (mBlankFragment == null) {
                mBlankFragment = BlankFragment.newInstance("", "");
                fm.add(R.id.fl_content_container, mBlankFragment, "home1");
            } else {
                fm.show(mBlankFragment);
            }
        }
        if (index == 3) {
            if (mBlankFragment == null) {
                mBlankFragment = BlankFragment.newInstance("", "");
                fm.add(R.id.fl_content_container, mBlankFragment, "blank");
            } else {
                fm.show(mBlankFragment);
            }
        }
        if (index == 4) {
            if (mBlankFragment == null) {
                mBlankFragment = BlankFragment.newInstance("", "");
                fm.add(R.id.fl_content_container, mBlankFragment, "blank1");
            } else {
                fm.show(mBlankFragment);
            }
        }
        fm.commit();
    }

    private void hideFragments(FragmentTransaction fm) {
        if (mHomeFragment != null) {
            fm.hide(mHomeFragment);
        }
        if (mMainFragment != null) {
            fm.hide(mMainFragment);
        }
        if (mBlankFragment != null) {
            fm.hide(mBlankFragment);
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_trace:
                Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_collect:
                Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_todo:
                Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_night_mode:
                Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
//        return super.onOptionsItemSelected(item);
        return true;
    }
}
