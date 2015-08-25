package com.dachuwang.software.yaohu.mylibrary.widget;

import android.view.View;

public class RecyclerViewInterface {
    public RecyclerViewInterface() {
    }

    public interface OnFooterViewClickListener<T> {
        void onFooterViewClick(View var1, T var2);
    }

    public interface OnHeaderViewClickListener<T> {
        void onHeaderViewClick(View var1, T var2);
    }

    public interface OnErrorViewClickListener {
        void onErrorViewClick(View var1);
    }

    public interface OnEmptyViewClickListener {
        void onEmptyViewClick(View var1);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View var1, T var2);
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View var1, T var2);
    }
}
