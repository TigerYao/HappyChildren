package com.xf.pageviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.BaseAdapter;

import com.cooki.tiger.childbook.R;

import java.io.File;

public class MainActivity extends Activity {

	private String m_sBookPath;   //���·��
	private int    m_nStyle;      //0 ��ҳ��  1  ˫ҳ
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String  sdCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
        String rev = getIntent().getData().getPath();
        String num = rev.substring(rev.length() - 1);

        m_nStyle =Integer.parseInt(num);
        m_sBookPath = rev.substring(0, rev.length() - 1);
        Log.i("tag",rev+"???"+m_sBookPath);
//    	m_sBookPath = getIntent().getData().getPath();
//
//    	m_nStyle = 0;
    	
        setContentView(R.layout.activity_main);
        PageWidget page = (PageWidget) findViewById(R.id.main_pageWidget);
        if(!TextUtils.isEmpty(m_sBookPath)) {
            PageWidgetAdapter adapter = new PageWidgetAdapter(this, m_sBookPath, m_nStyle);
            page.setAdapter(adapter);
        }
    }


    
}
