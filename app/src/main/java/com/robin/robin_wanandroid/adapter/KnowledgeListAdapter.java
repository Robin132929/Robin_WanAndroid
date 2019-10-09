package com.robin.robin_wanandroid.adapter;

import android.view.View;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
    protected void bindViewClickListener(BaseViewHolder baseViewHolder) {
        super.bindViewClickListener(baseViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
//        if (mSelectedPosition==position){
//           holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.Light_Blue));
//            Logger.i("v select");
//        }else {
//            Logger.i("v no select");
//            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.White));
//        }
    }

    @Override
    public void setOnItemClick(View v, int position) {
        super.setOnItemClick(v, position);


    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KnowledgeStructureBean.DataBean item) {

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
