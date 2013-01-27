package com.example.myphone;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DatetimeActivity extends Activity {
	
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;

	private TextView tv;
	private TimePicker tp;
	private DatePicker dp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datetime);
		
	    Calendar c=Calendar.getInstance();
	    mYear=c.get(Calendar.YEAR);
	    mMonth=c.get(Calendar.MONTH);
	    mDay=c.get(Calendar.DAY_OF_MONTH);
	    mHour=c.get(Calendar.HOUR_OF_DAY);
	    mMinute=c.get(Calendar.MINUTE);
		
	    tv= (TextView) findViewById(R.id.showTime);
	    updateDisplay();
	    
	    dp=(DatePicker)findViewById(R.id.dPicker);
	    dp.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
		  @Override
		  public void onDateChanged(DatePicker view, int year, int monthOfYear,
		                            int dayOfMonth) {
		    mYear=year;
		    mMonth= monthOfYear;
		    mDay=dayOfMonth;
		    updateDisplay();
		  }
	    });
    
	    tp=(TimePicker)findViewById(R.id.tPicker);
	    tp.setIs24HourView(true);
	    
	    tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
	      @Override
		  public void onTimeChanged(TimePicker view,int hourOfDay,int minute)
	      {
	        mHour=hourOfDay;
	        mMinute=minute;
	        updateDisplay();
		  }
	    });
	}
	private void updateDisplay()
	{
	    tv.setText(
	      new StringBuilder().append(mYear).append("/")
	                         .append(format(mMonth + 1)).append("/")
	                         .append(format(mDay)).append("¡@")
	                         .append(format(mHour)).append(":")
	                         .append(format(mMinute))
	    );
	}
	private String format(int x)
	{
	    String s=""+x;
	    if(s.length()==1) s="0"+s;
	    return s;
	}
}
