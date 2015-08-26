package com.dachuwang.software.yaohu.happyeducation.base;

import android.content.Context;
import android.util.DisplayMetrics;


/**
 * Created by yee on 11/18/13.
 *
 * @author yefeng
 */
public class AppInfo {
    public static int width;
    public static int height;
    public static float density;
    public static int densityDpi;
    public AppInfo() {
        super();
    }
    public static void initDisplay(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        density = metrics.density;
        densityDpi = metrics.densityDpi;
    }
}
