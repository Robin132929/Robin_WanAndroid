package com.robin.robin_wanandroid.adapter.wanandroid;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class KnowledgeListAdapter extends BaseQuickAdapter<KnowledgeStructureBean.DataBean, BaseViewHolder> {
    private int mSelectedPosition=-1;
    private int mLast=-2;

    public KnowledgeListAdapter(int layoutResId, @Nullable List<KnowledgeStructureBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KnowledgeStructureBean.DataBean item) {
//        ViewGroup.LayoutParams lp=helper.getView(R.id.show_text).getLayoutParams();
//        if (lp instanceof FlexboxLayoutManager.LayoutParams){
//            Logger.e("KnowledgeStructureBean size is  no layout "+item.getName());
//            FlexboxLayoutManager.LayoutParams flex= (FlexboxLayoutManager.LayoutParams) lp;
//            flex.setFlexGrow(1.0f);
//        }
        helper.setText(R.id.show_text,item.getName());

    }
    public void setSelectedPosition(int position){
        mLast=mSelectedPosition;
        this.mSelectedPosition=position;
    }

    public int getLast(){
        return mLast;
    }
}
