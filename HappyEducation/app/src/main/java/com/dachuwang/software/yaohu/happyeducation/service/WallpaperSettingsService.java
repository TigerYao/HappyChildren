package com.dachuwang.software.yaohu.happyeducation.service;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.mylibrary.file.FileUtils;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.UiThread;

import java.io.File;
import java.io.IOException;

/**
 * Created by yaohu on 15/8/27.
 * email yaohu@dachuwang.com
 */
@EService
public class WallpaperSettingsService extends Service{

    private int index=0;
    private File[] wallpaperDir;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    WallpaperManager wallpaperManager;
    @Override
    public void onCreate() {
        super.onCreate();
        wallpaperDir = FileUtils.findFileByNameContain(Environment.getExternalStorageDirectory()+"/happychildren/background","style");

    }

    @Background(delay = 10000, id = "updatewallpaper")
    public void caculaterWallPaperChage() {
        if(wallpaperDir!=null) {
            if (index >= wallpaperDir.length) {
                index = 0;
            }
            File file = wallpaperDir[index];
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            updageWallpaper(bitmap);
            index += 1;
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        wallpaperManager = WallpaperManager.getInstance(this);
        try {
            // wallpaperManager.setResource(R.raw.bg);
            String path=Environment.getExternalStorageDirectory() + "/happychildren/background/style000.png";
            Bitmap bitmap= BitmapFactory.decodeFile(path);
            if(bitmap!=null)
           wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  caculaterWallPaperChage();
        return super.onStartCommand(intent, flags, startId);
    }

    @UiThread
    public void updageWallpaper(Bitmap bitmap) {
        caculaterWallPaperChage();
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
