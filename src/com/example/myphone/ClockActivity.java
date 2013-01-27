package com.example.myphone;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.AnalogClock;
import android.widget.TextView;

public class ClockActivity extends Activity {
	protected static final int GUINOTIFIER = 0x1234;
	private TextView mTextView;
	public AnalogClock mAnalogClock;
	public Calendar mCalendar;
	public int mMinutes;
	public int mHour;
	public Handler mHandler;
	private Thread mClockThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock);
		
	    mTextView=(TextView)findViewById(R.id.myTextView);
	    mAnalogClock=(AnalogClock)findViewById(R.id.myAnalogClock);
	    
	    mHandler = new Handler()
	    {
	      public void handleMessage(Message msg) 
	      {
	      	 switch (msg.what)
	      	  { 
	      	    case GUINOTIFIER:
	      	  	  mTextView.setText(mHour+" : "+mMinutes);
	      	  	  break; 
	      	  } 
	      	  super.handleMessage(msg); 
	      	  }
	    };
	    
	    mClockThread=new LooperThread();
	    mClockThread.start();
	}
  	class LooperThread extends Thread {
      public void run() {
      	super.run();
        try {
        	do {
        		long time = System.currentTimeMillis();
    	  	  	final Calendar mCalendar = Calendar.getInstance();
    	  	  	mCalendar.setTimeInMillis(time);
    	  	  	mHour = mCalendar.get(Calendar.HOUR);
    	  	  	mMinutes = mCalendar.get(Calendar.MINUTE);
    	  	  	Thread.sleep(1000); 
    	  	  	Message m = new Message();
    	  	  	m.what = GUINOTIFIER;
    	  	  	mHandler.sendMessage(m);       
        	} while(LooperThread.interrupted()==false);
        }
        catch(Exception e) {
         e.printStackTrace();
        }
      }      
   }
}
