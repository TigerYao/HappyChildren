package com.dachuwang.software.yaohu.mylibrary.model;

import android.os.Parcel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
@Table(name = "recent_table")
public class RecentReadEntity extends AppInfoEntity {
    public static final Creator<RecentReadEntity> CREATOR = new Creator<RecentReadEntity>() {
        public RecentReadEntity createFromParcel(Parcel source) {
            return new RecentReadEntity(source);
        }

        public RecentReadEntity[] newArray(int size) {
            return new RecentReadEntity[size];
        }
    };
    @Column(column = "bookiid")
    private int bookid;
    @Column(column = "parentid")
    private int parentid;

    public RecentReadEntity() {
    }

    protected RecentReadEntity(Parcel in) {
        super(in);
        this.bookid = in.readInt();
        this.parentid = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.bookid);
        dest.writeInt(this.parentid);
    }

}
