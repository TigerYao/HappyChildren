package com.dachuwang.software.yaohu.happyeducation;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public int index = 0;
    @ViewById
    Toolbar toolbar;
    @ViewById
    ImageView wallpaper;
    @FragmentById
    Fragment fragment;
    @ViewById
    ImageView help;
    @ViewById
    ImageView settings;
    @ViewById
    ImageView backup;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        index = savedInstanceState.getInt("index");

    }

    @AfterViews
    public void viewEvent() {
        help.setOnClickListener(this);
        settings.setOnClickListener(this);
        backup.setOnClickListener(this);
        toolbar.setTitle("nihao");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        Bitmap bitmap =BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        colorChange(bitmap);
    }


    @Background(delay = 2000, id = "updatewallpaper")
    public void caculaterWallPaperChage() {
        index++;
        updageWallpaper();
        Log.i("vv", index + "");

    }

    @UiThread
    public void updageWallpaper() {
        caculaterWallPaperChage();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("index", index);
        Log.i("vv", index + "dd");
    }


    @Override
    protected void onPause() {
        super.onPause();
        BackgroundExecutor.cancelAll("updatewallpaper", true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updageWallpaper();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.help:
                break;
            case R.id.settings:
                break;
            case R.id.backup:
                break;
        }
    }

    /**
     * 界面颜色的更改
     */
    private void colorChange(Bitmap bitmap) {
        // 用来提取颜色的Bitmap

        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            /**
             * 提取完之后的回调方法
             */
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
//				/* 界面颜色UI统一性处理,看起来更Material一些 */
                fragment.getView().setBackgroundColor(colorBurn(vibrant.getRgb()));
                toolbar.setBackgroundColor(vibrant.getRgb());
                toolbar.setAlpha(0.5f);
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    // 很明显，这两货是新API才有的。
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     *            RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *            Android中我们一般使用它的16进制，
     *            例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *            red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *            所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

}
