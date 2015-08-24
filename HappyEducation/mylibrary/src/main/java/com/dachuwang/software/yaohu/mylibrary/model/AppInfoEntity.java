package com.dachuwang.software.yaohu.mylibrary.model;

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

    public static final Creator<AppInfoEntity> CREATOR = new Creator<AppInfoEntity>() {
        public AppInfoEntity createFromParcel(Parcel source) {
            return new AppInfoEntity(source);
        }

        public AppInfoEntity[] newArray(int size) {
            return new AppInfoEntity[size];
        }
    };
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

    protected AppInfoEntity(Parcel in) {
        this.appname = in.readString();
        this.icon = in.readString();
        this.appclass = in.readString();
        this.index = in.readInt();
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
        return icon;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appname);
        dest.writeString(this.icon);
        dest.writeString(this.appclass);
        dest.writeInt(this.index);
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
