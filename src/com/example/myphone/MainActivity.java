package com.example.myphone;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static String EXTRA_STRING;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//变换主题
	    //setTheme(R.style.ThemeTranslucent2);
		
		setContentView(R.layout.activity_main);
		
		Button btn_bkground=(Button)findViewById(R.id.btn_bkground);
		btn_bkground.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	
			}
		});		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		//erturn true;
		
	    menu.add(0, 0, 0, "关于");
	    menu.add(0, 1, 1, "关闭");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch(item.getItemId()) {
		case 0:
			openOptionsDialog();
			break;
		case 1:
			finish();
			break;
		default:
			;
		}
		return true;
	}
	private void openOptionsDialog() {
		new AlertDialog.Builder(this)
		.setTitle("关于")
		.setMessage("this is a menu test")
		.setPositiveButton("OK",
		    new DialogInterface.OnClickListener()
		    {
		     public void onClick(DialogInterface dialoginterface, int i)
		     {
		     }
		}).show();
	}

	public void doBundle(View view)  {
		
		Intent intent = new Intent();
		//用bundle传数据
		Bundle bundle = new Bundle();
		bundle.putString("name", "肖可汗");
		bundle.putString("sex", "M");
		bundle.putDouble("tall", 1.78);
		intent.putExtras(bundle);
		intent.setClass(this, BundleActivity.class);
		
		//用intent传数据
		//intent.putExtra(EXTRA_STRING, "THIS IS A TEST!");
		//intent.setClass(MainActivity.this, BundleActivity.class);
		
		startActivityForResult(intent, 0);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(resultCode) {
		case RESULT_OK:
			String weight = data.getStringExtra("weight");
			TextView tv = (TextView)findViewById(R.id.btn_bkground);
			tv.setText(weight);
			break;
		default:
			;
		}
	}
	
	public void createWidget(View view) {
		Intent intent = new Intent();
		intent.setClass(this, NullActivity.class);
		
		//Log.v("TAG", "main Activity doBundle()");
		
		startActivity(intent);
	}
	public void doDialog(View view) {
        new AlertDialog.Builder(MainActivity.this)
        .setTitle("关于")
        .setMessage("copyleft 2012")
        .setPositiveButton("OK",
           new DialogInterface.OnClickListener() {
        		public void onClick(DialogInterface dialoginterface, int i)
        		{
        		}
           }
        ).show();		
	}
	public void setFont(View view) {
		Intent intent = getIntent();
		intent.setClass(this, FontActivity.class);
		startActivity(intent);
	}
	public void doGallery(View v) {
		Intent intent = getIntent();
		intent.setClass(this, GalleryActivity.class);
		startActivity(intent);
	}
	public void doWaiting(View v) {
		Intent intent = getIntent();
		intent.setClass(this, WaitingActivity.class);
		
		Log.v("TAG", "main Activity doWaiting()");
		
		startActivity(intent);
	}
	public void doListener(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ListenerActivity.class);
		startActivity(intent);
	}
	public void doImage(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ImageActivity.class);
		startActivity(intent);
	}
	public void doDropdown(View v) {
		Intent intent = getIntent();
		intent.setClass(this, DropdownActivity.class);
		startActivity(intent);
	}
	public void doFilelist(View v) {
		Intent intent = getIntent();
		intent.setClass(this, FilelistActivity.class);
		startActivity(intent);
	}
	public void doClock(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ClockActivity.class);
		startActivity(intent);
	}
	public void doDatetime(View v) {
		Intent intent = getIntent();
		intent.setClass(this, DatetimeActivity.class);
		startActivity(intent);
	}
	public void doImageview(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ImageviewActivity.class);
		startActivity(intent);
	}
	public void doProgress(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ProgressActivity.class);
		startActivity(intent);
	}
	public void doGridview(View v) {
		Intent intent = getIntent();
		intent.setClass(this, GridviewActivity.class);
		startActivity(intent);
	}
	public void doListview(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ListviewActivity.class);
		startActivity(intent);
	}
	public void doListactivity(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ListactivityActivity.class);
		startActivity(intent);
	}
	public void doListfile(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ListfileActivity.class);
		startActivity(intent);
	}
	public void doMatrix(View v) {
		Intent intent = getIntent();
		intent.setClass(this, MatrixActivity.class);
		startActivity(intent);
	}
	public void doFinish(View v) {
		Intent intent = getIntent();
		intent.setClass(this, FinishActivity.class);
		startActivity(intent);
	}
	public void doSim(View v) {
		Intent intent = getIntent();
		intent.setClass(this, SimActivity.class);
		startActivity(intent);
	}
	public void doStorage(View v) {
		Intent intent = getIntent();
		intent.setClass(this, StorageActivity.class);
		startActivity(intent);
	}
	public void doSdcard(View v) {
		Intent intent = getIntent();
		intent.setClass(this, SdcardActivity.class);
		startActivity(intent);
	}
	public void doHttp(View v) {
		Intent intent = getIntent();
		intent.setClass(this, HttpActivity.class);
		startActivity(intent);
	}
	public void doWebview(View v) {
		Intent intent = getIntent();
		intent.setClass(this, WebviewActivity.class);
		startActivity(intent);
	}
	public void doUri(View v) {
		Intent intent = getIntent();
		intent.setClass(this, UriActivity.class);
		startActivity(intent);
	}
	public void doRpc(View v) {
		Intent intent = getIntent();
		intent.setClass(this, RpcActivity.class);
		startActivity(intent);
	}
	public void doInstall(View v) {
		Intent intent = getIntent();
		intent.setClass(this, InstallActivity.class);
		startActivity(intent);
	}
	public void doScroll(View v) {
		Intent intent = getIntent();
		intent.setClass(this, ScrollActivity.class);
		startActivity(intent);
	}
}
