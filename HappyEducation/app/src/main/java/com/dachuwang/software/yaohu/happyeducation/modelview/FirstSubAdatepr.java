package com.dachuwang.software.yaohu.happyeducation.modelview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.activity.CartongBooksActivity;
import com.dachuwang.software.yaohu.happyeducation.activity.CartongBooksActivity_;
import com.dachuwang.software.yaohu.happyeducation.base.AppInfo;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewAdapter;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewInterface;

import java.util.ArrayList;


/**
 * Created by yaohu on 15/8/25.
 * email yaohu@dachuwang.com
 */
public class FirstSubAdatepr extends RecyclerViewAdapter<AppInfoEntity>{
    public  ArrayList<AppInfoEntity> data;
    private int childrenWith = 0;
    public static int height =0;
    public FirstSubAdatepr(ArrayList<AppInfoEntity> data,int childrenWith) {
        super(data);
        this.data = data;
        this.childrenWith = childrenWith;
    }

    public FirstSubAdatepr(ArrayList<AppInfoEntity> data, int mode, int toolBarHeight) {
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
    public void onBindDataViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        final AppInfoEntity entity = getData().get(position);
        final AppInfoViewHolder appInfoViewHolder = (AppInfoViewHolder)viewHolder;
        appInfoViewHolder.title.setText(entity.getAppname());
        appInfoViewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getmOnItemClickListener()!=null){
                    getmOnItemClickListener().onItemClick(appInfoViewHolder.itemView,entity);
                }
            }
        });
//        childrenWith = childrenWith-appInfoViewHolder.itemView.getPaddingLeft()-appInfoViewHolder.itemView.getPaddingRight();
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

        public AppInfoViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            content = (ImageView) itemView.findViewById(R.id.contentPanel);
            title = (TextView) itemView.findViewById(R.id.title);

        }
    }
}
