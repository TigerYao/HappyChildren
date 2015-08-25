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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.mylibrary.model.AppInfoEntity;

import java.util.ArrayList;
import java.util.List;


public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {
    private static final int COUNT = 13;

    private static final int COMLUMES=3;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private final List<AppInfoEntity> mItems;
    private int mCurrentItemId = 0;

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
        mItems.add(position, new AppInfoEntity());
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
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(holder.container.getContext(),LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(layoutManager);
        int lastPosition= position+COMLUMES;
        if(lastPosition>=mItems.size()){
            lastPosition = mItems.size()-1;
        }
        ArrayList<AppInfoEntity> subList = new ArrayList<>();
        for(int i=position;i<lastPosition;i++){
            subList.add(mItems.get(i));
        }
        FirstSubAdatepr adatepr = new FirstSubAdatepr(subList);
        holder.recyclerView.setAdapter(adatepr);
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
}
