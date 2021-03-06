package com.robin.robin_wanandroid.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class ToolbarBehavior extends CoordinatorLayout.Behavior<View> {
    private ObjectAnimator hideAnimator, displayAnimator;

    public ToolbarBehavior() {
    }

    public ToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    // 垂直滑动

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes,int type) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed,int type) {
        if (dy > 0) {
            // 上滑隐藏
            if (hideAnimator == null) {
                hideAnimator = ObjectAnimator.ofFloat(child, "translationY", 0, child.getHeight());
                hideAnimator.setDuration(200);
            }
            if (!hideAnimator.isRunning() && child.getTranslationY() <= 0) {
                hideAnimator.start();
            }
        } else if (dy < 0) {
            // 下滑显示
            if (displayAnimator == null) {
                displayAnimator = ObjectAnimator.ofFloat(child, "translationY", child.getHeight(), 0);
                displayAnimator.setDuration(200);
            }
            if (!displayAnimator.isRunning() && child.getTranslationY() >= child.getHeight()) {
                displayAnimator.start();
            }
        }
    }
}
