package com.dachuwang.software.yaohu.happyeducation.activity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.AppInfo;
import com.dachuwang.software.yaohu.happyeducation.base.BaseActivity;
import com.dachuwang.software.yaohu.happyeducation.modelview.FirstSubAdatepr;
import com.dachuwang.software.yaohu.happyeducation.modelview.HorizontalLayoutFragment;
import com.dachuwang.software.yaohu.happyeducation.modelview.LayoutAdapter;
import com.dachuwang.software.yaohu.happyeducation.service.WallpaperSettingsService_;
import com.dachuwang.software.yaohu.mylibrary.file.FilePathConstant;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;
import com.dachuwang.software.yaohu.mylibrary.model.BookEntity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import java.util.ArrayList;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener,HorizontalLayoutFragment.FragmentListener {
    @ViewById
    Toolbar toolbar;
    @ViewById
    FrameLayout fragment;
    @ViewById
    ImageView help;
    @ViewById
    ImageView settings;
    @ViewById
    TabLayout titletab;
    @ViewById
    ImageView loadingImg;
    @ViewById
    View ll_loading;
    @ViewById
    View loading;

    @ViewById
    View mainlayout;

    HorizontalLayoutFragment horizontalLayoutFragment;
//    HorizontalFragment horizontalFragment;

    @AfterViews
    public void viewEvent() {
        help.setOnClickListener(this);
        settings.setOnClickListener(this);
        setSupportActionBar(toolbar);
        ll_loading.setBackground(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR + "loading/exit.png"));
        addFrameAnimation(loadingImg);
        Drawable drawable = AppInfo.createStateDrawable(FilePathConstant.BACKGOUND_PICS_DIR+"style000.png");
        mainlayout.setBackground(drawable);
        createDrawable();
    }

    @Background
    public void createDrawable(){
        Drawable helpDrawable=AppInfo.createStateDrawable(FilePathConstant.APK_ICON_DIR+"help.png");
        Drawable settingsDrawable = AppInfo.createStateDrawable(FilePathConstant.APK_ICON_DIR+"set.png");
        setDrawables(helpDrawable, settingsDrawable);
    }

    @MainThread
    public void setDrawables( Drawable helpDrawable,Drawable settingsDrawable){
        help.setImageDrawable(helpDrawable);
        settings.setImageDrawable(settingsDrawable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BackgroundExecutor.cancelAll("updatewallpaper", true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loading.setVisibility(View.VISIBLE);
        LayoutAdapter.COMLUMES=3;
        FirstSubAdatepr.height = (int) getResources().getDimension(R.dimen.mainactivity_cardbg_height_532);
        FirstSubAdatepr.childrenWith = (int)getResources().getDimension(R.dimen.mainactivity_cardbg_with_543);
        FirstSubAdatepr.imgHeight = (int)getResources().getDimension(R.dimen.mainactivity_cardimg_height_446);
        FirstSubAdatepr.imgwith = (int)getResources().getDimension(R.dimen.mainactivity_cardimg_with_490);
        horizontalLayoutFragment = new HorizontalLayoutFragment();
        horizontalLayoutFragment.setFragmentListener(this);
        replaceFragment(fragment.getId(), horizontalLayoutFragment);
        onLoardData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.help:
                break;
            case R.id.settings:
                startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                break;
        }
    }
    @Background
    public void onLoardData(){
        AppInfo.getDataFromDb();
        updateView();
    }

    @UiThread
    public void updateView(){
        if(horizontalLayoutFragment!=null)
            horizontalLayoutFragment.updateAdapter(AppInfo.appInfoEntityList, 3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.GONE);
            }
        },1000);

    }
    @Override
    public void onAdaperClickListener(View view, Object object) {
        AppInfoEntity entity = (AppInfoEntity) object;
        int type = entity.getType();
        CartongBooksActivity_.intent(MainActivity.this).type(type).parentid(entity.get_id()).start();
    }
}
