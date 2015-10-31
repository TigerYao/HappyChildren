/*
   Copyright 2012 Harri Smatt

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.cooki.tiger.childbook.widget.curl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cooki.tiger.childbook.R;
import com.cooki.tiger.childbook.fileutil.FileReaderUtil;
import com.cooki.tiger.childbook.fileutil.imageutils;

import java.io.File;
import java.net.URI;

/**
 * Simple Activity for curl testing.
 * 
 * @author harism
 */
public class CurlActivity extends Activity {

	private CurlView mCurlView;
	File names[];
    String rootPath;
	private ImageView img,next;
	private int mode =1;
	AlertDialog.Builder builder;
	Handler hander = new Handler(){
		@Override
		public void handleMessage(Message msg) {
//			builder.show();
			new AlertDialog.Builder(CurlActivity.this).setMessage("确定退出？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					finish();
				}
			}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).show();
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		Intent intent = getIntent();
		rootPath = intent.getDataString();
		String rev = rootPath.substring(rootPath.length()-1);
		mode =Integer.parseInt(rev)+1;
		rootPath = rootPath.substring(0,rootPath.length()-1);
		img = (ImageView)findViewById(R.id.img);
		next = (ImageView)findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurlView.setCurrentIndex(mCurlView.getCurrentIndex() + 1);
			}
		});
		findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurlView.setCurrentIndex(mCurlView.getCurrentIndex() - 1);
			}
		});



