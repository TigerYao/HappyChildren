package com.dachuwang.software.yaohu.happyeducation.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.AppInfo;
import com.dachuwang.software.yaohu.happyeducation.base.BaseActivity;
import com.dachuwang.software.yaohu.happyeducation.modelview.FirstSubAdatepr;
import com.dachuwang.software.yaohu.happyeducation.modelview.HorizontalLayoutFragment;
import com.dachuwang.software.yaohu.happyeducation.modelview.LayoutAdapter;
import com.dachuwang.software.yaohu.mylibrary.App.KeyboardUtil;
import com.dachuwang.software.yaohu.mylibrary.file.FilePathConstant;
import com.dachuwang.software.yaohu.mylibrary.model.BookEntity;
import com.dachuwang.software.yaohu.mylibrary.model.CatalogEntity;
import com.dachuwang.software.yaohu.mylibrary.model.RecentReadEntity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.net.URI;
import java.util.ArrayList;

@EActivity(R.layout.activity_cartong_books)
public class CartongBooksActivity extends BaseActivity implements TabLayout.OnTabSelectedListener,HorizontalLayoutFragment.FragmentListener{

    @ViewById
    FrameLayout fragment;
    @ViewById
    ImageView up;
    @ViewById
    TabLayout titletab;
    @ViewById
    ImageView next;
    @ViewById
    View toolbar;
    @ViewById
    EditText search_input;
    @ViewById
    ImageView search_bar;
    @ViewById
    ImageView return_back;
    @ViewById
    View mainlayout;
    @Extra
    int type;
    @Extra
    int parentid;

    @ViewById
    ImageView loadingImg;
    @ViewById
    View ll_loading;
    @ViewById
    View loading;


    HorizontalLayoutFragment horizontalLayoutFragment;

    @AfterViews
    public void viewEvent(){
        Drawable drawable = AppInfo.createStateDrawable(FilePathConstant.BACKGOUND_PICS_DIR+"style010.png");
        mainlayout.setBackground(drawable);
        titletab.setTabMode(TabLayout.MODE_SCROLLABLE);
        titletab.setTabGravity(TabLayout.GRAVITY_CENTER);
        bindTitleData();
        titletab.setOnTabSelectedListener(this);
        createDrawable();
        horizontalLayoutFragment = new HorizontalLayoutFragment();
        horizontalLayoutFragment.setFragmentListener(this);
        replaceFragment(fragment.getId(), horizontalLayoutFragment);
        search_input.setVisibility(View.GONE);
        ll_loading.setBackground(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR + "loading/exit.png"));
        addFrameAnimation(loadingImg);
    }
    @Background
    public void createDrawable(){
        Drawable upDrawable= AppInfo.createStateDrawable(FilePathConstant.SECOND_ICON+"titlebeforepage.png");
        Drawable nextDrawalbe = AppInfo.createStateDrawable(FilePathConstant.SECOND_ICON+"titleafterpage.png");
        Drawable returnDrawable = AppInfo.createStateDrawable(FilePathConstant.SECOND_ICON+"return.png");
        Drawable searchDrawable = AppInfo.createStateDrawable(FilePathConstant.SECOND_ICON+"search.png");
        setDrawables(upDrawable, nextDrawalbe, returnDrawable, searchDrawable);
}
    @MainThread
    public void setDrawables(Drawable upDrawable,Drawable nextDrawalbe,Drawable returnDrawable, Drawable searchDrawable){
        up.setImageDrawable(upDrawable);
        next.setImageDrawable(nextDrawalbe);
        return_back.setImageDrawable(returnDrawable);
        search_bar.setImageDrawable(searchDrawable);
    }
    @Click(R.id.up)
    void up(){
        if(titletab.getSelectedTabPosition()>0)
        titletab.getTabAt(titletab.getSelectedTabPosition() - 1).select();
    }
    @Click(R.id.next)
    void next(){
        if(titletab.getSelectedTabPosition()<titletab.getTabCount()-1)
        titletab.getTabAt(titletab.getSelectedTabPosition()+1).select();
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
        loading.setVisibility(View.VISIBLE);
        CatalogEntity catalogEntity = AppInfo.catalogEntityList.get(tab.getPosition());
        if(tab.getPosition()==0){
            getBookEntities(true,catalogEntity.get_id());
        }else if(tab.getPosition()==1){
            getRecentEntities();
        }else
        getBookEntities(false,catalogEntity.get_id());
//
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

        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.tabview,null);// new TextView(this);
        textView.setText(entity.getName());
        textView.setBackground(drawable);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(34);
//        titletab.addTab(titletab.newTab().setIcon(drawable).setText(entity.getName()));
        titletab.addTab(titletab.newTab().setCustomView(textView));
        textView.getLayoutParams().width = 220;
    }

    @Background
    public void getBookEntities(boolean isAll,int catalogid){
        ArrayList<BookEntity> bookEntities = AppInfo.getBookEntityList(isAll, type, catalogid);
        updateAdater(bookEntities);
    }

    @Background
    public void getRecentEntities(){
        ArrayList<BookEntity> bookEntities =AppInfo.getRecentRead(type);
        updateAdater(bookEntities);
    }

    @UiThread
    public void updateAdater(ArrayList<BookEntity> bookEntities){
        horizontalLayoutFragment.updateAdapter(bookEntities, 4);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.GONE);
            }
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        loading.setVisibility(View.VISIBLE);
        LayoutAdapter.COMLUMES = 4;
        FirstSubAdatepr.height = (int)getResources().getDimension(R.dimen.cartong_cardbg_height);
        FirstSubAdatepr.childrenWith = (int)getResources().getDimension(R.dimen.cartong_cardbg_with);
        FirstSubAdatepr.imgHeight = (int)getResources().getDimension(R.dimen.cartong_cardbgimg_height);
        FirstSubAdatepr.imgwith = (int)getResources().getDimension(R.dimen.cartong_cardimg_with);
    }

    @Override
    public void onAdaperClickListener(View view, Object object) {
        if(object instanceof BookEntity){

            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BookEntity entity = (BookEntity) object;
            Bundle bundle = new Bundle();
            switch (entity.getType()){
                case 0:
                    intent.setAction("android.intent.action.Book");
                    bundle.putInt("id",entity.get_id());
                    bundle.putInt("screentype", entity.getReverse1());
                    bundle.putInt("page", entity.getPos());
                    intent.setData(Uri.parse("file:" + entity.getUrl() + entity.getReverse1()));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                    break;
                case 1:
                    intent.setAction("android.intent.action.children.video");
                    intent.setData(Uri.parse("file:" + entity.getUrl()));
//                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                    break;

            }

        }
    }
}
