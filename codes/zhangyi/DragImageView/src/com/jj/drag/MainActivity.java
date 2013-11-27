package com.jj.drag;

import java.io.InputStream;

import com.wifi.location.LocData;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	private int window_width, window_height;// �ؼ����
	private DragImageView dragImageView;// �Զ���ؼ�
	private int state_height;// ״̬���ĸ߶�
	//���ڽ�������ľ�γ��
	float lonD,latD;
	
	LocData ld;
	
	//���տؼ�
	EditText lon;
	EditText lat;
	
	ImageButton imageButton1;

	private ViewTreeObserver viewTreeObserver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		ld=new LocData(this);
		 lon = (EditText)findViewById(R.id.lon);
		 lat = (EditText)findViewById(R.id.lat);
		 imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
		imageButton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/** ��ȡ��Ҋ����߶� **/
				WindowManager manager = getWindowManager();
				window_width = manager.getDefaultDisplay().getWidth();
				window_height = manager.getDefaultDisplay().getHeight();

				dragImageView = (DragImageView) findViewById(R.id.div_main);
				//��ȡ����ľ�γ��
				lonD=Float.valueOf(lon.getText().toString());
				latD=Float.valueOf(lat.getText().toString());
				Bitmap bmp;
				if(lonD>0 && latD>0)
				{
					Bitmap tem=ld.DrawMap(lonD,latD);
					bmp = BitmapUtil.ReadBitmap(getApplicationContext(),tem,window_width, window_height);
				}
				else
				{
					bmp = BitmapUtil.ReadBitmapById(getApplicationContext(), R.drawable.leaf,
							window_width, window_height);
				}
				
				// ����ͼƬ
				dragImageView.setImageBitmap(bmp);
				dragImageView.setmActivity(MainActivity.this);//ע��Activity.
				/** ����״̬���߶� **/
				viewTreeObserver = dragImageView.getViewTreeObserver();
				viewTreeObserver
						.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

							@Override
							public void onGlobalLayout() {
								if (state_height == 0) {
									// ��ȡ״�����߶�
									Rect frame = new Rect();
									getWindow().getDecorView()
											.getWindowVisibleDisplayFrame(frame);
									state_height = frame.top;
									dragImageView.setScreen_H(window_height-state_height);
									dragImageView.setScreen_W(window_width);
								}

							}
						});
			}
		});

	}

	/**
	 * ��ȡ������Դ��ͼƬ
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap ReadBitmapById(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// ��ȡ��ԴͼƬ
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}

}