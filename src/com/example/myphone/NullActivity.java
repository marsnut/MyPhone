package com.example.myphone;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NullActivity extends Activity {
	private LinearLayout myLayout;
	private LinearLayout.LayoutParams layoutP;
	private int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private TextView black_TV, blue_TV, cyan_TV, dkgray_TV;
	private TextView gray_TV, green_TV,ltgray_TV, magenta_TV, red_TV;
	private TextView transparent_TV, white_TV, yellow_TV;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		Log.v("TAG", "null Activity onStart()");
		
		super.onCreate(savedInstanceState);
		
		myLayout = new LinearLayout(this);
		myLayout.setOrientation(LinearLayout.VERTICAL);
		myLayout.setBackgroundResource(R.drawable.icon);
		setContentView(myLayout);
		layoutP = new LinearLayout.LayoutParams(WC, WC);
		
		constructTextView();
		addTextView();
		setTextViewColor();
		setTextViewText();			
	}

	public void constructTextView() {
		black_TV = new TextView(this);
		blue_TV = new TextView(this);
		cyan_TV = new TextView(this);
		dkgray_TV = new TextView(this);
		gray_TV = new TextView(this);
		green_TV = new TextView(this);
		ltgray_TV = new TextView(this);
		magenta_TV = new TextView(this);
		red_TV = new TextView(this);
		transparent_TV = new TextView(this);
		white_TV = new TextView(this);
		yellow_TV = new TextView(this);
	}
	public void addTextView() {
		myLayout.addView(black_TV, layoutP);
		myLayout.addView(blue_TV, layoutP);
		myLayout.addView(cyan_TV, layoutP);
		myLayout.addView(dkgray_TV, layoutP);
		myLayout.addView(gray_TV, layoutP);
		myLayout.addView(green_TV, layoutP);
		myLayout.addView(ltgray_TV, layoutP);
		myLayout.addView(magenta_TV, layoutP);
		myLayout.addView(red_TV, layoutP);
		myLayout.addView(transparent_TV, layoutP);
		myLayout.addView(white_TV, layoutP);
		myLayout.addView(yellow_TV, layoutP);
	}	
	public void setTextViewText() {
		black_TV.setText("��ɫ");
		blue_TV.setText("��ɫ");
		cyan_TV.setText("����ɫ");
		dkgray_TV.setText("�Һ�ɫ");
		gray_TV.setText("��ɫ");
		green_TV.setText("��ɫ");
		ltgray_TV.setText("ǳ��ɫ");
		magenta_TV.setText("����ɫ");
		red_TV.setText("��ɫ");
		transparent_TV.setText("͸��");
		white_TV.setText("��ɫ");
		yellow_TV.setText("��ɫ");
	}
	public void setTextViewColor() {
		black_TV.setTextColor(Color.BLACK);
		blue_TV.setTextColor(Color.BLUE);
		dkgray_TV.setTextColor(Color.DKGRAY);
		gray_TV.setTextColor(Color.GRAY);
		green_TV.setTextColor(Color.GREEN);
		ltgray_TV.setTextColor(Color.LTGRAY);
		magenta_TV.setTextColor(Color.MAGENTA);
		red_TV.setTextColor(Color.RED);
		transparent_TV.setTextColor(Color.TRANSPARENT);
		white_TV.setTextColor(Color.WHITE);
		yellow_TV.setTextColor(Color.YELLOW);
	}
}
