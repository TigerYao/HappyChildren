package com.cooki.tiger.childbook.fileutil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.cooki.tiger.childbook.R;

/**
 * Created by yaohu on 15/10/17.
 */
public class imageutils {

    public static Drawable toConformBitmap(Bitmap background, Bitmap foreground) {
        if( background == null ) {
            return null;
        }
        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        int fgWidth = foreground.getWidth();
        int fgHeight = foreground.getHeight();
        //create the new blank bitmap 创建一个新的和SRC长度宽度一样的位图
        Bitmap newbmp = Bitmap.createBitmap(fgWidth, fgHeight, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newbmp);
        //draw bg into
        cv.drawBitmap(background, fgWidth/2, 200, null);//在 0，0坐标开始画入bg
        //draw fg into
        cv.drawBitmap(foreground, 0, 0, null);//在 0，0坐标开始画入fg ，可以从任意位置画入
        //save all clip
        cv.save(Canvas.ALL_SAVE_FLAG);//保存
        //store
        cv.restore();//存储
        return new BitmapDrawable(newbmp);
    }

    public static Drawable covageBitmap(Drawable back,Drawable fore){
        BitmapDrawable backBitm = (BitmapDrawable) back;
        BitmapDrawable foreBitm = (BitmapDrawable) fore;
        return  toConformBitmap(backBitm.getBitmap(),foreBitm.getBitmap());
    }

    public static Drawable bitmapDrawable(boolean left,Drawable drawable){
        BitmapDrawable drawable1 = (BitmapDrawable) drawable;
        Bitmap bitmap =null;
        if(!left) {
             bitmap = imageCrop(drawable1.getBitmap());
        }else{
            bitmap = imageleftCrop(drawable1.getBitmap());
        }
        drawable1 = new BitmapDrawable(bitmap);

        return drawable1;
    }

    /**
     * 按正方形裁切图片
     */
    public static Bitmap imageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int wh = w/2; //裁切后所取的正方形区域边长

        int retX = w/2;//基于原图，取正方形左上角x坐标
        int retY = 0;

        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, wh, h, null, false);
    }

    /**
     * 按正方形裁切图片
     */
    public static Bitmap imageleftCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int wh = w/2; //裁切后所取的正方形区域边长

        int retX = 0;//基于原图，取正方形左上角x坐标
        int retY = 0;

        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, wh, h, null, false);
    }


    /**
     * 将图片纠正到正确方向
     *
     * @param degree ： 图片被系统旋转的角度
     * @param bitmap ： 需纠正方向的图片
     * @return 纠向后的图片
     */
    public static Bitmap rotateBitmap(int degree, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);

        Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bm;
    }
}
