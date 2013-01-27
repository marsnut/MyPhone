package com.example.myphone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ListviewActivity extends Activity {
	private static final String[] array =
		  { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
	LinearLayout myLinearLayout;
	TextView myTextView;
	ListView myListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
	    myLinearLayout = new LinearLayout(this);
	    myLinearLayout.setOrientation(LinearLayout.VERTICAL);
	    myLinearLayout.setBackgroundColor(android.graphics.Color.WHITE);

	    myTextView = new TextView(this);
	    LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(
	        LinearLayout.LayoutParams.FILL_PARENT,
	        LinearLayout.LayoutParams.WRAP_CONTENT);
	    myTextView.setText(R.string.str_title);
	    myTextView.setTextColor(getResources().getColor(R.color.blue));
	    myLinearLayout.addView(myTextView, param1);

	    myListView = new ListView(this);
	    LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
	        LinearLayout.LayoutParams.FILL_PARENT,
	        LinearLayout.LayoutParams.WRAP_CONTENT);
	    myListView.setBackgroundColor(getResources().getColor(R.color.graywhite));
	    myLinearLayout.addView(myListView, param2);

	    setContentView(myLinearLayout);

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        R.layout.simple_checkedtextivew, array);
	    myListView.setAdapter(adapter);

	    myListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	          @Override
	          public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	            myTextView.setText("week; " + arg0.getSelectedItem().toString());
	          }

	          @Override
	          public void onNothingSelected(AdapterView<?> arg0) {
	          }

	    });

	    myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	      @Override
	      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        myTextView.setText("week: " + array[arg2]);
	      }
	    });
	}
}
