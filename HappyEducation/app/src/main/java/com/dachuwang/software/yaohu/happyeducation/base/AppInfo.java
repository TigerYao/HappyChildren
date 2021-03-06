package com.dachuwang.software.yaohu.happyeducation.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
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

    /**
     * drawable.addState(new int[]{android.R.attr.state_pressed}, this.getResources().getDrawable(R.drawable.botton_add_down));
     drawable.addState(new int[]{android.R.attr.state_selected}, this.getResources().getDrawable(R.drawable.botton_add));
     drawable.addState(new int[]{}, this.getResources().getDrawable(R.drawable.botton_add));//默认
     * @param normal
     * @param seleted
     */
    public static StateListDrawable createStateDrawable(Drawable normal,Drawable seleted){
        StateListDrawable drawable = new StateListDrawable();
        int state[][]={{android.R.attr.state_selected},{android.R.attr.state_focused},{android.R.attr.state_pressed},{}};
        drawable.addState(state[0],seleted);
        drawable.addState(state[1],seleted);
        drawable.addState(state[2],seleted);
        drawable.addState(state[3],normal);
        return drawable;
    }

}
