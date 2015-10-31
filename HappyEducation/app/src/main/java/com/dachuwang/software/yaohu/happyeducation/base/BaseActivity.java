package com.dachuwang.software.yaohu.happyeducation.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dachuwang.software.yaohu.happyeducation.R;
import com.dachuwang.software.yaohu.happyeducation.util.ConstantUtils;
import com.dachuwang.software.yaohu.mylibrary.file.FilePathConstant;
import com.dachuwang.software.yaohu.mylibrary.widget.CustomProgressDialog;

/**
 * Created by yaohu on 15/8/26.
 * email yaohu@dachuwang.com
 */
public class BaseActivity extends AppCompatActivity {
    /**
     * 加载框的文字说明.
     */
    public String mProgressMessage = "请稍候...";

    /**
     * 全局的加载框对象，已经完成初始化.
     */
    public ProgressDialog mProgressDialog;

    /**
     * 底部弹出的Dialog.
     */
    private Dialog mBottomDialog;

    /**
     * 居中弹出的Dialog.
     */
    private Dialog mCenterDialog;

    /**
     * 底部弹出的Dialog的View.
     */
    private View mBottomDialogView = null;

    /**
     * 居中弹出的Dialog的View.
     */
    private View mCenterDialogView = null;
    /**
     * 自定义进度弹框.
     */
    private CustomProgressDialog progressDialog = null;
    protected int mStartTime;

