package com.robin.robin_wanandroid.ui.conveniententrance.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.AppBarLayout;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.base.MyBaseActivity;
import com.robin.robin_wanandroid.customize_interface.PageType;
import com.robin.robin_wanandroid.customize_interface.RealPageFactory;
import com.robin.robin_wanandroid.mvp.contract.ConvenientContract;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.ConvenientPresenter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

public class ConvenientEntranceActivity extends MyBaseActivity<ConvenientPresenter> implements ConvenientContract.View {
    @BindView(R.id.general_toolbar)
    Toolbar generalToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    private PageType mPage;
    private RealPageFactory realPageFactory = new RealPageFactory();

    public static void onStartActivty(String clz, Context context) {
        Intent intent = new Intent(context, ConvenientEntranceActivity.class);
        intent.putExtra("class", clz);
        context.startActivity(intent);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        String clz = intent.getStringExtra("class");
        //TODO  如何避免mPageType为空情况下的crash
        mPage = realPageFactory.creatPage(clz);
        if (mPage != null) {
            initToolbar(generalToolbar);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.page_content_fl, (BaseFragment)mPage);
            ft.show((BaseFragment)mPage);
            ft.commit();
            mPage.setPresenter(mPresenter);
        }
    }

    private void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPage.initToolbar(getSupportActionBar());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_convenient_button_content;
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
    public void setData(Object data,Object... agrs) {
        mPage.setData(data);
    }
}
