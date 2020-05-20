package com.robin.robin_wanandroid.ui.common.activty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.CommonUtils.Utils.PreferUtil;
import com.robin.rbase.CommonUtils.Utils.ToastUtils;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.contract.common.MainContract;
import com.robin.robin_wanandroid.mvp.presenter.common.MainActivityPresenter;
import com.robin.robin_wanandroid.ui.wanandroid.history.CollectFragment;
import com.robin.robin_wanandroid.ui.wanandroid.history.FootPrintFragment;
import com.robin.robin_wanandroid.ui.gank.GankMainFragment;
import com.robin.robin_wanandroid.ui.wanandroid.home.fragment.WanAndroidHomeFragment;
import com.robin.robin_wanandroid.ui.wanandroid.home.fragment.WanAndroidMainFragment;
import com.robin.robin_wanandroid.ui.login.LoginActivity;
import com.robin.robin_wanandroid.annotation.CheckLogin;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.base.RobinBaseActivity;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.ui.BlankFragment;
import com.robin.robin_wanandroid.rx.ColorEvent;
import com.robin.robin_wanandroid.rx.LoginEvent;
import com.robin.robin_wanandroid.rx.RefreshHomeEvent;
import com.robin.robin_wanandroid.rx.RxBus;
import com.robin.robin_wanandroid.ui.readhub.ReadhubMainFragment;
import com.robin.robin_wanandroid.util.FragmentPageManager;
import com.robin.robin_wanandroid.util.Login.LoginHelper;
import com.robin.robin_wanandroid.util.Login.Offline;
import com.robin.robin_wanandroid.util.Login.Online;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class WanAndroidMainActivity extends RobinBaseActivity<MainActivityPresenter> implements MainContract.View {
    private static final String TAG = "MainActivity";
    @Inject
    protected List<Fragment> fragments;
//    @Inject
    WanAndroidMainFragment mMainFragment=new WanAndroidMainFragment();
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.app_bar_layout)
//    AppBarLayout appBarLayout;
//    @BindView(R.id.fl_content_container)
//    FrameLayout flContentContainer;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.float_action_btn)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.cl_layout)
    CoordinatorLayout clLayout;
    @BindView(R.id.nv_view)
    NavigationView mNavigationView;
    @BindView(R.id.main_dl)
    DrawerLayout mainDl;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private TextView nav_username;
    private int PrePosition;

    @SuppressLint("WrongConstant")
    @Override
    public void initView(Bundle savedInstanceState) {
        RxBusSubscriber();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        Logger.e("view is "+(toolbar==null)+"  "+(searchBtn==null)+"  "+(mainDl==null));
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Main6Activity.class));
//                Toast.makeText(MainActivity.this, "实现中。。。", Toast.LENGTH_LONG).show();
//            }
//        });

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mainDl, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mActionBarDrawerToggle.syncState();
        mainDl.addDrawerListener(mActionBarDrawerToggle);
        nav_username = mNavigationView.getHeaderView(0).findViewById(R.id.tv_username);

        if (LoginHelper.getInstance().getState().getType() == Offline.LOGOUT) {
            mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
            nav_username.setText(R.string.login);
            nav_username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(WanAndroidMainActivity.this, LoginActivity.class));
                }
            });
        } else {
            mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            nav_username.setText(LoginHelper.getInstance().getState().getName());
        }
        setNavigationViewListener();
        setBottomNavigationFragment();
        setFloatingActionButtonListener();
        setBottomNavigationViewListener();
    }

    private void setFloatingActionButtonListener() {
        //TODO 一键返回顶部 已实现2019-10-25
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Fragment f = FragmentPageManager.getFragmentPageManager().getCurrentFragment();
                Logger.i("FragmentLifecycleCallbacksImpl111: current122 click " + f.getClass().getSimpleName());
                if (f instanceof ScrollTopListener) {
                    ((ScrollTopListener) f).scroll2Top();
                }
            }
        });
    }

    private void setBottomNavigationFragment() {
        //首页（最新博文、项目、）
        fragments.add(mMainFragment);
        //阅读记录/收藏
        fragments.add(new GankMainFragment());
        //
        fragments.add(new ReadhubMainFragment());
        //我的/设置
        fragments.add(new BlankFragment());

        addFragmnets(R.id.fl_content_container, fragments, 0);
    }

    private void addFragmnets(int ResId, List<Fragment> fragments, int showID) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            ft.add(ResId, fragments.get(i), fragments.get(i).getClass().getName());
            if (i != showID) {
                ft.hide(fragments.get(i));
            }
        }
        ft.commit();
    }

    private void setBottomNavigationViewListener() {
        mBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int ViewId = item.getItemId();
                switch (ViewId) {
                    case R.id.action_home:
                        showAndhideFragment(PrePosition, 0);
                        PrePosition = 0;
                        break;
                    case R.id.action_explore:
                        showAndhideFragment(PrePosition, 1);
                        PrePosition = 1;
                        break;
                    case R.id.action_history:
                        showAndhideFragment(PrePosition, 2);
                        PrePosition = 2;
                        break;
//                    case R.id.action_wechat:
//                        showAndhideFragment(PrePosition, 3);
//                        PrePosition = 3;
//                        break;
//                    case R.id.action_project:
//                        showAndhideFragment(PrePosition, 4);
//                        PrePosition = 4;
//                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void showAndhideFragment(int hidePosition, int showPosition) {
        Logger.i("form :" + hidePosition + "to :" + showPosition);
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        if (showPosition == hidePosition) return;
        fm.show(fragments.get(showPosition));

        Fragment hideFragment = fragments.get(hidePosition);
        if (hideFragment == null) {
            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            if (fragmentList != null) {
                for (Fragment fragment : fragmentList) {
                    if (fragment != null && fragment != fragments.get(showPosition)) {
                        fm.hide(fragment);
                    }
                }
            }
        } else {
            fm.hide(hideFragment);
        }
        fm.commit();
    }

    private void setNavigationViewListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_collect:
                        routerTocollect();
                        break;
                    case R.id.nav_night_mode:
                        //todo 夜间模式需要优化实现方式 目前方式需要重启activty
                        if (!(boolean) PreferUtil.get(App.getmMyAppComponent().application(), "night_mode", false)) {
                            PreferUtil.persist(App.getmMyAppComponent().application(), "night_mode", true);
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        } else {
                            PreferUtil.persist(App.getmMyAppComponent().application(), "night_mode", false);
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        }
//                        startActivity(new Intent(MainActivity.this, MainActivity.class));
//                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                        finish();
                        recreate();
                        break;
                    case R.id.nav_setting:
//                        ConvenientEntranceActivity.onStartActivty( WanAndroidMainActivity.class,getApplicationContext());
                        break;
                    case R.id.nav_logout:
                        if (LoginHelper.getInstance().getState().getType() == Online.LOGIN) {
                            logout();
                        } else {
                            ToastUtils.showLong("未登录!!!");
                        }

                        break;
                    case R.id.nav_footprint:
                        goFootPrint();
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @CheckLogin(action = CheckLogin.Action.JUMP)
    private void goFootPrint() {
        NavigationActivity.onStartActivty(FootPrintFragment.class, WanAndroidMainActivity.this);
    }

    @CheckLogin(action = CheckLogin.Action.JUMP)
    public void routerTocollect() {
        NavigationActivity.onStartActivty(CollectFragment.class, WanAndroidMainActivity.this);
    }

    private void logout() {
        mPresenter.logout();
    }

    @SuppressLint("CheckResult")
    public void RxBusSubscriber() {

        RxBus.getInstance().toObservable(this, LoginEvent.class).subscribe(new Consumer<LoginEvent>() {
            @Override
            public void accept(LoginEvent loginEvent) throws Exception {
                WanAndroidMainFragment ma = (WanAndroidMainFragment) fragments.get(0);

                if (loginEvent.isLogin) {
                    nav_username.setText(loginEvent.name);
//                    ma.lazyLoadData();
                    mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
                } else {
                    nav_username.setText("登录");
//                    ma.lazyLoadData();
                    mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
                }
            }
        });
        RxBus.getInstance().toObservable(this, RefreshHomeEvent.class).subscribe(new Consumer<RefreshHomeEvent>() {
            @Override
            public void accept(RefreshHomeEvent refreshHomeEvent) throws Exception {
                WanAndroidMainFragment ma = (WanAndroidMainFragment) fragments.get(0);

                if (refreshHomeEvent.isRefresh) {
                    Logger.i("refesh home");
                    for (Fragment fragment : ma.getChildFragmentManager().getFragments()) {
                        if (fragment instanceof WanAndroidHomeFragment) {
//                            ((WanAndroidHomeFragment) fragment).lazyLoadData();
                        }
                    }
                }
            }
        });

        RxBus.getInstance().toObservable(this, ColorEvent.class).subscribe(new Consumer<ColorEvent>() {
            @Override
            public void accept(ColorEvent colorEvent) throws Exception {
                Logger.i("color is " + colorEvent.Color + "   " + colorEvent.colorInt + "   " + getResources().getColor(R.color.ALIZARIN));

//               int color= Color.valueOf(colorEvent.colorInt);
                initColor(getResources().getColor(colorEvent.colorInt));
            }
        });

    }

    @Override
    public void initColor(int color) {
        super.initColor(color);
        mNavigationView.getHeaderView(0).setBackgroundColor(color);
        mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    //TODO loading view 已实现2019-10-24
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showLogoutSuccess(boolean success) {
        if (success) {
            PreferUtil.persist(App.getmMyAppComponent().application(), "login", false);
            PreferUtil.persist(App.getmMyAppComponent().application(), "name", "");
            RxBus.getInstance().post(new LoginEvent(false, ""));
            startActivity(new Intent(WanAndroidMainActivity.this, LoginActivity.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.e("activity pause");
    }
}
