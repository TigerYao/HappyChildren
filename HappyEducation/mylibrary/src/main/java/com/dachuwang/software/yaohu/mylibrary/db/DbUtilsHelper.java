package com.dachuwang.software.yaohu.mylibrary.db;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;

import java.io.File;

/**
 * Created by yaohu on 15/8/21.
 * email yaohu@dachuwang.com
 */
public  class DbUtilsHelper {
    private static DbUtils dbUtils;

    public DbUtilsHelper(Context ctx) {
        //判断外部存储卡是否存在  
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(ctx, "读取失败，SD存储卡不存在！", Toast.LENGTH_LONG).show();
            return;
        }

        //初始化File  
        String path = Environment.getRootDirectory().toString()
                + "\\HappyChildren\\db";
        File file = new File(path);
        if (!file.exists()) {
            Toast.makeText(ctx, "文件不存在！", Toast.LENGTH_LONG).show();
            file.mkdirs();
        }
//        DbUtils.DaoConfig daoConfig = new DbUtils.DaoConfig(ctx);
//        daoConfig.setDbUpgradeListener(new MyDbUpdateListener());
//        daoConfig.setDbDir(path);
//        daoConfig.setDbName("book.db");
//        Log.i("tag", daoConfig.getDbDir());
//        dbUtils = DbUtils.create(daoConfig);
        if(file.exists()) {
            dbUtils = DbUtils.create(ctx, path, "book.db");
        }else
            Toast.makeText(ctx, "文件不存在！", Toast.LENGTH_LONG).show();
    }

    public static DbUtils getDbUtils() {
        return dbUtils;
    }

    class MyDbUpdateListener implements DbUtils.DbUpgradeListener {
        @Override
        public void onUpgrade(DbUtils dbUtils, int i, int i1) {

        }
    }


}
