package com.dachuwang.software.yaohu.mylibrary.model;

import android.os.Parcel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
@Table(name = "setting_table")
public class SettingEntity extends BaseEntity {

    @Column(column = "device_sn")
    private String device_sn;
    @Column(column = "user")
    private String user;
    @Column(column = "pwd")
    private String pwd;
    @Column(column = "software_version")
    private String software_version;

    public SettingEntity() {
    }


    public String getDevice_sn() {
        return device_sn;
    }

    public void setDevice_sn(String device_sn) {
        this.device_sn = device_sn;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getSoftware_version() {
        return software_version;
    }

    public void setSoftware_version(String software_version) {
        this.software_version = software_version;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