//		rootPath = "mnt/internal_sd/happychildren/covert/";
//		Log.i("tag",rootPath);
		File filebg =new File(URI.create(rootPath));
		 names = FileReaderUtil.getFile(filebg.getAbsolutePath() + File.separator + "jpg/");
		int index = 0;
		if (getLastNonConfigurationInstance() != null) {
			index = (Integer) getLastNonConfigurationInstance();
		}
		mCurlView = (CurlView) findViewById(R.id.curl);
		mCurlView.setPageProvider(new PageProvider());
		mCurlView.setSizeChangedObserver(new SizeChangedObserver());
		mCurlView.setCurrentIndex(index);
		mCurlView.setBackgroundColor(Color.WHITE);


		// This is something somewhat experimental. Before uncommenting next
		// line, please see method comments in CurlView.
		 mCurlView.setEnableTouchPressure(true);
		mCurlView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				hander.sendEmptyMessage(0);
				return true;
			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
		mCurlView.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		mCurlView.onResume();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return mCurlView.getCurrentIndex();
	}

	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {

		// Bitmap resources.
//		private int[] names = { R.drawable.photo1, R.drawable.photo2,
//				R.drawable.photo3, R.drawable.photo4,
//				R.drawable.photo5, R.drawable.photo6  };

		@Override
		public int getPageCount() {
			if(names==null||names.length ==0){
				return 0;
			}
//			if(mode == 1){
//				return names.length;
//			}
			return (int) Math.ceil(names.length/mode);
		}

		private Bitmap loadBitmap(int width, int height, int index) {
			Log.i("tag", index + "");
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFC0C0C0);
			Canvas c = new Canvas(b);


			Drawable ddd = Drawable.createFromPath(names[index].getAbsolutePath());
			Drawable dd = Drawable.createFromPath(names[index].getAbsolutePath().replace("bg.jpg", "text_ch_1.png"));
			Drawable ddds= getResources().getDrawable(R.drawable.doublepage);
			if(index == 0){
				if(mode == 1){
				 	ddds = getResources().getDrawable(R.drawable.singlecoverpage);
				}else {
					ddds = getResources().getDrawable(R.drawable.doublecoverpage);
				}
				ddd = imageutils.covageBitmap(ddd,ddds);
			}else{
				if(mode  == 1){
					ddds = getResources().getDrawable(R.drawable.singlepage);
				}else
					ddds = imageutils.bitmapDrawable(index%2!=0,ddds);
			}
			if(dd == null)
				dd = ddd;
			LayerDrawable d = new LayerDrawable(new Drawable[]{ddd,dd,ddds});
			d.setLayerInset(0,1,1,1,1);
			d.setLayerInset(1,2,2,2,2);
			d.setLayerInset(2,3,3,3,3);
			if(d==null)
				return null;

			int margin = 0;
			int border = 0;
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			int imageWidth = r.width() - (border * 2);
			int imageHeight = imageWidth * d.getIntrinsicHeight()
					/ d.getIntrinsicWidth();
			if (imageHeight > r.height() - (border * 2)) {
				imageHeight = r.height() - (border * 2);
				imageWidth = imageHeight * d.getIntrinsicWidth()
						/ d.getIntrinsicHeight();
			}

			r.left += ((r.width() - imageWidth) / 2) - border;
			r.right = r.left + imageWidth + border + border;
			r.top += ((r.height() - imageHeight) / 2) - border;
			r.bottom = r.top + imageHeight + border + border;

			Paint p = new Paint();
			p.setColor(0xFFC0C0C0);
			c.drawRect(r, p);
			r.left += border;
			r.right -= border;
			r.top += border;
			r.bottom -= border;

			d.setBounds(r);
			d.draw(c);

			return b;
		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {
//			if(index == 0){
//				mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
//				mCurlView.setMargins(.1f, .1f, .1f, .1f);
//			}else{
//				mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
//				mCurlView.setMargins(.1f, .05f, .1f, .05f);
//			}

			Bitmap fronts = loadBitmap(width, height, index*mode);
			page.setTexture(fronts, CurlPage.SIDE_FRONT);
			if(mode==2&&index*mode+1<names.length) {
				Bitmap back = loadBitmap(width, height, index*mode+1);
				page.setTexture(back, CurlPage.SIDE_BACK);
			}else
				page.setColor(Color.argb(125, 180, 180, 180), CurlPage.SIDE_BACK);

//			switch (index) {
//			// First case is image on front side, solid colored back.
//			case 0: {
//				Bitmap front = loadBitmap(width, height, 0);
//				page.setTexture(front, CurlPage.SIDE_FRONT);
//				page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
//				break;
//			}
////			// Second case is image on back side, solid colored front.
//			case 1: {
//				Bitmap back = loadBitmap(width, height, 2);
//				page.setTexture(back, CurlPage.SIDE_BACK);
//				page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
//				break;
//			}
//			// Third case is images on both sides.
//			case 2: {
//				Bitmap front = loadBitmap(width, height, 1);
//				Bitmap back = loadBitmap(width, height, 3);
//				page.setTexture(front, CurlPage.SIDE_FRONT);
//				page.setTexture(back, CurlPage.SIDE_BACK);
//				break;
//			}
//			// Fourth case is images on both sides - plus they are blend against
//			// separate colors.
//			case 3: {
//				Bitmap front = loadBitmap(width, height, 2);
//				Bitmap back = loadBitmap(width, height, 1);
//				page.setTexture(front, CurlPage.SIDE_FRONT);
//				page.setTexture(back, CurlPage.SIDE_BACK);
//				page.setColor(Color.argb(127, 170, 130, 255),
//						CurlPage.SIDE_FRONT);
//				page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
//				break;
//			}
////			// Fifth case is same image is assigned to front and back. In this
////			// scenario only one texture is used and shared for both sides.
//			case 4:
//				Bitmap front = loadBitmap(width, height, 0);
//				page.setTexture(front, CurlPage.SIDE_BOTH);
//				page.setColor(Color.argb(127, 255, 255, 255),
//						CurlPage.SIDE_BACK);
//				break;
//			default:
//				Bitmap fronts = loadBitmap(width, height, index);
//				Bitmap back = loadBitmap(width, height, index-1);
//				page.setTexture(fronts, CurlPage.SIDE_FRONT);
//				page.setTexture(back, CurlPage.SIDE_BACK);
//				break;
//			}

		}

	}

	/**
	 * CurlView size changed observer.
	 */
	private class SizeChangedObserver implements CurlView.SizeChangedObserver {
		@Override
		public void onSizeChanged(int w, int h) {
			if (mode == 2) {
				mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
				mCurlView.setMargins(.1f, .05f, .1f, .05f);
			} else {
				mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
				mCurlView.setMargins(.1f, .1f, .1f, .1f);
			}
		}
	}

}