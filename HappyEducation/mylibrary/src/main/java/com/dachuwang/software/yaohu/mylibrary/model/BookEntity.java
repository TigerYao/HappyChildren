package com.dachuwang.software.yaohu.mylibrary.model;

import android.os.Environment;
import android.os.Parcel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
@Table(name = "book_table")
public class BookEntity extends BaseEntity {

    @Column(column = "catalogid")
    private int catalogid;
    @Column(column = "name")
    private String name;
    @Column(column = "icon")
    private String icon;
    @Column(column = "url")
    private String url;
    @Column(column = "readtime")
    private String readtime;
    @Column(column = "index")
    private int index;
    @Column(column = "pos")
    private int pos;
    @Column(column = "readcount")
    private int readcount;

    @Column(column = "parentid")
    private int parentid;

    public BookEntity() {
    }


    public int getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(int catalogid) {
        this.catalogid = catalogid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public String getReadtime() {
        return readtime;
    }

    public void setReadtime(String readtime) {
        this.readtime = readtime;
    }

    public String getUrl() {
        return Environment.getExternalStorageDirectory()+url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }


    @Override
    public String toString() {
        return "BookEntity{" +
                "catalogid=" + catalogid +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", readtime='" + readtime + '\'' +
                ", index=" + index +
                ", pos=" + pos +
                ", readcount=" + readcount +
                ", parentid=" + parentid +
                '}';
    }
}
