package com.robin.robin_wanandroid.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.functions.Consumer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.CommonUtils.Utils.PreferUtil;
import com.robin.rbase.MVP.MvpBase.BaseMvpActivity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.dummy.DummyContent;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.MainContract;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.MainPresenter;
import com.robin.robin_wanandroid.mvp.ui.BlankFragment;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.MainFragment;
import com.robin.robin_wanandroid.mvp.ui.gank.GankMainFragment;
import com.robin.robin_wanandroid.mvp.ui.readhub.ReadhubMainFragment;
import com.robin.robin_wanandroid.rx.LoginEvent;
import com.robin.robin_wanandroid.rx.RxBus;
import com.robin.robin_wanandroid.util.FragmentPageManager;
import com.robin.robin_wanandroid.util.statusbarUtil.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View, ItemFragment.OnListFragmentInteractionListener {
    private BottomNavigationView mBottomNavigationView;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private FragmentTransaction fm;
    private Button search_btn;
    private TextView nav_username;
    private int PrePosition;
    private List<Fragment> fragments = new ArrayList<>();
    private String username= (String) PreferUtil.get(App.getmMyAppComponent().application(),"name","");
    private boolean isLogin= (boolean) PreferUtil.get(App.getmMyAppComponent().application(),"login",false);

    @SuppressLint("WrongConstant")
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
        RxBusSubscriber();
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
        if (!isLogin){
            mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        }
        nav_username = mNavigationView.getHeaderView(0).findViewById(R.id.tv_username);
        nav_username.setText(mNavigationView.getMenu().findItem(R.id.nav_logout).isVisible() ? username: "登陆");
        nav_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLogin){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_collect:
                        Intent intent=new Intent(MainActivity.this,SlidingMenuDetailActivity.class);
                        intent.putExtra("type",0x0001);
                        startActivity(intent);
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
                        logout();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        fragments.add(new MainFragment());
        fragments.add(new GankMainFragment());
        fragments.add(new ReadhubMainFragment());
        fragments.add(ItemFragment.newInstance(1));
        fragments.add(new BlankFragment());

        addFragmnets(R.id.fl_content_container, fragments, 0);
        //TODO 一键返回顶部 已实现2019-10-25
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Fragment f = FragmentPageManager.getFragmentPageManager().getCurrentFragment();
                Logger.i("current122 click " + f.getClass().getSimpleName());
                if (f instanceof ScrollTopListener) {
                    ((ScrollTopListener) f).scroll2Top();
                }
            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int ViewId = item.getItemId();
                switch (ViewId) {
                    case R.id.action_home:
                        showAndhideFragment(PrePosition, 0);
                        PrePosition = 0;
                        break;
                    case R.id.action_knowledge_system:
                        showAndhideFragment(PrePosition, 1);
                        PrePosition = 1;
                        break;
                    case R.id.action_navigation:
                        showAndhideFragment(PrePosition, 2);
                        PrePosition = 2;
                        break;
                    case R.id.action_wechat:
                        showAndhideFragment(PrePosition, 3);
                        PrePosition = 3;
                        break;
                    case R.id.action_project:
                        showAndhideFragment(PrePosition, 4);
                        PrePosition = 4;
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void logout() {
        mPresenter.logout();
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

    private void showAndhideFragment(int hidePosition, int showPosition) {
        Logger.i("form :"+hidePosition +"to :"+showPosition);
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
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public void RxBusSubscriber(){
        RxBus.getInstance().toObservable(this, LoginEvent.class).subscribe(new Consumer<LoginEvent>() {
            @Override
            public void accept(LoginEvent loginEvent) throws Exception {
                MainFragment ma= (MainFragment) fragments.get(0);

                if (loginEvent.isLogin){
                    nav_username.setText(loginEvent.name);
                    ma.lazyLoadData();
                    mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
                }else {
                    nav_username.setText("登录");
                    ma.lazyLoadData();
                    mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
                }
            }
        });
    }

    @Override
    public void showLogoutSuccess(boolean success) {
       if (success){
           PreferUtil.persist(App.getmMyAppComponent().application(),"login",false);
           PreferUtil.persist(App.getmMyAppComponent().application(),"name","");
           RxBus.getInstance().post(new LoginEvent(false,""));
           startActivity(new Intent(MainActivity.this,LoginActivity.class));
       }
    }
}
