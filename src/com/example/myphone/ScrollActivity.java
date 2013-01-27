package com.example.myphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScrollActivity extends Activity {
	private ListView mListView1;
	private ListView mListView2;
	private static final String[] array1 =
		  { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };

	private static final String[] array2 =
		  { "one", "two", "three", "four", "five", "six", "sever" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll);
		
		mListView1 = (ListView)findViewById(R.id.listView1);
		mListView2 = (ListView)findViewById(R.id.listView2);
		
	    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
		        R.layout.simple_checkedtextivew, array1);
		mListView1.setAdapter(adapter1);

		mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		      @Override
		      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		    	  mListView1.setVisibility(View.GONE);
		      }
		});
		
	    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
		        R.layout.simple_checkedtextivew, array2);
		mListView2.setAdapter(adapter2);

		mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		      @Override
		      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		        mListView1.setVisibility(View.VISIBLE);
		      }
		});		
		
	}
}
