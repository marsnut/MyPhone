package com.example.myphone;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FontActivity extends Activity {
	
	private int[] colors;
	private float[] sizes;
	private int colornum = 0;
	private int sizenum = 0;
	private TextView mText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_font);
		
		mText = (TextView)findViewById(R.id.textView1);
		Button btn1 = (Button)findViewById(R.id.button1);
		Button btn2 = (Button)findViewById(R.id.button2);
		
		colors = new int[] {
			    Color.BLACK, Color.RED, Color.BLUE,
			    Color.GREEN, Color.MAGENTA, Color.YELLOW				
		};
		sizes = new float[] {
				6,8,10,12,14,16,18,20,24,36
		};
		
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mText.setTextColor(colors[colornum]);
				mText.setTextSize(sizes[sizenum]);
				
				//int i = Integer.parseInt(s);
				String text = "×ÖºÅ" + String.valueOf(sizes[sizenum]);
				mText.setText(text);
				colornum++;
				sizenum++;
				if(colornum >= colors.length)
					colornum = 0;
				if(sizenum >= sizes.length)
					sizenum = 0;
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        mText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/HandmadeTypewriter.ttf"));
		    }				
		});		
	}
}
