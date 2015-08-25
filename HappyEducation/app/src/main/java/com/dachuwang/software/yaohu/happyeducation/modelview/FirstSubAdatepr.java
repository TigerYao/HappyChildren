package com.dachuwang.software.yaohu.happyeducation.modelview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewAdapter;
import java.util.ArrayList;


/**
 * Created by yaohu on 15/8/25.
 * email yaohu@dachuwang.com
 */
public class FirstSubAdatepr extends RecyclerViewAdapter<AppInfoEntity>{

    public FirstSubAdatepr(ArrayList<AppInfoEntity> data) {
        super(data);
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
    public void onBindDataViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        AppInfoEntity entity = getData().get(position);
        AppInfoViewHolder appInfoViewHolder = (AppInfoViewHolder)viewHolder;
//        appInfoViewHolder.content.setImageURI(Uri.parse(entity.getIcon()));
//        appInfoViewHolder.title.setText(position+"");

    }

    public static final class AppInfoViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView content;

        public AppInfoViewHolder(View itemView) {
            super(itemView);
            content = (ImageView) itemView.findViewById(R.id.contentPanel);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
