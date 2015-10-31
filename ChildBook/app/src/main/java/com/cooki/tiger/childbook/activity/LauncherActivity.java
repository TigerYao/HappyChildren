package com.cooki.tiger.childbook.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;

import com.cooki.tiger.childbook.R;
import com.cooki.tiger.childbook.base.BaseActivity;
import com.cooki.tiger.childbook.fileutil.FileReaderUtil;
import com.cooki.tiger.childbook.widget.DoublePagerView;

import java.io.File;
import java.net.URI;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LauncherActivity extends BaseActivity {

    private View mControlsView;
    private String rootPath;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        rootPath = intent.getDataString();

        setContentView(R.layout.activity_launcher);
        DoublePagerView page = (DoublePagerView) findViewById(R.id.fullscreen_content);
        File filebg =new File(URI.create(rootPath+File.separator+"bg"));
        String names[] = FileReaderUtil.getFiles(filebg.getAbsolutePath());
        BaseAdapter adapter = new PageWidgetAdapter(this,names);
        page.setAdapter(adapter);
//        mControlsView = findViewById(R.id.fullscreen_content_controls);
        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mControlsView.setVisibility(View.GONE);
            }
        });
    }
}
