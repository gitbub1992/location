package com.wifi.location;

import com.jj.drag.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class LocData extends View{
	




	// ��ʼ��ͼƬ��Դ
	private Bitmap bitmap1,bitmap2;
	// λͼ��͸�
	private int width1, height1,width2,height2;
	// Matrix ʵ��
	private Matrix matrix = new Matrix();
	// ������б��
	private float sx = 0.0f;
	// ���ű���
	private float scale = 1.0f;
	// �ж����Ż�����ת
	private boolean isScale = true;
	
	
	//����
	public float lon=0;
	//ά��
	public float lat=0;
	//��¼λͼ
	public Bitmap canvasBitmap = Bitmap.createBitmap(1000, 1000,Config.ARGB_8888);
	
	private Canvas canvas = new Canvas(canvasBitmap);
	
	public LocData(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.leaf).copy(Bitmap.Config.ARGB_8888,true);
		this.setFocusable(true);
		this.canvas.drawBitmap(bitmap1,0,0,null);
	}
	
	public Bitmap DrawMap(float lon, float lat)
	{
		// ���λͼ1,2
		bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.leaf).copy(Bitmap.Config.ARGB_8888,true);
		bitmap2 = ((BitmapDrawable)getResources().getDrawable(R.drawable.icon_geo)).getBitmap();
		// ���λͼ��1,2
		width1 = bitmap1.getWidth(); 
		width2 = bitmap2.getWidth();
		// ���λͼ��1,2
		height1 = bitmap1.getHeight();
		height2 = bitmap2.getHeight();
		// ʹ��ǰ��ͼ��ý���
		this.setFocusable(true);
		this.canvas.drawBitmap(bitmap1,0,0,null);
		this.canvas.drawBitmap(bitmap2,lon,lat,null);
		return canvasBitmap;

	}

	

}
