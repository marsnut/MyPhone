package com.example.myphone;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DropdownActivity extends Activity {
	
	private static final String[] countriesStr = {"中国","美帝","东瀛","棒子"};
	private TextView myTextView;
	private EditText myEditText;
	private Button myButton_add;
	private Button myButton_remove;
	private Spinner mySpinner;
	private ArrayAdapter<String> adapter;
	private List<String> allCountries;
	//Animation myAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dropdown);
		
	    myTextView = (TextView) findViewById(R.id.myTextView);
	    myEditText = (EditText) findViewById(R.id.myEditText);
	    myButton_add = (Button) findViewById(R.id.myButton_add);
	    myButton_remove = (Button) findViewById(R.id.myButton_remove);    
	    mySpinner = (Spinner) findViewById(R.id.mySpinner);

	    //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countriesStr);
	    //adapter.setDropDownViewResource(R.layout.myspinner_dropdown);

	    allCountries = new ArrayList<String>();
	    for (int i = 0; i < countriesStr.length; i++)
	    {
	    	allCountries.add(countriesStr[i]);
	    }
	    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCountries);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    
	    mySpinner.setAdapter(adapter);

	    myButton_add.setOnClickListener(new Button.OnClickListener() {
	      @Override
	      public void onClick(View arg0) {
	  		//Log.v("TAG", "dropdown Activity onClick()...");
	        String newCountry = myEditText.getText().toString();
	        for (int i = 0; i < adapter.getCount(); i++) {
	          if (newCountry.equals(adapter.getItem(i))) {
	            return;
	          }
	        }
	        
	        if (!newCountry.equals("")) {
	          adapter.add(newCountry);
	          int position = adapter.getPosition(newCountry);
	          mySpinner.setSelection(position);
	          myEditText.setText("");
	        }
	      }
	    });

	    myButton_remove.setOnClickListener(new Button.OnClickListener() {
	      @Override
	      public void onClick(View arg0) {
	        if (mySpinner.getSelectedItem() != null) {
	          adapter.remove(mySpinner.getSelectedItem().toString());
	          myEditText.setText("");
	          if (adapter.getCount() == 0) {
	            myTextView.setText("");
	          }
	        }
	      }
	    });
	    
	    mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
	      @Override
	      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        myTextView.setText(arg0.getSelectedItem().toString());
	      }

	      @Override
	      public void onNothingSelected(AdapterView<?> arg0) {
	      }
	    });

	    /*
	    mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
	      @Override
	      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        myTextView.setText("国名:" + countriesStr[arg2]);
	        arg0.setVisibility(View.VISIBLE);
	      }

	      @Override
	      public void onNothingSelected(AdapterView<?> arg0) {
	      }
	    });
	    myAnimation = AnimationUtils.loadAnimation(this, R.animator.my_anim);
	    
	    mySpinner.setOnTouchListener(new Spinner.OnTouchListener()
	    {
	      @Override
	      public boolean onTouch(View v, MotionEvent event)
	      {
	        v.startAnimation(myAnimation);
	        v.setVisibility(View.INVISIBLE);
	        return false;
	      }
	    });

	    mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener()
	    {
	      @Override
	      public void onFocusChange(View v, boolean hasFocus) {
	      }
	    });
	    */	    
	}
}
