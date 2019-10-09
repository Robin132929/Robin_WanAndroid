package com.robin.robin_wanandroid.mvp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chad.library.adapter.base.layoutmanager.FlowLayoutManager;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.KnowledgeListAdapter;
import com.robin.robin_wanandroid.adapter.ProjectCategoryAdapter;
import com.robin.robin_wanandroid.adapter.ProjectItemAdapter;
import com.robin.robin_wanandroid.mvp.contract.ProjectContract;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;
import com.robin.robin_wanandroid.mvp.presenter.ProjectPresenter;
import com.robin.robin_wanandroid.widget.CustomPopupWindow;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class ProjectFragment extends BaseMvpFragment<ProjectPresenter> implements ProjectContract.View {

    private RecyclerView mCategory_RecyclerView;
    private RecyclerView mItem_RecyclerView;
    private RecyclerView mPopopWindowRecycleView;
    private ToggleButton mToggleButton;
    private ConvenientBanner mConvenientBanner;
    private CustomPopupWindow mExpandPopupWindow;


    private List<ProjectCategoryBean.DataBean> mCategory_list;
    private List<ProjectItemBean.DataBean.DatasBean> mItem_list;

    private ProjectCategoryAdapter mProjectCategoryAdapter;
    private ProjectCategoryAdapter mPopopWindow_ProjectCategoryAdapter;
    private ProjectItemAdapter mProjectItemAdapter;

    private CustomPopupWindow.LayoutGravity layoutGravity;



    public ProjectFragment() {
        mCategory_list=new ArrayList<>();
        mItem_list=new ArrayList<>();
    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategory_RecyclerView=view.findViewById(R.id.rv_list);
        mItem_RecyclerView=view.findViewById(R.id.rv_project_list);
        mToggleButton=view.findViewById(R.id.btn_expand);
        mConvenientBanner=view.findViewById(R.id.project_banner);
        View  contentView= LayoutInflater.from(mContext).inflate(R.layout.popup_list, null, false);

        mExpandPopupWindow= CustomPopupWindow.builder()
                .contentView(contentView)
                .isFocus(true)
                .parentView(mItem_RecyclerView)
                .setHeight(500)
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                    @Override
                    public void initPopupView(View contentView) {
                        mPopopWindowRecycleView=contentView.findViewById(R.id.data_list);
                        mPopopWindowRecycleView.setLayoutManager(new FlowLayoutManager());
                        mPopopWindow_ProjectCategoryAdapter= new ProjectCategoryAdapter(R.layout.top_nav_item, mCategory_list);
                        mPopopWindowRecycleView.setAdapter(mPopopWindow_ProjectCategoryAdapter);
                    }

                }).build();
        mExpandPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mToggleButton.setChecked(false);
            }
        });
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mCategory_RecyclerView.setLayoutManager(linearLayoutManager);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mItem_RecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mProjectCategoryAdapter=new ProjectCategoryAdapter(R.layout.top_nav_item,mCategory_list);
        mProjectItemAdapter=new ProjectItemAdapter(R.layout.top_nav_item,mItem_list);

        mCategory_RecyclerView.setAdapter(mProjectCategoryAdapter);
        mItem_RecyclerView.setAdapter(mProjectItemAdapter);
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mPopopWindow_ProjectCategoryAdapter.notifyDataSetChanged();
                    layoutGravity=new CustomPopupWindow.LayoutGravity(CustomPopupWindow.LayoutGravity.ALIGN_LEFT| CustomPopupWindow.LayoutGravity.TO_BOTTOM);
                    mExpandPopupWindow.showBashOfAnchor(mCategory_RecyclerView,layoutGravity,0,-mCategory_RecyclerView.getHeight());
                }else {
                    mCategory_RecyclerView.setLayoutManager(linearLayoutManager);

                }
            }
        });

    }

    @Override
    public void setData(@Nullable Object data) {


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void setProjectCategory(ProjectCategoryBean bean) {
        mCategory_list.addAll(bean.getData());
        mProjectCategoryAdapter.addData(mCategory_list);

    }

    @Override
    public void setProjectitem(ProjectItemBean bean, boolean isRefresh) {
        mItem_list.addAll(bean.getData().getDatas());
        mProjectItemAdapter.addData(mItem_list);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
