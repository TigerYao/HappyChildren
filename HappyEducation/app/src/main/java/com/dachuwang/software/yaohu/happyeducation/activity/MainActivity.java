package com.dachuwang.software.yaohu.happyeducation.activity;

import android.app.Fragment;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.modelview.HorizontalLayoutFragment;
import com.dachuwang.software.yaohu.happyeducation.modelview.LayoutAdapter;
import com.dachuwang.software.yaohu.happyeducation.service.WallpaperSettingsService;
import com.dachuwang.software.yaohu.happyeducation.service.WallpaperSettingsService_;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewInterface;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import java.io.File;
import java.io.IOException;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

    }

    @AfterViews
    public void viewEvent() {
        LayoutAdapter.COMLUMES=3;
        help.setOnClickListener(this);
        settings.setOnClickListener(this);
        backup.setOnClickListener(this);
        imageViews=new ImageView[]{help,settings,backup};
        setSupportActionBar(toolbar);
        WallpaperSettingsService_.intent(this).start();
        ((HorizontalLayoutFragment)fragment).mAdapter.setOnItemClickListener(new RecyclerViewInterface.OnItemClickListener() {
            @Override
            public void onItemClick(View var1, Object var2) {
                CartongBooksActivity_.intent(MainActivity.this).start();
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();
        BackgroundExecutor.cancelAll("updatewallpaper", true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.help:
//                changeBg(0);
                break;
            case R.id.settings:
//                changeBg(1);
                break;
            case R.id.backup:
//                changeBg(2);
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
