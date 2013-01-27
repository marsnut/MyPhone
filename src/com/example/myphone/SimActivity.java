package com.example.myphone;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.os.Bundle; 
import android.telephony.TelephonyManager;
import android.util.Log;

public class SimActivity extends ListActivity {
	  private TelephonyManager telMgr;
	  private List<String> item=new ArrayList<String>();
	  private List<String> value=new ArrayList<String>();

	  @Override 
	  public void onCreate(Bundle savedInstanceState) 
	  { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_sim); 
		telMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE); 

		Log.v("TAG", "step1");
		
		item.add(getResources().getText(R.string.str_sim_list0).toString());
		if(telMgr.getSimState()==telMgr.SIM_STATE_READY)
		{
		  value.add("ready");
		}
		else if(telMgr.getSimState()==telMgr.SIM_STATE_ABSENT)
		{
		  value.add("absent SIM");
		}
		else
		{
		  value.add("SIM ???");
		}

		Log.v("TAG", "step2");
		/*
		item.add(getResources().getText(R.string.str_sim_list1).toString());
		if(telMgr.getSimSerialNumber()!=null)
		{
		  value.add(telMgr.getSimSerialNumber());
		}
		else
		{
		  value.add("non-sn");
		}
		
		Log.v("TAG", "step3");
		
		item.add(getResources().getText(R.string.str_sim_list2).toString());
		if(telMgr.getSimOperator().equals(""))
		{
		  value.add("non-operator");
		}
		else
		{
		  value.add(telMgr.getSimOperator());
		}
		
		Log.v("TAG", "step4");
		
		item.add(getResources().getText(R.string.str_sim_list3).toString());
		if(telMgr.getSimOperatorName().equals(""))
		{
		  value.add("non-operatorname");
		}
		else
		{
		  value.add(telMgr.getSimOperatorName());
		}
		
		Log.v("TAG", "step5");
		
		item.add(getResources().getText(R.string.str_sim_list4).toString());
		if(telMgr.getSimCountryIso().equals(""))
		{
		  value.add("non-countryIso");
		}
		else
		{
		  value.add(telMgr.getSimCountryIso());
		}
		*/
		
		setListAdapter(new SimAdapter(this,item,value));
	  } 
}
