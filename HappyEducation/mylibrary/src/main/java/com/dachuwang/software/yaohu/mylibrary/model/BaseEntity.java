package com.dachuwang.software.yaohu.mylibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.lidroid.xutils.db.annotation.Column;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
public abstract class BaseEntity implements Parcelable {

    private int _id;

    @Column(column = "reverse1")
    private String reverse1;

    @Column(column = "reverse2")
    private String reverse2;

    @Column(column = "type")
    private int type;

    public BaseEntity() {
    }

    protected BaseEntity(Parcel in) {
        this._id = in.readInt();
        this.reverse1 = in.readString();
        this.reverse2 = in.readString();
        this.type = in.readInt();
    }

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

    public String getReverse1() {
        return reverse1;
    }

    public void setReverse1(String reverse1) {
        this.reverse1 = reverse1;
    }

    public String getReverse2() {
        return reverse2;
    }

    public void setReverse2(String reverse2) {
        this.reverse2 = reverse2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this.reverse1);
        dest.writeString(this.reverse2);
        dest.writeInt(this.type);
    }

}
