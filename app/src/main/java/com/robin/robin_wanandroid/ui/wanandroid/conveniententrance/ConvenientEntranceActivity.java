package com.robin.robin_wanandroid.ui.wanandroid.conveniententrance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.AppBarLayout;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.base.RobinBaseActivity;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.customize_interface.PageType;
import com.robin.robin_wanandroid.customize_interface.RealPageFactory;
import com.robin.robin_wanandroid.factory.ConvenientEntranceFactory;
import com.robin.robin_wanandroid.mvp.contract.ConvenientContract;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

public class ConvenientEntranceActivity extends RobinBaseActivity {
    @BindView(R.id.general_toolbar)
    Toolbar generalToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    private RobinBaseFragment currentFragment;

    public static void onStartActivty(Class<? extends RobinBaseFragment > clz, Context context) {
        Intent intent = new Intent(context, ConvenientEntranceActivity.class);
        intent.putExtra("class", clz);
        context.startActivity(intent);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        //toolbar
        setSupportActionBar(generalToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Class clz = intent.getParcelableExtra("class");
        currentFragment=ConvenientEntranceFactory.creatFragment(clz);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fl_page_content, currentFragment);
            ft.show(currentFragment);
            ft.commit();
            //TODO title设置
//        generalToolbar.setTitle(currentFragment.getTitle());
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
}
