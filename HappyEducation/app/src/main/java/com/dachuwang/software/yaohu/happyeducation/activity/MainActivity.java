package com.dachuwang.software.yaohu.happyeducation.activity;

import android.app.Fragment;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.AppInfo;
import com.dachuwang.software.yaohu.happyeducation.base.BaseActivity;
import com.dachuwang.software.yaohu.happyeducation.modelview.FirstSubAdatepr;
import com.dachuwang.software.yaohu.happyeducation.modelview.HorizontalLayoutFragment;
import com.dachuwang.software.yaohu.happyeducation.modelview.LayoutAdapter;
import com.dachuwang.software.yaohu.happyeducation.service.WallpaperSettingsService_;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;
import com.dachuwang.software.yaohu.mylibrary.model.BookEntity;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewInterface;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {
    @ViewById
    Toolbar toolbar;
    @FragmentById
    Fragment fragment;
    @ViewById
    ImageView help;
    @ViewById
    ImageView settings;
    @ViewById
    TabLayout titletab;



    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @AfterViews
    public void viewEvent() {
        onLoardData();
        LayoutAdapter.COMLUMES=3;
        FirstSubAdatepr.height = (int) getResources().getDimension(R.dimen.mainactivity_cardbg_height_534);
        FirstSubAdatepr.childrenWith = (int)getResources().getDimension(R.dimen.mainactivity_cardbg_with_510);
        help.setOnClickListener(this);
        settings.setOnClickListener(this);
        setSupportActionBar(toolbar);
        WallpaperSettingsService_.intent(this).start();
        ((HorizontalLayoutFragment)fragment).mAdapter.setOnItemClickListener(new RecyclerViewInterface.OnItemClickListener() {
            @Override
            public void onItemClick(View var1, Object var2) {

                    AppInfoEntity entity = (AppInfoEntity) var2;
                    int type = entity.getType();
                    CartongBooksActivity_.intent(MainActivity.this).type(type).parentid(entity.get_id()).start();
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
                break;
            case R.id.settings:
                break;
        }
    }
    @Background
    public void onLoardData(){
        AppInfo.getDataFromDb();
        ((HorizontalLayoutFragment)fragment).updateAdapter(AppInfo.appInfoEntityList,3);
    }
}
