package com.ldy.library.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * 像素工具
 */
public class PixelUtils {

    // sp转成px
    public static float sp2px(Context context, double size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (float) size, context.getResources().getDisplayMetrics());
    }

    // dp转成px
    public static int dp2px(Context context, float size) {
        return (int) dp2pxFloat(context, size);
    }

    // dp转成px
    public static float dp2pxFloat(Context context, float size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, context.getResources().getDisplayMetrics());
    }

    //px转换为sp
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    //px转换为dp
    public static int px2dp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // 获取屏幕宽度
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    // 获取屏幕高度
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}
