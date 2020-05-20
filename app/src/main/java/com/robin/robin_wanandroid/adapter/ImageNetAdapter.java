package com.robin.robin_wanandroid.adapter;

import android.os.Build;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.IViewHolder;
import com.youth.banner.util.BannerUtils;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 自定义布局，网络图片
 */
public class ImageNetAdapter extends BannerAdapter<BannerBean.DataBean, BaseViewHolder> {

    public ImageNetAdapter(List<BannerBean.DataBean> mDatas) {
        super(mDatas);
    }

    @Override
    public BaseViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);

        return new BaseViewHolder(imageView);
    }

    @Override
    public void onBindView(BaseViewHolder holder, BannerBean.DataBean data, int position, int size) {
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        Glide.with(holder.itemView)
                .load(data.getImagePath())
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into((ImageView) holder.getView(R.id.image));
    }

//    @Override
//    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
//        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);
//        //通过裁剪实现圆角
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            BannerUtils.setBannerRound(imageView,20);
//        }
//        return new ImageHolder(imageView);
//    }
//
//    @Override
//    public void onBindView(ImageHolder holder, DataBean data, int position, int size) {
//        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
//        Glide.with(holder.itemView)
//                .load(data.imageUrl)
////                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
//                .into(holder.imageView);
//    }

}
