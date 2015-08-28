/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dachuwang.software.yaohu.happyeducation.modelview;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;


public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {
    private static final int COUNT = 13;

    public static  int COMLUMES=3;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private final List<AppInfoEntity> mItems;
    private int mCurrentItemId = 0;
    int width = 0;
    private RecyclerViewInterface.OnItemClickListener onItemClickListener;
    public LayoutAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mItems = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            addItem(i);
        }
        mRecyclerView = recyclerView;

    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        AppInfoEntity entity = new AppInfoEntity();
        entity.setAppname("app"+position);
        mItems.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, int position) {
        holder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(holder.container.getContext(),LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(layoutManager);
        int startPostion = position*COMLUMES;
        int lastPosition= startPostion+COMLUMES;
        if(startPostion>=mItems.size()){
            startPostion = mItems.size()-1;
        }
        if(lastPosition>=mItems.size()){
            lastPosition = mItems.size();
        }
        final ArrayList<AppInfoEntity> subList = new ArrayList<>();
        for(int i=startPostion;i<lastPosition;i++){
            subList.add(mItems.get(i));
        }
        if(width==0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                     width = (holder.container.getWidth()-holder.container.getPaddingRight()*2)/ COMLUMES;
                     onBindList(holder, subList);
                }
            }, 500);
        }else {
            onBindList(holder, subList);
        }
    }

    private void onBindList(SimpleViewHolder holder, ArrayList<AppInfoEntity> subList) {
        FirstSubAdatepr adatepr = new FirstSubAdatepr(subList, width);
        holder.recyclerView.setAdapter(adatepr);
        adatepr.setOnItemClickListener(new RecyclerViewInterface.OnItemClickListener() {
            @Override
            public void onItemClick(View var1, Object var2) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(var1, var2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size()/COMLUMES+(mItems.size()%3);
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout container;
        public final RecyclerView recyclerView;
        public SimpleViewHolder(View view) {
            super(view);
            container = (LinearLayout) view.findViewById(R.id.container);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        }
    }

    public void setOnItemClickListener(RecyclerViewInterface.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
