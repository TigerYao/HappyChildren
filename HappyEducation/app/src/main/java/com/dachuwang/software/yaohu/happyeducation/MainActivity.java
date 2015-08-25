package com.dachuwang.software.yaohu.happyeducation;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public int index = 0;
    @ViewById
    Toolbar toolbar;
    @ViewById
    ImageView wallpaper;
    @FragmentById
    Fragment fragment;
    @ViewById
    ImageView help;
    @ViewById
    ImageView settings;
    @ViewById
    ImageView backup;
    @ViewById
    TabLayout titletab;

    public int resIdNormal[]= {R.mipmap.help_normal,R.mipmap.settings_normal,R.mipmap.backup_normal};
    public int resIdSelector[]= {R.mipmap.help_selector,R.mipmap.settings_seleted,R.mipmap.backup_selector};
    public ImageView imageViews[];
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        index = savedInstanceState.getInt("index");

    }

    @AfterViews
    public void viewEvent() {
        help.setOnClickListener(this);
        settings.setOnClickListener(this);
        backup.setOnClickListener(this);
        imageViews=new ImageView[]{help,settings,backup};
        setSupportActionBar(toolbar);
    }

    @Background(delay = 2000, id = "updatewallpaper")
    public void caculaterWallPaperChage() {
        index++;
        updageWallpaper();
        Log.i("vv", index + "");

    }

    @UiThread
    public void updageWallpaper() {
        caculaterWallPaperChage();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("index", index);
        Log.i("vv", index + "dd");
    }


    @Override
    protected void onPause() {
        super.onPause();
        BackgroundExecutor.cancelAll("updatewallpaper", true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updageWallpaper();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.help:
                changeBg(0);
                break;
            case R.id.settings:
                changeBg(1);
                break;
            case R.id.backup:
                changeBg(2);
                break;
        }
    }

    private void changeBg(int position){
        for(int i=0;i<resIdNormal.length;i++){
            if(i==position){
                imageViews[i].setImageResource(resIdSelector[i]);
                continue;
            }
            imageViews[i].setImageResource(resIdNormal[i]);
        }
    }

}
