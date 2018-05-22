package com.haoyue.auxiliary;

import android.content.Context;

/**
 * Created by chen1 on 2017/12/8.
 * TO DO:
 */

public class DisplayUtils {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @return
     */
    public static int pxToDp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @return
     */
    public static int dpToPx(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @return
     */
    public static int pxToSp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @return
     */
    public static int spToPx(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static double mathToInt(double value){
//        舍掉小数取整:
        return Math.floor(value);
    }
    public static double mathRounded(double value){
//        四舍五入取整:
        return Math.rint(value);
    }
    public static double mathAddToInt(double value){
//      进位取整:
        return Math.ceil(value);
    }
    public static double MathABS(double value){
//        取绝对值：
        return Math.abs(value);
    }
}
