package com.dachuwang.software.yaohu.mylibrary.model;

import android.os.Environment;
import android.os.Parcel;

import com.dachuwang.software.yaohu.mylibrary.db.DbUtilsHelper;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
@Table(name = "appinfo_table")
public class AppInfoEntity extends BaseEntity {

    @Column(column = "appname")
    private String appname;
    @Column(column = "icon")
    private String icon;
    @Column(column = "appclass")
    private String appclass;
    @Column(column = "index")
    private int index;

    public AppInfoEntity() {
    }



    public String getAppclass() {
        return appclass;
    }

    public void setAppclass(String appclass) {
        this.appclass = appclass;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getIcon() {
        return Environment.getExternalStorageDirectory()+icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }



    public List<AppInfoEntity> getAllFromDb() {
        try {
            return DbUtilsHelper.getDbUtils().findAll(AppInfoEntity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AppInfoEntity getFirst() {
        try {
            return DbUtilsHelper.getDbUtils().findFirst(AppInfoEntity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

}
