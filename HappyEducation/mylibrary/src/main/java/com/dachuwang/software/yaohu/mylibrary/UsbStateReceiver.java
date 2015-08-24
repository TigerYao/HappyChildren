package com.dachuwang.software.yaohu.mylibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by yaohu on 15/8/24.
 * email yaohu@dachuwang.com
 * 通过广播传递过来的intent.getData()会得到一个uri，然后uri.getPath()就是插上usb的路径，可以记录下每次插上或者拔出的usb的路径。起到一些动态的控制等等。
 */
public class UsbStateReceiver extends BroadcastReceiver {

    public static final int USB_STATE_MSG = 0x00020;
    public static final int USB_STATE_ON = 0x00021;
    public static final int USB_STATE_OFF = 0x00022;
    private static final String TAG = "UsbStateReceiver";
    private Context mContext;
    private Handler mHandler;

    public UsbStateReceiver(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    public void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_CHECKING);
        filter.addAction(Intent.ACTION_MEDIA_EJECT);
        filter.addAction(Intent.ACTION_MEDIA_REMOVED);
        // 必须要有此行，否则无法收到广播
        filter.addDataScheme("file");
        this.mContext.registerReceiver(this, filter);
    }

    public void unRegisterReceiver() {
        this.mContext.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        if (this.mHandler == null) {
            Log.v(TAG, "usb state change but handler is null");
            return;
        }

        Log.v(TAG, "usb action = " + arg1.getAction());
        Message msg = new Message();
        msg.what = USB_STATE_MSG;
        if (arg1.getAction().equals(Intent.ACTION_MEDIA_MOUNTED) ||
                arg1.getAction().equals(Intent.ACTION_MEDIA_CHECKING)) {
            msg.arg1 = USB_STATE_ON;
        } else {
            msg.arg1 = USB_STATE_OFF;
        }
        msg.obj=arg1.getData();
        this.mHandler.sendMessage(msg);
    }
}
