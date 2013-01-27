package com.example.myphone;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressActivity extends Activity {
	private TextView mTextView01;
	private Button mButton01;
	private ProgressBar mProgressBar01;
	public int intCounter=0;
	  
	protected static final int GUI_STOP_NOTIFIER = 0x108;
	protected static final int GUI_THREADING_NOTIFIER = 0x109;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		
	    mButton01 = (Button)findViewById(R.id.myButton1); 
	    mTextView01 = (TextView)findViewById(R.id.myTextView1);
	    
	    mProgressBar01 = (ProgressBar)findViewById(R.id.myProgressBar1);
	    mProgressBar01.setIndeterminate(false);
	    mButton01.setOnClickListener(new Button.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        mTextView01.setText("start...");
	        mProgressBar01.setVisibility(View.VISIBLE);
	        mProgressBar01.setMax(100);
	        mProgressBar01.setProgress(0);
	        new Thread(new Runnable() {
	          public void run() {
	            for (int i=0;i<10;i++) {
	              try {
	                intCounter = (i+1)*20;
	                Thread.sleep(1000);
	                if(i==4) {
	                  Message m = new Message();
	                  m.what = GUI_STOP_NOTIFIER;
	                  myMessageHandler.sendMessage(m);
	                  break;
	                }
	                else {
	                  Message m = new Message();
	                  m.what = GUI_THREADING_NOTIFIER;
	                  myMessageHandler.sendMessage(m); 
	                }
	              } catch(Exception e) {
	                e.printStackTrace();
	              }
	            }
	          }
	        }).start();
	      }
	    });
	  }
	  
	  Handler myMessageHandler = new Handler() {
		public void handleMessage(Message msg) { 
	      switch (msg.what) { 
	        case GUI_STOP_NOTIFIER:
	          mTextView01.setText("done!");
	          mProgressBar01.setVisibility(View.GONE);
	          Thread.currentThread().interrupt();
	          break;
	        case GUI_THREADING_NOTIFIER:
	          if(!Thread.currentThread().isInterrupted()) {
	            mProgressBar01.setProgress(intCounter);
	            mTextView01.setText(
	              "start.. ("+Integer.toString(intCounter)+"%)\n"+"Progress:"+Integer.toString(mProgressBar01.getProgress())+"\n"+"Indeterminate:"+Boolean.toString(mProgressBar01.isIndeterminate())
	            );
	          }
	          break;
	      } 
	      super.handleMessage(msg); 
	    }
	  };
}
