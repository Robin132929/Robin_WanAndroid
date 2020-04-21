package com.robin.robin_wanandroid.ui.common.activty;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.AppBarLayout;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.base.RobinBaseActivity;
import com.robin.robin_wanandroid.factory.NavigationFragmentFactory;
import com.robin.robin_wanandroid.mvp.contract.common.NavigationViewContract;
import com.robin.robin_wanandroid.mvp.presenter.common.NavigationViewPresenter;

public class NavigationActivity extends RobinBaseActivity<NavigationViewPresenter> implements NavigationViewContract.View {
    @BindView(R.id.general_toolbar)
    Toolbar generalToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    private BaseFragment currentFragment=null;

    public static void onStartActivty(Class clz, Context context) {
        Intent intent = new Intent(context, NavigationActivity.class);
        intent.putExtra("class", clz);
        context.startActivity(intent);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        Class clz = intent.getParcelableExtra("class");
            currentFragment=NavigationFragmentFactory.create(clz,mPresenter);
        if (currentFragment!=null){
            initToolbar(generalToolbar);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.page_content_fl, currentFragment);
            ft.show(currentFragment);
            ft.commit();
        }
    }
    private void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

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
    public void showCollectList() {

    }

    @Override
    public void showBrowsingHistory() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
