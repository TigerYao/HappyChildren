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

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.base.AppInfo;
import com.dachuwang.software.yaohu.mylibrary.model.BaseEntity;
import com.dachuwang.software.yaohu.mylibrary.widget.CirclePageIndicator;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewPager;

import java.util.ArrayList;

public class HorizontalLayoutFragment extends Fragment {
    private View mViewRoot;
    private RecyclerViewPager mRecyclerView;
    private Toast mToast;
    private CirclePageIndicator indicator;
    public LayoutAdapter mAdapter;
    private ImageView  up,next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mViewRoot == null) {
            mViewRoot = inflater.inflate(R.layout.layout_horizontal, container, false);
        } else if (mViewRoot.getParent() != null) {
            ((ViewGroup) mViewRoot.getParent()).removeView(mViewRoot);
        }
        return mViewRoot;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();
        mToast = Toast.makeText(activity, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mRecyclerView = (RecyclerViewPager) view.findViewById(R.id.list);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layout);
       mAdapter = new LayoutAdapter(activity, mRecyclerView,AppInfo.appInfoEntityList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        updateState(RecyclerView.SCROLL_STATE_IDLE);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                updateState(scrollState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int childCount = mRecyclerView.getChildCount();
                int width = mRecyclerView.getChildAt(0).getWidth();
                int padding = (mRecyclerView.getWidth() - width) / 2;
                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);

                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });

        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mRecyclerView.getChildCount() < 3) {
                    if (mRecyclerView.getChildAt(1) != null) {
                        if (mRecyclerView.getCurrentPosition() == 0) {
                            View v1 = mRecyclerView.getChildAt(1);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = mRecyclerView.getChildAt(0);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (mRecyclerView.getChildAt(0) != null) {
                        View v0 = mRecyclerView.getChildAt(0);
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    if (mRecyclerView.getChildAt(2) != null) {
                        View v2 = mRecyclerView.getChildAt(2);
                        v2.setScaleY(0.9f);
                        v2.setScaleX(0.9f);
                    }
                }

            }
        });
        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mAdapter.getItemCount() - 1) {
                    next.setVisibility(View.INVISIBLE);
                } else if (position == 0) {
                    up.setVisibility(View.INVISIBLE);
                } else if (position > 0 && position < mAdapter.getItemCount()) {
                    if (next.getVisibility() == View.INVISIBLE)
                        next.setVisibility(View.VISIBLE);
                    if (up.getVisibility() == View.INVISIBLE)
                        up.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        indicator.setRecyclerViewPager(mRecyclerView);
        up = (ImageView) mViewRoot.findViewById(R.id.up);
        next = (ImageView) mViewRoot.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(mRecyclerView.getCurrentPosition()+1);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(mRecyclerView.getCurrentPosition()-1);
            }
        });

        if(mAdapter.getItemCount()<=1){
            next.setVisibility(View.INVISIBLE);
        }
        Drawable upDrawalbe= AppInfo.createStateDrawable(getResources().getDrawable(R.mipmap.check_flipthearrowleft),getResources().getDrawable(R.mipmap.check_flipthearrowleft_select));
        up.setImageDrawable(upDrawalbe);
    }


    private void updateState(int scrollState) {
        String stateName = "Undefined";
        switch (scrollState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                stateName = "Idle";
                break;

            case RecyclerView.SCROLL_STATE_DRAGGING:
                stateName = "Dragging";
                break;

            case RecyclerView.SCROLL_STATE_SETTLING:
                stateName = "Flinging";
                break;
        }
    }

    public void updateAdapter(ArrayList<?extends BaseEntity> entities,int comul){
        mAdapter.updateDatas(entities,comul);
    }
}
