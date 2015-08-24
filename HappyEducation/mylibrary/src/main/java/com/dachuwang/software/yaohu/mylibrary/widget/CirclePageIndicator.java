package com.dachuwang.software.yaohu.mylibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dachuwang.software.yaohu.mylibrary.R;

import java.lang.reflect.Field;

/**
 * Created by liangfei on 3/26/15.
 */
public class CirclePageIndicator extends LinearLayout implements RecyclerViewPager.OnPageChangedListener {

    public static final int DEFAULT_INDICATOR_SPACING = 5;

    public static final int INDICATOR_TYPE_CIRCLE = 0;
    public static final int INDICATOR_TYPE_FRACTION = 1;
    public static final int DEFAULT_INDICATOR_TYPE = INDICATOR_TYPE_CIRCLE;

    private int mActivePosition;
    private int mIndicatorSpacing;
    private int mIndicatorType;

    private RecyclerViewPager.OnPageChangedListener mUserDefinedPageChangeListener;
    private int size = 0;
    private int strokenDrawableId, solidDrawableId;

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

//    public CirclePageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//
//    }

    public CirclePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CirclePageIndicator, 0, 0);
        try {
            mIndicatorSpacing = a.getDimensionPixelSize(
                    R.styleable.CirclePageIndicator_indicator_spacing,
                    DEFAULT_INDICATOR_SPACING);
            mIndicatorType = a.getInt(
                    R.styleable.CirclePageIndicator_indicator_type,
                    DEFAULT_INDICATOR_TYPE);
            strokenDrawableId = a.getResourceId(R.styleable.CirclePageIndicator_indicator_drawable_normal, R.drawable.circle_indicator_stroke);
            solidDrawableId = a.getResourceId(R.styleable.CirclePageIndicator_indicator_drawable_selector, R.drawable.circle_indicator_solid);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        if (!(getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.BOTTOM | Gravity.START;
            setLayoutParams(params);
        }
    }

    public void setRecyclerViewPager(RecyclerViewPager pager) {
        mUserDefinedPageChangeListener = getOnPageChangeListener(pager);
        mActivePosition = pager.getCurrentPosition();
        pager.addOnPageChangedListener(this);
        addIndicator(pager.getAdapter().getItemCount());
    }

    public void setRecyclerViewPager(RecyclerViewPager pager, int count, RecyclerViewPager.OnPageChangedListener pageChangeListener) {
        mUserDefinedPageChangeListener = pageChangeListener;
        pager.addOnPageChangedListener(this);
        mActivePosition = pager.getCurrentPosition();
        addIndicator(count);
        size = count;
    }

    private void addIndicator(int count) {
        if (count <= 1) return;
        if (mIndicatorType == INDICATOR_TYPE_CIRCLE) {
            removeAllViews();
            for (int i = 0; i < count; i++) {
                ImageView img = new ImageView(getContext());
                LayoutParams params = new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.leftMargin = mIndicatorSpacing;
                params.rightMargin = mIndicatorSpacing;
                img.setImageResource(strokenDrawableId);
                addView(img, params);
            }
            ((ImageView) getChildAt(mActivePosition)).setImageResource(solidDrawableId);
        } else if (mIndicatorType == INDICATOR_TYPE_FRACTION) {
            TextView textView = new TextView(getContext());
            textView.setTag(count);
            LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            textView.setText("1/" + count);
            addView(textView, params);
        }
    }

    private int getPosition(int position) {
        return false ? position % size : position;
    }

    private void updateIndicator(int position) {
        position = getPosition(position);
        if (mActivePosition != position) {
            if (mIndicatorType == INDICATOR_TYPE_CIRCLE) {
                ((ImageView) getChildAt(mActivePosition)).setImageResource(strokenDrawableId);
                ((ImageView) getChildAt(position)).setImageResource(solidDrawableId);
            } else if (mIndicatorType == INDICATOR_TYPE_FRACTION) {
                TextView textView = (TextView) getChildAt(0);
                //noinspection RedundantCast
                textView.setText(String.format("%d/%d", position + 1, textView.getTag()));
            }
            mActivePosition = position;
        }
    }

    private RecyclerViewPager.OnPageChangedListener getOnPageChangeListener(RecyclerViewPager pager) {
        try {
            Field f = pager.getClass().getDeclaredField("mOnPageChangeListener");
            f.setAccessible(true);
            return (RecyclerViewPager.OnPageChangedListener) f.get(pager);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        if (mUserDefinedPageChangeListener != null) {
//            mUserDefinedPageChangeListener.OnPageChanged(position, positionOffset, positionOffsetPixels);
//        }
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        updateIndicator(position);
//        if (mUserDefinedPageChangeListener != null) {
//            mUserDefinedPageChangeListener.onPageSelected(position);
//        }
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//        if (mUserDefinedPageChangeListener != null) {
//            mUserDefinedPageChangeListener.onPageScrollStateChanged(state);
//        }
//    }

    @Override
    public void OnPageChanged(int oldPosition, int newPosition) {
        updateIndicator(newPosition);
        if (mUserDefinedPageChangeListener != null)
            mUserDefinedPageChangeListener.OnPageChanged(oldPosition, newPosition);
    }
}

