package com.example.myphone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishActivity extends Activity {
	private Button mButton1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		
	    mButton1 = (Button) findViewById(R.id.myButton1);
	    mButton1.setOnClickListener(new Button.OnClickListener()
	    {
		    @Override
		    public void onClick(View v)
		    {
		      new AlertDialog.Builder(FinishActivity.this)
		      .setTitle(R.string.app_about)
		      .setIcon(R.drawable.hot) 
		      .setMessage(R.string.app_about_msg)
		      .setPositiveButton(R.string.str_ok,
		    		new DialogInterface.OnClickListener() {
		    	  		public void onClick(DialogInterface dialoginterface, int i) {           
		    	  			finish();
		    	  		}
		      	}).setNegativeButton(R.string.str_no,
		      			new DialogInterface.OnClickListener() {
		      				public void onClick(DialogInterface dialoginterface, int i) {
		      			}
		      	}).show();
		    }
	    });
	}
}
