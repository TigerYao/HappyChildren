package com.dachuwang.software.yaohu.happyeducation.activity;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_cartong_books)
public class CartongBooksActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{

    @FragmentById
    Fragment fragment;
    @ViewById
    ImageView up;
    @ViewById
    TabLayout titletab;
    @ViewById
    ImageView next;
    @ViewById
    Toolbar toolbar;
    String title[]={"最近阅读","全部绘本","绘本教学","最近阅读","全部绘本","绘本教学","最近阅读","全部绘本","绘本教学"};
    int resId[]={R.mipmap.recentlyread_back,R.mipmap.all_picture_back,R.mipmap.search_back,R.mipmap.recentlyread_back,R.mipmap.all_picture_back,R.mipmap.search_back,R.mipmap.recentlyread_back,R.mipmap.all_picture_back,R.mipmap.search_back};
    @AfterViews
    public void viewEvent(){
        titletab.setTabMode(TabLayout.MODE_SCROLLABLE);
        bindTitleData();
        titletab.setOnTabSelectedListener(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void bindTitleData(){
        for(int i=0;i<title.length;i++){
            titletab.addTab(titletab.newTab().setText(title[i]).setIcon(resId[i]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
