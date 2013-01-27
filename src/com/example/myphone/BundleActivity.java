package com.example.myphone;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BundleActivity extends Activity {
	private String weight = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("TAG", "bundle Activity onStart()");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bundle);

		//用bundle传数据
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String name = bundle.getString("name");
		String sex  = bundle.getString("sex"); 
		double tall = bundle.getDouble("tall");
		TextView tv = (TextView)findViewById(R.id.tv_hello);
		weight = standardWeight(sex, tall);
		tv.setText("standard weight for " + name + " is " + weight);
		
		//用intent传数据
		//Intent intent = getIntent();
		//String message = intent.getStringExtra(MainActivity.EXTRA_STRING);
		//TextView tv = (TextView)findViewById(R.id.tv_hello);
		//tv.setText(message);

		Button btn_res = (Button)findViewById(R.id.button1);
		btn_res.setOnClickListener(new OnClickListener(){	
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
				intent.putExtra("weight", weight);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
	
	private String format(double val) {
		NumberFormat fmt = new DecimalFormat("0.00");
		return fmt.format(val);
	}
	private String standardWeight(String sex, double tall) {
		String weight = "";
		if(sex.equals("M"))
			weight = format((tall-0.8)*70);
		else
			weight = format((tall-0.7)*60);
		
		return weight;
	}
}