    /**
     * 主要Handler类，在线程中可用
     * what：0.提示文本信息,1.等待框   ,2.移除等待框
     */
    private Handler baseHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantUtils.SHOW_TOAST:
                    showToast(msg.getData().getString("Msg"));
                    break;
                case ConstantUtils.SHOW_PROGRESS:
                    showProgressDialog(mProgressMessage);
                    break;
                case ConstantUtils.REMOVE_PROGRESS:
                    removeProgressDialog();
                    break;
                case ConstantUtils.REMOVE_DIALOGBOTTOM:
                    removeDialog(ConstantUtils.DIALOGBOTTOM);
                case ConstantUtils.REMOVE_DIALOGCENTER:
                    removeDialog(ConstantUtils.DIALOGCENTER);
                default:
                    break;
            }
        }
    };


    /**
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */
    public void showToast(String text) {
        Toast.makeText(this, "" + text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 描述：Toast提示文本.
     *
     * @param resId 文本的资源ID
     */
    public void showToast(int resId) {
        Toast.makeText(this, "" + this.getResources().getText(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 描述：在线程中提示文本信息.
     *
     * @param resId 要提示的字符串资源ID，消息what值为0,
     */
    public void showToastInThread(int resId) {
        Message msg = baseHandler.obtainMessage(0);
        Bundle bundle = new Bundle();
        bundle.putString("Msg", this.getResources().getString(resId));
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }

    /**
     * 描述：在线程中提示文本信息.
     *
     * @param toast 消息what值为0
     */
    public void showToastInThread(String toast) {
        Message msg = baseHandler.obtainMessage(0);
        Bundle bundle = new Bundle();
        bundle.putString("Msg", toast);
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }

    /**
     * 描述：显示进度框.
     */
    public void showProgressDialog() {
        showProgressDialog(null);
    }

    /**
     * 描述：显示进度框.
     *
     * @param message the message
     */
    public void showProgressDialog(String message) {
        // 创建一个显示进度的Dialog
        if (!TextUtils.isEmpty(message)) {
            mProgressMessage = message;
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(mProgressMessage);
//            mProgressDialog.setContentView(R.layout.view_loading_material);
            /*Window window = mProgressDialog.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
			window.setGravity(Gravity.CENTER);
			lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
			// 添加动画
			window.setWindowAnimations(android.R.style.Animation_Dialog);
			window.setAttributes(lp);*/
        }
        showDialog(ConstantUtils.DIALOGPROGRESS);
    }

    /**
     * 描述：在底部显示一个Dialog,id为1,在中间显示id为2.
     *
     * @param id           Dialog的类型
     * @param view         指定一个新View
     * @param paddingWidth 如果Dialog不是充满屏幕，要设置这个值
     */
    public void showDialog(int id, View view, int paddingWidth) {

        if (id == ConstantUtils.DIALOGBOTTOM) {
            mBottomDialogView = view;
            if (mBottomDialog == null) {
                mBottomDialog = new Dialog(this);
                mBottomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Window window = mBottomDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                //此处可以设置dialog显示的位置
                window.setGravity(Gravity.BOTTOM);
                //设置宽度
                lp.width = AppInfo.width - paddingWidth;
                lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
                window.setAttributes(lp);
                // 添加动画
                window.setWindowAnimations(android.R.style.Animation_Dialog);
            }
            mBottomDialog.setContentView(mBottomDialogView, new LinearLayout.LayoutParams(AppInfo.width - paddingWidth, LinearLayout.LayoutParams.WRAP_CONTENT));
            showDialog(id);
        } else if (id == ConstantUtils.DIALOGCENTER) {
            mCenterDialogView = view;
            if (mCenterDialog == null) {
                mCenterDialog = new Dialog(this);
                mCenterDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Window window = mCenterDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                //此处可以设置dialog显示的位置
                window.setGravity(Gravity.CENTER);
                //设置宽度
                lp.width = AppInfo.width - paddingWidth;
                lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
                window.setAttributes(lp);
                // 添加动画
                window.setWindowAnimations(android.R.style.Animation_Dialog);
            }
            mCenterDialog.setContentView(mCenterDialogView, new LinearLayout.LayoutParams(AppInfo.width - paddingWidth, LinearLayout.LayoutParams.WRAP_CONTENT));
            showDialog(id);
        }
    }

    /**
     * 描述：对话框初始化.
     *
     * @param id the id
     * @return the dialog
     * @see Activity#onCreateDialog(int)
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case ConstantUtils.DIALOGPROGRESS:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialog(this);
                    mProgressDialog.setMessage(mProgressMessage);
                }
                return mProgressDialog;
            case ConstantUtils.DIALOGBOTTOM:
                return mBottomDialog;
            case ConstantUtils.DIALOGCENTER:
                return mCenterDialog;
            default:
                break;
        }
        return dialog;
    }

    /**
     * 描述：移除进度框.
     */
    public void removeProgressDialog() {
        removeDialog(ConstantUtils.DIALOGPROGRESS);
    }

    /**
     * 描述：移除Dialog.
     *
     * @param id the id
     * @see Activity#removeDialog(int)
     */
    public void removeDialogInThread(int id) {
        baseHandler.sendEmptyMessage(id);
    }

    /**
     * 描述：对话框dialog （确认，取消）.
     *
     * @param title              对话框标题内容
     * @param msg                对话框提示内容
     * @param mOkOnClickListener 点击确认按钮的事件监听
     */
    public void dialogOpen(String title, String msg, DialogInterface.OnClickListener mOkOnClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setPositiveButton("确认", mOkOnClickListener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 描述：对话框dialog （确认，取消）.
     *
     * @param title              对话框标题内容
     * @param msg                对话框提示内容
     * @param mOkOnClickListener 点击确认按钮的事件监听
     */
    public void dialogOpen(String title, String msg, String confirm, String cancel, DialogInterface.OnClickListener mOkOnClickListener, DialogInterface.OnClickListener mCancelOnClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setPositiveButton(confirm, mOkOnClickListener);
        builder.setNegativeButton(cancel, mCancelOnClickListener);
        builder.create().show();
    }

    /**
     * 描述：对话框dialog （无按钮）.
     *
     * @param title 对话框标题内容
     * @param msg   对话框提示内容
     */
    public void dialogOpen(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    public void startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("加载中...");
        }
        progressDialog.show();
    }

    public void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void jumpActivity(Class clz,Bundle bundle){
        Intent intent=new Intent(this,clz);
        if(bundle!=null){
            intent.putExtra("bundle",bundle);
        }
        startActivity(intent);
    }


    public void setBackground(View view ,String path){
        Drawable drawable = Drawable.createFromPath(path);
        view.setBackground(drawable);
    }
    public void setImageViewDrawalbe(ImageView view ,String path){
        Drawable drawable = Drawable.createFromPath(path);
        view.setImageDrawable(drawable);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void replaceFragment(int containId,Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(containId, fragment).commit();
    }


    public void addFrameAnimation(ImageView view){
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading01.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading02.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading03.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading04.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading05.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading06.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading07.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading08.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading09.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading10.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading11.png"),60);
        animationDrawable.addFrame(Drawable.createFromPath(FilePathConstant.APK_ICON_DIR+"loading/loading12.png"),60);
        view.setImageDrawable(animationDrawable);
        animationDrawable.setOneShot(false);
        animationDrawable.start();

    }
}
