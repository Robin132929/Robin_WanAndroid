package com.robin.rbase.CommonBase;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ================================================
 * 基类 {@link RecyclerView.ViewHolder}
 * ================================================
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();
    protected OnViewClickListener mOnViewClickListener = null;

    public BaseHolder(View itemView) {
        super(itemView);
        //点击事件
        itemView.setOnClickListener(this);
    }

    /**
     * 设置数据
     *
     * @param data     数据
     * @param position 在 RecyclerView 中的位置
     */
    public abstract void setData(@NonNull T data, int position);

    /**
     * 在 Activity 的 onDestroy 中使用 {@link DefaultAdapter#releaseAllHolder(RecyclerView)} 方法 (super.onDestroy() 之前)
     * {@link BaseHolder#onRelease()} 才会被调用, 可以在此方法中释放一些资源
     */
    protected void onRelease() {

    }

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener.onViewClick(view, this.getPosition());
        }
    }

    public void setOnItemClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }

    /**
     * item 点击事件
     */
    public interface OnViewClickListener {

        /**
         * item 被点击
         *
         * @param view     被点击的 {@link View}
         * @param position 在 RecyclerView 中的位置
         */
        void onViewClick(View view, int position);
    }
}