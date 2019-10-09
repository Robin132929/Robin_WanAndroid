package com.robin.robin_wanandroid.widget;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

public class CustomPopupWindow extends PopupWindow {
    private View mContentView;
    private View mParentView;
    private CustomPopupWindowListener mListener;
    private boolean isOutsideTouch;
    private boolean isFocus;
    private Drawable mBackgroundDrawable;
    private int mAnimationStyle;
    private boolean isWrap;
    private int mWidth;
    private int mHeight;

    private CustomPopupWindow(Builder builder) {
        this.mContentView = builder.contentView;
        this.mParentView = builder.parentView;
        this.mListener = builder.listener;
        this.isOutsideTouch = builder.isOutsideTouch;
        this.isFocus = builder.isFocus;
        this.mBackgroundDrawable = builder.backgroundDrawable;
        this.mAnimationStyle = builder.animationStyle;
        this.isWrap = builder.isWrap;
        this.mWidth=builder.mWidth;
        this.mHeight=builder.mHeight;
        initLayout();
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initLayout() {
        mListener.initPopupView(mContentView);
        setWidth(mWidth);
        setHeight(mHeight);
        setFocusable(isFocus);
        setOutsideTouchable(isOutsideTouch);
        setBackgroundDrawable(mBackgroundDrawable);
        if (mAnimationStyle != -1)//如果设置了动画则使用动画
            setAnimationStyle(mAnimationStyle);
        setContentView(mContentView);
    }

    /**
     * 获得用于展示popup内容的view
     *
     * @return
     */
    @Override
    public View getContentView() {
        return mContentView;
    }

    /**
     * 用于填充contentView,必须传ContextThemeWrapper(比如activity)不然popupwindow要报错
     * @param context
     * @param layoutId
     * @return
     */
    public static View inflateView(ContextThemeWrapper context, int layoutId) {
        return LayoutInflater.from(context)
                .inflate(layoutId, null);
    }

    public void show() {//默认显示到中间
        if (mParentView == null) {
            showAtLocation(mContentView, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else {
            showAtLocation(mParentView, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    public void showBashOfAnchor(View anchor, LayoutGravity layoutGravity, int xmerge, int ymerge) {
        int[] offset=layoutGravity.getOffset(anchor,this);
        showAsDropDown(anchor, offset[0]+xmerge, offset[1]+ymerge);
    }
//    @Override
//    public void showAsDropDown(View anchor, int xoff, int yoff) {
//        showAsDropDown(anchor, xoff, yoff);
//    }
//    @Override
//    public void showAtLocation(View parent, int gravity, int x, int y) {
//        showAtLocation(parent, gravity, x, y);
//    }

    public static class LayoutGravity {
        private int layoutGravity;
        // waring, don't change the order of these constants!
        public static final int ALIGN_LEFT=0x1;
        public static final int ALIGN_ABOVE=0x2;
        public static final int ALIGN_RIGHT=0x4;
        public static final int ALIGN_BOTTOM=0x8;
        public static final int TO_LEFT=0x10;
        public static final int TO_ABOVE=0x20;
        public static final int TO_RIGHT=0x40;
        public static final int TO_BOTTOM=0x80;
        public static final int CENTER_HORI=0x100;
        public static final int CENTER_VERT=0x200;

        public LayoutGravity(int gravity) {
            layoutGravity=gravity;
        }

        public int getLayoutGravity() { return layoutGravity; }
        public void setLayoutGravity(int gravity) { layoutGravity=gravity; }

        public void setHoriGravity(int gravity) {
            layoutGravity&=(0x2+0x8+0x20+0x80+0x200);
            layoutGravity|=gravity;
        }
        public void setVertGravity(int gravity) {
            layoutGravity&=(0x1+0x4+0x10+0x40+0x100);
            layoutGravity|=gravity;
        }

        public boolean isParamFit(int param) {
            return (layoutGravity & param) > 0;
        }

        public int getHoriParam() {
            for(int i=0x1; i<=0x100; i=i<<2)
                if(isParamFit(i))
                    return i;
            return ALIGN_LEFT;
        }

        public int getVertParam() {
            for(int i=0x2; i<=0x200; i=i<<2)
                if(isParamFit(i))
                    return i;
            return TO_BOTTOM;
        }

        public int[] getOffset(View anchor, PopupWindow window) {
            int anchWidth=anchor.getWidth();
            int anchHeight=anchor.getHeight();

            int winWidth=window.getWidth();
            int winHeight=window.getHeight();
            View view=window.getContentView();
            if(winWidth<=0)
                winWidth=view.getWidth();
            if(winHeight<=0)
                winHeight=view.getHeight();

            int xoff=0;
            int yoff=0;

            switch (getHoriParam()) {
                case ALIGN_LEFT:
                    xoff=0; break;
                case ALIGN_RIGHT:
                    xoff=anchWidth-winWidth; break;
                case TO_LEFT:
                    xoff=-winWidth; break;
                case TO_RIGHT:
                    xoff=anchWidth; break;
                case CENTER_HORI:
                    xoff=(anchWidth-winWidth)/2; break;
                default:break;
            }
            switch (getVertParam()) {
                case ALIGN_ABOVE:
                    yoff=-anchHeight; break;
                case ALIGN_BOTTOM:
                    yoff=-winHeight; break;
                case TO_ABOVE:
                    yoff=-anchHeight-winHeight; break;
                case TO_BOTTOM:
                    yoff=0; break;
                case CENTER_VERT:
                    yoff=(-winHeight-anchHeight)/2; break;
                default:break;
            }
            return new int[]{ xoff, yoff };
        }
    }


    public static final class Builder {
        private View contentView;
        private View parentView;
        private CustomPopupWindowListener listener;
        private boolean isOutsideTouch = true;//默认为true
        private boolean isFocus = true;//默认为true
        private Drawable backgroundDrawable = new ColorDrawable(0x00000000);//默认为透明
        private int animationStyle = -1;
        private boolean isWrap;
        private int mWidth;
        private int mHeight;

        private Builder() {
        }

        public Builder contentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder parentView(View parentView) {
            this.parentView = parentView;
            return this;
        }

        public Builder isWrap(boolean isWrap) {
            this.isWrap = isWrap;
            return this;
        }

        public Builder customListener(CustomPopupWindowListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder isOutsideTouch(boolean isOutsideTouch) {
            this.isOutsideTouch = isOutsideTouch;
            return this;
        }

        public Builder isFocus(boolean isFocus) {
            this.isFocus = isFocus;
            return this;
        }

        public Builder backgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder animationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        public Builder setWidth(int width){
            this.mWidth=width;
            return this;
        }

        public Builder setHeight(int height){
            this.mHeight=height;
            return this;
        }
        public CustomPopupWindow build() {
            if (contentView == null)
                throw new IllegalStateException("ContentView is required");
            if (listener == null)
                throw new IllegalStateException("CustomPopupWindowListener is required");

            return new CustomPopupWindow(this);
        }
    }

    public interface CustomPopupWindowListener {
        public void initPopupView(View contentView);
    }
}
