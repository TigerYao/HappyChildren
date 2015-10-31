package com.dachuwang.software.yaohu.mylibrary.model;

import android.os.Parcel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
@Table(name = "recent_table")
public class RecentReadEntity  {
    @Column(column = "bookid")
    private int bookid;
    @Column(column = "parentid")
    private int parentid;

    @Column(column = "_id")
    private int _id;

    @Column(column = "reverse1")
    private int reverse1 = 0;

    @Column(column = "reverse2")
    private String reverse2;

    @Column(column = "type")
    private int type;




    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getReverse1() {
        return reverse1;
    }

    public void setReverse1(int reverse1) {
        this.reverse1 = reverse1;
    }

    public String getReverse2() {
        return reverse2;
    }

    public void setReverse2(String reverse2) {
        this.reverse2 = reverse2;
    }



    public RecentReadEntity() {
    }


    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "RecentReadEntity{" +
                "bookid=" + bookid +
                ", parentid=" + parentid +
                '}';
    }
}
