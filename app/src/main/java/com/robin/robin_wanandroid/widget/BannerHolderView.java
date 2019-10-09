package com.robin.robin_wanandroid.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.util.GlideUtils;

public class BannerHolderView extends Holder<BannerBean.DataBean> {
    private ImageView mImage;
    private TextView mText;
    private Context mContext;
    public BannerHolderView(View itemView,Context context) {
        super(itemView);
        this.mContext=context;
    }

    @Override
    protected void initView(View itemView) {
        mImage = itemView.findViewById(R.id.image_banner);
        mText = itemView.findViewById(R.id.text_banner);
    }

    @Override
    public void updateUI(BannerBean.DataBean data) {
        GlideUtils.showBannerImage(mContext,mImage,data.getImagePath());
        mText.setText(data.getTitle());
    }
}
