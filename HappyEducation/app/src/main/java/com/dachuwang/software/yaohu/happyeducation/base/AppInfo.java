package com.dachuwang.software.yaohu.happyeducation.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.util.Log;

import com.dachuwang.software.yaohu.mylibrary.db.DbUtilsHelper;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;
import com.dachuwang.software.yaohu.mylibrary.model.BookEntity;
import com.dachuwang.software.yaohu.mylibrary.model.CatalogEntity;
import com.dachuwang.software.yaohu.mylibrary.model.RecentReadEntity;
import com.dachuwang.software.yaohu.mylibrary.model.SettingEntity;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


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
    public static ArrayList<AppInfoEntity> appInfoEntityList;
    public static ArrayList<CatalogEntity> catalogEntityList;
    public static SettingEntity settingEntity;
    public static DbUtilsHelper dbUtilsHelper;
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
        int state[][]={{android.R.attr.state_selected},{android.R.attr.state_focused},{android.R.attr.state_pressed},{android.R.attr.state_checked},{}};
        drawable.addState(state[0],seleted);
        drawable.addState(state[1],seleted);
        drawable.addState(state[2],seleted);
        drawable.addState(state[3],seleted);
        drawable.addState(state[4],normal);
        return drawable;
    }

    public static StateListDrawable createStateDrawable(String normal){
       Drawable normalDrawable= Drawable.createFromPath(normal);
        String seleted = normal.replace(".png","_select.png");
        Drawable selectedDrawable =Drawable.createFromPath(seleted);
        return createStateDrawable(normalDrawable,selectedDrawable);
    }
    public static  void getDataFromDb() {
        try {
            appInfoEntityList = new ArrayList<>(dbUtilsHelper.getDbUtils().findAll(AppInfoEntity.class));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<CatalogEntity> getCatalogEntityList(int type) {
        try {
            Selector selector = Selector.from(CatalogEntity.class);
            selector.where("type","=",type);
            List<CatalogEntity> list=dbUtilsHelper.getDbUtils().findAll(selector);
            catalogEntityList = new ArrayList<>(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return catalogEntityList;
    }

    public static ArrayList<BookEntity> getBookEntityList(boolean isall,int parentid,int catalogId) {
        ArrayList<BookEntity> bookEntityList = new ArrayList<>();
        try {
            Selector selector = Selector.from(BookEntity.class);
            if(isall){
                selector.where("parentid","=",parentid);
            }else
                selector.where("catalogid","=",catalogId);
            List<BookEntity> list= dbUtilsHelper.getDbUtils().findAll(selector);
            bookEntityList = new ArrayList<>(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return bookEntityList;
    }
    public static ArrayList<RecentReadEntity> getRecentRead(boolean isall,int parentid,int bookid) {
        ArrayList<RecentReadEntity> recentReadEntities=new ArrayList<>();
        try {
            Selector selector = Selector.from(RecentReadEntity.class);
            if(isall){
                selector.where("parentid","=",parentid);
            }else
                selector.where("bookid","=",bookid);
            List<RecentReadEntity> list= dbUtilsHelper.getDbUtils().findAll(selector);
            recentReadEntities = new ArrayList<>(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return recentReadEntities;
    }
}
