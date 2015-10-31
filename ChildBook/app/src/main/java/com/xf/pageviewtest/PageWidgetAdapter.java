package com.xf.pageviewtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cooki.tiger.childbook.R;

public class PageWidgetAdapter extends BaseAdapter {

	private Context mContext;
	private int count;
	private int pagecount;
	private String mSBookPath;
	private LayoutInflater inflater;
	List<String> jpgFList;
	List<String> txtFList;
	List<String> audioFList;
	private int m_nStyle;  //0 ��ҳ,    1  ˫ҳ

	private String[] imgs;
//	private Integer[] imgs = { R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
//			  R.drawable.photo4, R.drawable.photo5, R.drawable.photo6};
//
	 String FindFileNameByIndex(List<String> list, int index)
	 {
		 String sFilename = "";
		 String sIndex = String.format("%03d",index);
		 if(list.isEmpty())
			 return sFilename;
		 for(int i = 0; i < list.size(); i++)
		 {
			if(list.get(i).contains(sIndex))
			{
				sFilename = list.get(i);
				break;
			}
		 }
		 return sFilename;
	 }
	 
	 
	 public int GetJpgFileName(List<String> list, String sType ) {
	        int count_ = 0;
	        String filename_  = mSBookPath + sType;

	        File file = new File(filename_);
		 Log.i("tag",filename_);
		 if(!file.exists()){
			 return 0;
		 }
		 File[] subFile = file.listFiles();
	        if(subFile.length > 0)
	        {
		        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
		            // �ж��Ƿ�Ϊ�ļ���
		            if (!subFile[iFileLength].isDirectory()) {
		                String filename = subFile[iFileLength].getName();
		                // �ж��Ƿ�ΪMP4��β
		                String sEnd = "*.*";
		                if(sType.equals("jpg"))
		                	sEnd = "." + sType;
		                else if(sType.equals("text"))
		                	sEnd = "." + "png";
		                else if(sType.equals("audio"))
		                	sEnd = "." + "ogg";
		                if (filename.trim().toLowerCase().endsWith(sEnd)) {
		                	String s1 = subFile[iFileLength].getPath();
		                	list.add(s1);
		                   count_++;
		                   
		                }
		            }
		        }
	        }
	        return count_;
	    }
	
	public PageWidgetAdapter(Context context, String s, int nStyle) {		
		mSBookPath = s;
		m_nStyle = nStyle;
		jpgFList = new ArrayList<String>();
		txtFList = new ArrayList<String>();
		audioFList = new ArrayList<String>();
		int count_ = GetJpgFileName(jpgFList, "jpg");
		GetJpgFileName(txtFList, "text");
		GetJpgFileName(audioFList, "audio");
		pagecount = count_;
		mContext = context;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(m_nStyle == 1)
			count = (int) Math.ceil((count_-3)/2.0) + 3;
		else
			count = count_;
	}

	public int getStyle() {
		// TODO Auto-generated method stub
		return m_nStyle;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imgs[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	//�ú�������ʾҳ��ͼ��png����ͼ �Լ�ҳ��
	@SuppressLint("NewApi")
	private void setuniImage(int pos, ImageView img, int nleft) throws FileNotFoundException
	{
		int width = 960;
		int hight = 1080;
		Bitmap bp = null;
		{
			if(pos != 0)
			{
			//	String sFileName = String.format("%s%04d.jpg", mSBookPath, pos);
				String sFileName = FindFileNameByIndex(jpgFList, pos);
				FileInputStream fis = new FileInputStream(sFileName);
				bp = BitmapFactory.decodeStream(fis);
		//		Bitmap bp = BitmapFactory.decodeResource(mContext.getResources(),imgs[pos]);
				width = bp.getWidth();
				hight = bp.getHeight();
			}
			
			Bitmap icon = Bitmap.createBitmap(width, hight, Bitmap.Config.ARGB_8888); //����һ���յ�ͼ����
			Canvas canvas = new Canvas(icon);//��ʼ���������Ƶ�ͼ��icon��
			Paint photoPaint = new Paint(); //��������
			photoPaint.setColor(Color.WHITE);
			Rect dst = new Rect(0, 0, width-1, hight-1);//����һ��ָ�����¾��ε����
			canvas.drawRect(dst,photoPaint);
			if(pos != 0)
			{
				Rect src;
				if(m_nStyle == 1)   //˫ҳ��ʾ
				{
					if(nleft == 0)
						src = new Rect(0, 0, width-16, hight-1);
					else
						src = new Rect(15, 0, width-1, hight-1);
				}
				else		//��ҳ��ʾ
				{
					src = new Rect(0, 0, width-1, hight-1);
				}
					
				canvas.drawBitmap(bp, src, dst, photoPaint);//��photo ���Ż������� dstʹ�õ������photoPaint
				
				//����� png����ͼ�ڸõط�����
				//canvas.drawBitmap
				{
					String sFileName = FindFileNameByIndex(txtFList, pos);
					if(!sFileName.isEmpty())
					{
						FileInputStream fis_ = new FileInputStream(sFileName);
						bp = BitmapFactory.decodeStream(fis_);
						canvas.drawBitmap(bp, src, dst, null);//��photo ���Ż������� dstʹ�õ������photoPaint
					}
				}
			}
				
			//
			
			Paint mPaint = new Paint();
			//mPaint.setAlpha(0x77); 
	        mPaint.setTextSize(32); 
	        mPaint.setColor(Color.BLUE);
            if(pos > 3)
	        {
		        String text = String.valueOf(pos-3); 
		        Rect mBounds = new Rect();
		        mPaint.getTextBounds(text, 0, text.length(), mBounds);  
		        float textWidth = mBounds.width();  
		        float textHeight = mBounds.height();  
		        canvas.drawText(text, width / 2 - textWidth / 2, hight -  
		                + textHeight - 25, mPaint);
	        }
	        
			canvas.save(Canvas.ALL_SAVE_FLAG);
			img.setImageBitmap(icon); 
		}
	}
	
	private void setViewContent(ViewGroup layout, int position) throws FileNotFoundException {
//		TextView text = (TextView) layout.findViewById(R.id.item_layout_leftText);
//		text.setText(String.valueOf(position*2+1));
		position += 1;
		if(m_nStyle == 1)
		{
			if(position < 4)
			{
				ImageView image = (ImageView) layout.findViewById(R.id.item_layout_rightImage);
				setuniImage(position, image, 1);
				
				image = (ImageView) layout.findViewById(R.id.item_layout_leftImage);
				setuniImage(0, image, 0);
			}
			else
			{
				ImageView image = (ImageView) layout.findViewById(R.id.item_layout_leftImage);
				setuniImage((position-4)*2+4, image, 0);
					
				if(((position-4)*2+5) <= pagecount)
				{
					image = (ImageView) layout.findViewById(R.id.item_layout_rightImage);
					setuniImage((position-4)*2+5, image, 1);
				}
				else
				{
					image = (ImageView) layout.findViewById(R.id.item_layout_rightImage);
					setuniImage(0, image, 1);
				}
			}	
		}
		else
		{
			ImageView image = (ImageView) layout.findViewById(R.id.item_layout_singleImage);
			setuniImage(position, image, 1);
		}
	}
	/*
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewGroup layout;
		//CounterView layout;
		if(convertView == null) {
			if(m_nStyle == 1)
				layout = (ViewGroup) inflater.inflate(R.layout.item_layout, null);
			else
				layout = (ViewGroup) inflater.inflate(R.layout.item_singlelayout, null);
		} else {
			layout = (ViewGroup) ((View)convertView);
		}
		try {
			setViewContent(layout, position);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (View)layout;
	}

}
