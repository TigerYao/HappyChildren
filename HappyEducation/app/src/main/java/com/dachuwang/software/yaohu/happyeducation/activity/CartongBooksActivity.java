package com.dachuwang.software.yaohu.happyeducation.activity;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.BaseActivity;
import com.dachuwang.software.yaohu.happyeducation.modelview.FirstSubAdatepr;
import com.dachuwang.software.yaohu.happyeducation.modelview.LayoutAdapter;

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
    @AfterViews
    public void viewEvent(){
        LayoutAdapter.COMLUMES = 4;
        FirstSubAdatepr.height = (int)getResources().getDimension(R.dimen.cartong_cardbg_height);
        FirstSubAdatepr.childrenWith = (int)getResources().getDimension(R.dimen.cartong_cardbg_with);
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
//        for(int i=0;i<title.length;i++){
//            titletab.addTab(titletab.newTab().setText(title[i]).setIcon(resId[i]));
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search=menu.findItem(R.id.ab_search);
        search.collapseActionView();
        SearchView searchview=(SearchView) search.getActionView();
        searchview.setIconifiedByDefault(false);
        searchview.setBackgroundResource(R.mipmap.search_box_back);
        return super.onCreateOptionsMenu(menu);
    }
}
