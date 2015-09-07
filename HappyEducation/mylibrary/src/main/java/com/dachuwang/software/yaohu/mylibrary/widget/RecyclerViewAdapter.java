package com.dachuwang.software.yaohu.mylibrary.widget;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.os.Handler;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import com.dachuwang.software.yaohu.mylibrary.widget.RecyclerViewInterface.*;
import java.util.ArrayList;

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter {
    public ArrayList<T> mData;
    public ArrayList<Object> mHeaders;
    public ArrayList<Object> mFooters;
    public int mMode;
    protected int mToolBarHeight;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnEmptyViewClickListener mOnEmptyViewClickListener;
    private OnErrorViewClickListener mOnErrorViewClickListener;
    private OnHeaderViewClickListener mOnHeaderViewClickListener;
    private OnFooterViewClickListener mOnFooterViewClickListener;

    public RecyclerViewAdapter(ArrayList<T> data) {
        this(data, 0, 0);
    }

    public RecyclerViewAdapter(ArrayList<T> data, int mode) {
        this(data, mode, 0);
    }

    public RecyclerViewAdapter(ArrayList<T> data, int mode, int toolBarHeight) {
        this(data, (ArrayList)null, (ArrayList)null, mode, toolBarHeight);
    }

    public RecyclerViewAdapter(ArrayList<T> data, ArrayList<Object> headers, ArrayList<Object> footers, int mode, int toolBarHeight) {
        this.mData = null == data?new ArrayList():data;
        this.mHeaders = null == headers?new ArrayList():headers;
        this.mFooters = null == footers?new ArrayList():footers;
        this.mMode = this.mData.isEmpty()?3:mode;
        this.mToolBarHeight = toolBarHeight;
    }

    public void setData(ArrayList<T> data) {
        this.setData(data, 0);
    }

    public void setData(ArrayList<T> data, int mode) {
        this.mData = null == data?new ArrayList():data;
        this.mMode = this.mData.isEmpty()?3:mode;
        this.notifyDataSetChanged();
    }

    public void addData(ArrayList<T> data) {
        if(null != data && !data.isEmpty()) {
            int startPosition = this.mData.size() + this.mHeaders.size();
            this.mData.addAll(data);
            this.notifyItemRangeInserted(startPosition, data.size());
        }
    }

    public ArrayList<T> getData() {
        return this.mData;
    }

    public ArrayList<Object> getHeaders() {
        return this.mHeaders;
    }

    public ArrayList<Object> getFooters() {
        return this.mFooters;
    }

    public void changeMode(int mode) {
        if(mode < 0 || mode > 3) {
            mode = 0;
        }

        this.mMode = mode;
        this.notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder dataViewHolder;
//        if(viewType == 1) {
//            dataViewHolder = this.onCreateLoadingViewHolder(parent);
//            dataViewHolder.itemView.setLayoutParams(new LayoutParams(-1, parent.getHeight() - this.mToolBarHeight));
//            return dataViewHolder;
//        } else if(viewType == 2) {
//            dataViewHolder = this.onCreateErrorViewHolder(parent);
//            dataViewHolder.itemView.setLayoutParams(new LayoutParams(-1, parent.getHeight() - this.mToolBarHeight));
//            dataViewHolder.itemView.setOnClickListener(new OnClickListener() {
//                public void onClick(final View v) {
//                    if(null != RecyclerViewAdapter.this.mOnErrorViewClickListener) {
//                        (new Handler()).postDelayed(new Runnable() {
//                            public void run() {
//                                RecyclerViewAdapter.this.mOnErrorViewClickListener.onErrorViewClick(v);
//                            }
//                        }, 200L);
//                    }
//
//                }
//            });
//            return dataViewHolder;
//        } else if(viewType == 3) {
//            dataViewHolder = this.onCreateEmptyViewHolder(parent);
//            dataViewHolder.itemView.setLayoutParams(new LayoutParams(-1, parent.getHeight() - this.mToolBarHeight));
//            dataViewHolder.itemView.setOnClickListener(new OnClickListener() {
//                public void onClick(final View v) {
//                    if(null != RecyclerViewAdapter.this.mOnEmptyViewClickListener) {
//                        (new Handler()).postDelayed(new Runnable() {
//                            public void run() {
//                                RecyclerViewAdapter.this.mOnEmptyViewClickListener.onEmptyViewClick(v);
//                            }
//                        }, 200L);
//                    }
//
//                }
//            });
//            return dataViewHolder;
//        } else if(viewType == 4) {
//            dataViewHolder = this.onCreateHeaderViewHolder(parent);
//            dataViewHolder.itemView.setOnClickListener(new OnClickListener() {
//                public void onClick(final View v) {
//                    if(null != RecyclerViewAdapter.this.mOnHeaderViewClickListener) {
//                        (new Handler()).postDelayed(new Runnable() {
//                            public void run() {
//                                RecyclerViewAdapter.this.mOnHeaderViewClickListener.onHeaderViewClick(v, v.getTag());
//                            }
//                        }, 200L);
//                    }
//
//                }
//            });
//            return dataViewHolder;
//        } else if(viewType == 5) {
//            dataViewHolder = this.onCreateFooterViewHolder(parent);
//            dataViewHolder.itemView.setOnClickListener(new OnClickListener() {
//                public void onClick(final View v) {
//                    if(null != RecyclerViewAdapter.this.mOnFooterViewClickListener) {
//                        (new Handler()).postDelayed(new Runnable() {
//                            public void run() {
//                                RecyclerViewAdapter.this.mOnFooterViewClickListener.onFooterViewClick(v, v.getTag());
//                            }
//                        }, 200L);
//                    }
//
//                }
//            });
//            return dataViewHolder;
//        } else {
            dataViewHolder = this.onCreateDataViewHolder(parent);
            dataViewHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(final View v) {
                    if(null != RecyclerViewAdapter.this.mOnItemClickListener) {
                        (new Handler()).postDelayed(new Runnable() {
                            public void run() {
                                RecyclerViewAdapter.this.mOnItemClickListener.onItemClick(v, v.getTag());
                            }
                        }, 200L);
                    }

                }
            });
            dataViewHolder.itemView.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(final View v) {
                    if(null != RecyclerViewAdapter.this.mOnItemLongClickListener) {
                        (new Handler()).postDelayed(new Runnable() {
                            public void run() {
                                RecyclerViewAdapter.this.mOnItemLongClickListener.onItemLongClick(v, v.getTag());
                            }
                        }, 200L);
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            return dataViewHolder;
//        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        if(this.mMode == 1) {
            this.onBindLoadingViewHolder(holder, position);
        } else if(this.mMode == 2) {
            this.onBindErrorViewHolder(holder, position);
        } else if(this.mMode == 3) {
            this.onBindEmptyViewHolder(holder, position);
        } else if(position < this.mHeaders.size()) {
            if(this.mHeaders.size() > 0) {
                this.onBindHeaderViewHolder(holder, position);
            }
        } else if(position >= this.mHeaders.size() + this.mData.size()) {
            if(this.mFooters.size() > 0) {
                this.onBindFooterViewHolder(holder, position - this.mHeaders.size() - this.mData.size());
            }
        } else {
            this.onBindDataViewHolder(holder, position - this.mHeaders.size());
        }

    }

    public int getItemCount() {
        return this.mMode == 0?this.mData.size() + this.mHeaders.size() + this.mFooters.size():1;
    }

    public int getItemViewType(int position) {
        return this.mMode == 1?1:(this.mMode == 2?2:(this.mMode == 3?3:(position < this.mHeaders.size()?4:(position >= this.mHeaders.size() + this.mData.size()?5:0))));
    }

    public abstract ViewHolder onCreateDataViewHolder(ViewGroup var1);

    public ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
        return null;
    }

    public ViewHolder onCreateErrorViewHolder(ViewGroup parent) {
        return null;
    }

    public abstract ViewHolder onCreateEmptyViewHolder(ViewGroup var1);

    public ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        return null;
    }

    public abstract void onBindDataViewHolder(ViewHolder var1, int var2);

    public void onBindLoadingViewHolder(ViewHolder holder, int position) {
    }

    public void onBindErrorViewHolder(ViewHolder holder, int position) {
    }

    public void onBindEmptyViewHolder(ViewHolder holder, int position) {
    }

    public void onBindHeaderViewHolder(ViewHolder holder, int position) {
    }

    public void onBindFooterViewHolder(ViewHolder holder, int position) {
    }

    public void setOnFooterViewClickListener(OnFooterViewClickListener onFooterViewClickListener) {
        this.mOnFooterViewClickListener = onFooterViewClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnEmptyViewClickListener(OnEmptyViewClickListener onEmptyViewClickListener) {
        this.mOnEmptyViewClickListener = onEmptyViewClickListener;
    }

    public void setOnErrorViewClickListener(OnErrorViewClickListener onErrorViewClickListener) {
        this.mOnErrorViewClickListener = onErrorViewClickListener;
    }

    public void setOnHeaderViewClickListener(OnHeaderViewClickListener onHeaderViewClickListener) {
        this.mOnHeaderViewClickListener = onHeaderViewClickListener;
    }

    public void setToolBarHeight(int toolBarHeight) {
        this.mToolBarHeight = toolBarHeight;
        this.notifyDataSetChanged();
    }

    public int getFooterCount() {
        return this.mFooters.size();
    }

    public int getHeaderCount() {
        return this.mHeaders.size();
    }

    public void addHeader(Object header) {
        if(this.mMode != 0) {
            Log.e("Recycler View Adapter", "error: you can not add header or footer while you are not in data mode");
        } else {
            if(!this.mHeaders.contains(header)) {
                this.mHeaders.add(header);
                this.notifyItemInserted(this.mHeaders.size() - 1);
            }

        }
    }

    public void removeHeader(Object header) {
        if(this.mHeaders.contains(header)) {
            int position = this.mHeaders.indexOf(header);
            this.mHeaders.remove(position);
            this.notifyItemRemoved(position);
        }

    }

    public void removeHeader(int position) {
        if(this.mHeaders.size() > 0 && position < this.mHeaders.size()) {
            this.mHeaders.remove(position);
            this.notifyItemRemoved(position);
        }

    }

    public void removeAllHeader() {
        if(this.mHeaders.size() > 0) {
            this.notifyItemRangeRemoved(0, this.mHeaders.size());
            this.mHeaders.clear();
            this.notifyDataSetChanged();
        }

    }

    public void addFooter(Object footer) {
        if(this.mMode != 0) {
            Log.e("Recycler View Adapter", "error: you can not add header or footer while you are not in data mode");
        } else {
            if(!this.mFooters.contains(footer)) {
                this.mFooters.add(footer);
                this.notifyItemInserted(this.mHeaders.size() + this.mData.size() + this.mFooters.size() - 1);
            }

        }
    }

    public void removeFooter(Object footer) {
        if(this.mFooters.contains(footer)) {
            int position = this.mFooters.indexOf(footer);
            this.mFooters.remove(position);
            this.notifyItemRemoved(this.mHeaders.size() + this.mData.size() + position);
        }

    }

    public void removeFooter(int position) {
        if(this.mFooters.size() > 0 && position < this.mFooters.size()) {
            this.mFooters.remove(position);
            this.notifyItemRemoved(this.mHeaders.size() + this.mData.size() + position);
        }

    }

    public void removeAllFooters() {
        if(this.mFooters.size() > 0) {
            this.notifyItemRangeChanged(0, this.mFooters.size());
            this.mFooters.clear();
            this.notifyDataSetChanged();
        }

    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }
}
