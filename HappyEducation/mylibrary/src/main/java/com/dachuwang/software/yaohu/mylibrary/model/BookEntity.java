package com.dachuwang.software.yaohu.mylibrary.model;

import android.os.Parcel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
@Table(name = "book_table")
public class BookEntity extends BaseEntity {

    public static final Creator<BookEntity> CREATOR = new Creator<BookEntity>() {
        public BookEntity createFromParcel(Parcel source) {
            return new BookEntity(source);
        }

        public BookEntity[] newArray(int size) {
            return new BookEntity[size];
        }
    };
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

    public BookEntity() {
    }

    protected BookEntity(Parcel in) {
        super(in);
        this.catalogid = in.readInt();
        this.name = in.readString();
        this.icon = in.readString();
        this.url = in.readString();
        this.readtime = in.readString();
        this.index = in.readInt();
        this.pos = in.readInt();
        this.readcount = in.readInt();
    }

    public int getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(int catalogid) {
        this.catalogid = catalogid;
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
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.catalogid);
        dest.writeString(this.name);
        dest.writeString(this.icon);
        dest.writeString(this.url);
        dest.writeString(this.readtime);
        dest.writeInt(this.index);
        dest.writeInt(this.pos);
        dest.writeInt(this.readcount);
    }
}
