package com.dachuwang.software.yaohu.mylibrary.file;

import android.os.Environment;

import java.io.File;

/**
 * Created by yaohu on 15/8/24.
 * email yaohu@dachuwang.com
 */
public class FilePathConstant {
    public static final String DBPATH = File.separator + "HappyChildren" + File.separator + "db" + File.separator + "updater.csv";
    public static String ROOTPATH = Environment.getExternalStorageDirectory().toString()
            + File.separator
            + "HappyChildren" + File.separator;
    public static final String APK_ICON_DIR = ROOTPATH + "icon";
    public static final String BACKGOUND_PICS_DIR = ROOTPATH + "background";
    public static final String BOOKS_DIR = ROOTPATH + "books";
    public static final String VIDEOS_DIR = ROOTPATH + "videos";

}
