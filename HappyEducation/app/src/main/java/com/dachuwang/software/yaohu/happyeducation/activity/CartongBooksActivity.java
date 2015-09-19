package com.dachuwang.software.yaohu.happyeducation.activity;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.AppInfo;
import com.dachuwang.software.yaohu.happyeducation.base.BaseActivity;
import com.dachuwang.software.yaohu.happyeducation.modelview.FirstSubAdatepr;
import com.dachuwang.software.yaohu.happyeducation.modelview.HorizontalLayoutFragment;
import com.dachuwang.software.yaohu.happyeducation.modelview.LayoutAdapter;
import com.dachuwang.software.yaohu.mylibrary.App.KeyboardUtil;
import com.dachuwang.software.yaohu.mylibrary.model.BookEntity;
import com.dachuwang.software.yaohu.mylibrary.model.CatalogEntity;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewInterface;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

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
    @ViewById
    EditText search_input;
    @ViewById
    ImageView search_bar;
    @ViewById
    ImageView return_back;
    @Extra
    int type;
    @Extra
    int parentid;

    HorizontalLayoutFragment horizontalLayoutFragment;

    @AfterViews
    public void viewEvent(){
        search_input.setVisibility(View.GONE);
        LayoutAdapter.COMLUMES = 4;
        FirstSubAdatepr.height = (int)getResources().getDimension(R.dimen.cartong_cardbg_height);
        FirstSubAdatepr.childrenWith = (int)getResources().getDimension(R.dimen.cartong_cardbg_with);
        titletab.setTabMode(TabLayout.MODE_SCROLLABLE);
        titletab.setTabGravity(TabLayout.GRAVITY_CENTER);
        bindTitleData();
        getBookEntities(true,-1);
        titletab.setOnTabSelectedListener(this);
        setSupportActionBar(toolbar);
        horizontalLayoutFragment = (HorizontalLayoutFragment)fragment;
        horizontalLayoutFragment.mAdapter.setOnItemClickListener(new RecyclerViewInterface.OnItemClickListener() {
            @Override
            public void onItemClick(View var1, Object var2) {

            }
        });
    }

    @Click(R.id.search_bar)
    void search(){
        if(search_input.getVisibility()==View.GONE&& TextUtils.isEmpty(search_input.getText()))
        search_input.setVisibility(View.VISIBLE);
        else if(!TextUtils.isEmpty(search_input.getText())){
            String searchContent = search_input.getText().toString();
        }else{
            KeyboardUtil.closeKeyboard(this);
            search_input.setVisibility(View.GONE);
        }
    }

    @Click(R.id.return_back)
    void onBackView(){
        finish();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        CatalogEntity catalogEntity = AppInfo.catalogEntityList.get(tab.getPosition());
        getBookEntities(false,catalogEntity.get_id());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Background
    public void bindTitleData(){
        AppInfo.getCatalogEntityList(type);
        for(int i=0;i<AppInfo.catalogEntityList.size();i++){
            CatalogEntity entity = AppInfo.catalogEntityList.get(i);
            addTabs(entity);
        }
    }
    @UiThread
    public void addTabs(CatalogEntity entity){
        Drawable drawable = AppInfo.createStateDrawable(entity.getIcon());
        titletab.addTab(titletab.newTab().setIcon(drawable).setText(entity.getName()));
    }

    @Background
    public void getBookEntities(boolean isAll,int catalogid){
        ArrayList<BookEntity> bookEntities = AppInfo.getBookEntityList(isAll, parentid, catalogid);
        updateAdater(bookEntities);

    }

    @UiThread
    public void updateAdater(ArrayList<BookEntity> bookEntities){
        horizontalLayoutFragment.updateAdapter(bookEntities,4);
    }

}
