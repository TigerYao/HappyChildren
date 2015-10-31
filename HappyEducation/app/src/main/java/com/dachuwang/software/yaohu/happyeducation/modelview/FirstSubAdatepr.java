package com.dachuwang.software.yaohu.happyeducation.modelview;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.AppInfo;
import com.dachuwang.software.yaohu.mylibrary.file.FilePathConstant;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;
import com.dachuwang.software.yaohu.mylibrary.model.BaseEntity;
import com.dachuwang.software.yaohu.mylibrary.model.BookEntity;
import com.dachuwang.software.yaohu.mylibrary.model.RecentReadEntity;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewAdapter;

import org.androidannotations.annotations.App;

import java.util.ArrayList;


/**
 * Created by yaohu on 15/8/25.
 * email yaohu@dachuwang.com
 */
public class FirstSubAdatepr extends RecyclerViewAdapter<BaseEntity>{
    public  ArrayList<? extends BaseEntity> data;
    public static int childrenWith = 0;
    public static int height =0;
    public static int imgwith=0;
    public static int imgHeight=0;

    public FirstSubAdatepr(ArrayList<BaseEntity> data) {
        super(data);
        this.data = data;
    }

    public FirstSubAdatepr(ArrayList<BaseEntity> data, int mode, int toolBarHeight) {
        super(data, mode, toolBarHeight);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);

        return new AppInfoViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindDataViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        final BaseEntity entity = data.get(position);
        String appname="";
        String icon = "";
        if(entity instanceof AppInfoEntity){
            AppInfoEntity appInfoEntity = (AppInfoEntity)entity;
            appname = appInfoEntity.getAppname();
            icon= appInfoEntity.getIcon();
        }else if(entity instanceof BookEntity){
            BookEntity bookEntity = (BookEntity)entity;
            appname = bookEntity.getName();
            icon = bookEntity.getIcon();
        }
        final AppInfoViewHolder appInfoViewHolder = (AppInfoViewHolder)viewHolder;
        if(entity!=null) {
            appInfoViewHolder.title.setText(appname);
            appInfoViewHolder.content.setImageDrawable(AppInfo.createStateDrawable(icon));
        }
        String path = "bluebox.png";
        String titlePath = "booktitle1.png";


       switch (position){
           case 0:
               path = "bluebox.png";
               titlePath = "booktitle1.png";
               break;

           case 2:
               path = "greenbox.png";
               titlePath = "booktitle3.png";
               break;
           case 1:
                   path = "purplebox.png";
                   titlePath = "booktitle2.png";
               if(LayoutAdapter.COMLUMES==3) {
                   path = "orangebox.png";
               }
                   break;

           case 3:
               path = "redbox.png";
               titlePath = "booktitle4.png";
               break;
       }
        if(LayoutAdapter.COMLUMES == 3){
            path = FilePathConstant.APK_ICON_DIR+"main"+path;
            titlePath = FilePathConstant.SECOND_ICON+titlePath;
        }else{
            path = FilePathConstant.SECOND_ICON+path;
            titlePath = FilePathConstant.SECOND_ICON+titlePath;
        }
        Drawable drawable = AppInfo.createStateDrawable(path);
        Drawable titleDrawable = AppInfo.createStateDrawable(titlePath);
        appInfoViewHolder.container.setBackground(drawable);
        appInfoViewHolder.title.setBackground(titleDrawable);
    }

    public  final class AppInfoViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView content;
        View container;

        public AppInfoViewHolder(final View itemView) {
            super(itemView);
//            Drawable backDrawable = AppInfo.createStateDrawable(FilePathConstant.SECOND_ICON+"booktitlebox.png");

            content = (ImageView) itemView.findViewById(R.id.contentPanel);
            title = (TextView) itemView.findViewById(R.id.title);
            container = itemView.findViewById(R.id.container);
            title.setTextSize(38);
            if(LayoutAdapter.COMLUMES == 3) {
                title.setPadding(0, 0, 0, 40);
            }else
                title.setPadding(0, 0, 0, 10);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){
                        BaseEntity entity =getData().get(getLayoutPosition());
                        if(entity instanceof BookEntity){
                            BookEntity bookEntity = (BookEntity)entity;
                            mOnItemClickListener.onItemClick(itemView, bookEntity);
                            RecentReadEntity recentReadEntity = new RecentReadEntity();
                            recentReadEntity.setBookid(bookEntity.get_id());
                            recentReadEntity.setParentid(bookEntity.getParentid());
                            AppInfo.saveREcentRead(recentReadEntity);
                            Log.i("tag",bookEntity.get_id()+"????");
                        } else {
                            AppInfoEntity appInfoEntity = (AppInfoEntity) entity;
                            mOnItemClickListener.onItemClick(itemView, appInfoEntity);
                        }

                    }
                }
            });
            content.getLayoutParams().width = imgwith;
            content.getLayoutParams().height = imgHeight;
            if(childrenWith>0){
                itemView.getLayoutParams().width = childrenWith;
            }
            if(height>0){
                itemView.getLayoutParams().height = height;
            }

        }
    }
}
