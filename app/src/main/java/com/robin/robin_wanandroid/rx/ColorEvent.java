package com.robin.robin_wanandroid.rx;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

public class ColorEvent {
    public String Color;
    @ColorRes
    public int colorInt;

    public ColorEvent(String color,@ColorInt int colorInt) {
        Color = color;
        this.colorInt=colorInt;
    }

}
