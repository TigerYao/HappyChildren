package com.dachuwang.software.yaohu.happyeducation.modelview;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.AppInfo;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;
import com.dachuwang.software.yaohu.mylibrary.model.BaseEntity;
import com.dachuwang.software.yaohu.mylibrary.model.BookEntity;
import com.dachuwang.software.yaohu.mylibrary.model.RecentReadEntity;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewAdapter;

import java.util.ArrayList;


/**
 * Created by yaohu on 15/8/25.
 * email yaohu@dachuwang.com
 */
public class FirstSubAdatepr extends RecyclerViewAdapter<BaseEntity>{
    public  ArrayList<? extends BaseEntity> data;
    public static int childrenWith = 0;
    public static int height =0;
    public FirstSubAdatepr(ArrayList<BaseEntity> data,int childrenWith) {
        super(data);
        this.data = data;
        this.childrenWith = childrenWith;

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
        if(entity instanceof AppInfoEntity || entity instanceof RecentReadEntity){
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
        if(childrenWith>0){
            appInfoViewHolder.itemView.getLayoutParams().width = childrenWith;
        }
        if(height>0){
            appInfoViewHolder.itemView.getLayoutParams().height = height;
        }
    }

    public  final class AppInfoViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView content;
        View itemView;

        public AppInfoViewHolder(final View itemView) {
            super(itemView);
            this.itemView = itemView;
            content = (ImageView) itemView.findViewById(R.id.contentPanel);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){
                        BaseEntity entity =getData().get(getLayoutPosition());
                        Log.i("tag",entity==null?"yes":"no");
                       mOnItemClickListener.onItemClick(itemView, entity);
                    }
                }
            });

        }
    }
}
