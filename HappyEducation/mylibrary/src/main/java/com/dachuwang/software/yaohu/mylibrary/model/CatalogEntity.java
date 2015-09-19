package com.dachuwang.software.yaohu.mylibrary.model;

import android.os.Environment;
import android.os.Parcel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
@Table(name = "catalog_table")
public class CatalogEntity extends BaseEntity {

    public static final Creator<CatalogEntity> CREATOR = new Creator<CatalogEntity>() {
        public CatalogEntity createFromParcel(Parcel source) {
            return new CatalogEntity(source);
        }

        public CatalogEntity[] newArray(int size) {
            return new CatalogEntity[size];
        }
    };
    @Column(column = "name")
    private String name;
    @Column(column = "icon")
    private String icon;
    @Column(column = "index")
    private int index;

    public CatalogEntity() {
    }

    protected CatalogEntity(Parcel in) {
        super(in);
        this.name = in.readString();
        this.icon = in.readString();
        this.index = in.readInt();
    }

    public String getIcon() {
        return Environment.getExternalStorageDirectory()+icon.replace("\\","/");
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.name);
        dest.writeString(this.icon);
        dest.writeInt(this.index);
    }
}
