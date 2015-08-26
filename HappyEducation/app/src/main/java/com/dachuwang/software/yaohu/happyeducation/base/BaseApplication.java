package com.dachuwang.software.yaohu.happyeducation.base;

import android.app.Application;

/**
 * Created by yaohu on 15/8/26.
 * email yaohu@dachuwang.com
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AppInfo.initDisplay(this);
    }
}
