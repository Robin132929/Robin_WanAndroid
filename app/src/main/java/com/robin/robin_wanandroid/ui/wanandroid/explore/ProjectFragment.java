package com.robin.robin_wanandroid.ui.wanandroid.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ToggleButton;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.ProjectCategoryAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.ProjectItemAdapter;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.ProjectContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectCategoryBean;
import com.robin.robin_wanandroid.mvp.model.bean.ProjectItemBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.ProjectPresenter;
import com.robin.robin_wanandroid.util.loading.Gloading;
import com.robin.robin_wanandroid.widget.BannerHolderView;
import com.robin.robin_wanandroid.widget.CustomPopupWindow;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class ProjectFragment extends RobinBaseFragment<ProjectPresenter> implements ProjectContract.View, ScrollTopListener {

    private RecyclerView mCategory_RecyclerView;
    private RecyclerView mItem_RecyclerView;
    private RecyclerView mPopopWindowRecycleView;
    private ToggleButton mToggleButton;
    private ConvenientBanner<BannerBean.DataBean> mConvenientBanner;
    private CustomPopupWindow mExpandPopupWindow;

    private List<ProjectCategoryBean.DataBean> mCategory_list;
    private List<ProjectItemBean.DataBean.DatasBean> mItem_list;

    private ProjectCategoryAdapter mProjectCategoryAdapter;
    private ProjectCategoryAdapter mPopopWindow_ProjectCategoryAdapter;
    private ProjectItemAdapter mProjectItemAdapter;

    private CustomPopupWindow.LayoutGravity layoutGravity;
    private ToggleButton mPopopWindow_ToggleButton;
    private LinearLayout ll_project;

    public ProjectFragment() {
        mCategory_list=new ArrayList<>();
        mItem_list=new ArrayList<>();
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategory_RecyclerView=view.findViewById(R.id.rv_list);
        mItem_RecyclerView=view.findViewById(R.id.rv_project_list);
        mToggleButton=view.findViewById(R.id.btn_expand);
        mConvenientBanner=view.findViewById(R.id.project_banner);
        ll_project=view.findViewById(R.id.ll_project);
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
                        mPopopWindow_ToggleButton=contentView.findViewById(R.id.expand_tb);
                        mPopopWindow_ToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked){
                                    mExpandPopupWindow.dismiss();
                                }
                            }
                        });

                        mPopopWindowRecycleView=contentView.findViewById(R.id.data_list);
                        mPopopWindowRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
                        mPopopWindow_ProjectCategoryAdapter= new ProjectCategoryAdapter(R.layout.top_nav_item, mCategory_list);
                        mPopopWindowRecycleView.setAdapter(mPopopWindow_ProjectCategoryAdapter);
                    }

                }).build();
        mExpandPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mToggleButton.setChecked(false);
                mPopopWindow_ToggleButton.setChecked(false);
            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mCategory_RecyclerView.setLayoutManager(linearLayoutManager);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mItem_RecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mProjectCategoryAdapter=new ProjectCategoryAdapter(R.layout.top_nav_item,mCategory_list);
        mProjectItemAdapter=new ProjectItemAdapter(R.layout.projectitem_recycle_item,mItem_list);

        mCategory_RecyclerView.setAdapter(mProjectCategoryAdapter);
        mItem_RecyclerView.setAdapter(mProjectItemAdapter);

//        mPopopWindow_ProjectCategoryAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ProjectCategoryBean.DataBean bean= (ProjectCategoryBean.DataBean) adapter.getItem(position);
//                mPresenter.getProjectitem(1,bean.getId(),false);
//            }
//        });
//
//        mProjectCategoryAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ProjectCategoryBean.DataBean bean= (ProjectCategoryBean.DataBean) adapter.getItem(position);
//                mPresenter.getProjectitem(1,bean.getId(),false);
//            }
//        });
//        mProjectItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ProjectItemBean.DataBean.DatasBean bean= (ProjectItemBean.DataBean.DatasBean) adapter.getItem(position);
//                ContentActivity.startActivity(mContext,bean.getTitle(),bean.getLink());
//            }
//        });
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
        if (isRefresh) {
            mItem_list.addAll(bean.getData().getDatas());
            mProjectItemAdapter.addData(mItem_list);
        }else {
            mItem_list.clear();
            mProjectItemAdapter.setNewData(bean.getData().getDatas());
        }

    }

    @Override
    public void setBanner(BannerBean banner) {
        Logger.i("banner is :"+banner.getData().size());
        mConvenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new BannerHolderView(itemView, getContext());
            }

            @Override
            public int getLayoutId() {
                return R.layout.project_item_banner_view;
            }
        }, banner.getData()).setPageIndicator(new int[]{R.drawable.ic_circle_normal, R.drawable.ic_circle_press})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
        mConvenientBanner.startTurning();
    }

    @Override
    public void showLoading() {
showLoadingView();    }

    @Override
    public void hideLoading() {
showLoadSuccess();    }

    @Override
    public void showError() {
showLoadFailed();    }

//    @Override
//    protected void lazyLoadData() {
//        mPresenter.getProjectCategory();
//        mPresenter.getProjectitem(1,294,false);
//        mPresenter.getBanner();
//    }

    @Override
    public void scroll2Top() {
        mItem_RecyclerView.scrollToPosition(0);
    }

    @Override
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder==null){
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().cover(ll_project).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    @Override
    protected void onLoadRetry() {

    }
    @Override
    public void onPause() {
        super.onPause();
        Logger.e("fragment  pause "+this.toString());

    }
}
