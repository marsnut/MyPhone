package com.example.myphone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WaitingActivity extends Activity {

	private TextView mText;
	private Button mButton;
	private ProgressDialog myDialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiting);

		TextView mText = (TextView)findViewById(R.id.textView1);
		Button mButton = (Button)findViewById(R.id.button1);
		
		Log.v("TAG", "waiting Activity doCreate()");
		
		mButton.setOnClickListener(myShowAlertDialog);
		//mButton.setOnClickListener(myShowProgressBar);
	}
	
	Button.OnClickListener myShowProgressBar = new Button.OnClickListener() {
		@Override
	    public void onClick(View v)
	    {
	      final CharSequence strTitle = getString(R.string.str_title);
	      final CharSequence strBody = getString(R.string.str_body);
	
	      myDialog = ProgressDialog.show(WaitingActivity.this, strTitle, strBody, true);
	      
	      mText.setText(strBody);
	      
	      new Thread() { 
	          public void run() { 
	              try { 
	                  sleep(3000);
	              } catch (Exception e) {
	                  e.printStackTrace();
	              } finally {
	                  myDialog.dismiss();
	              }
	          }
	      }.start();
	    }		
	};
	
	Button.OnClickListener myShowAlertDialog = new Button.OnClickListener()
	{
	    public void onClick(View arg0)
	    {
	      new AlertDialog.Builder(WaitingActivity.this)
	        .setTitle(R.string.str_title)
	        .setItems(R.array.items_irdc_dialog,
	        new DialogInterface.OnClickListener()
	        {
	          public void onClick(DialogInterface dialog, int whichcountry)
	          {
	            CharSequence strDialogBody = getString(R.string.str_body);
	            String[] aryShop = 
	            		getResources().getStringArray(R.array.items_irdc_dialog);                                           
	            new AlertDialog.Builder(WaitingActivity.this)                        
	              .setMessage(strDialogBody + aryShop[whichcountry])                        
	              .setNeutralButton(R.string.str_ok, new DialogInterface.OnClickListener()
	              {
	                public void onClick(DialogInterface dialog, int whichButton)
	                {
	                }
	            }).show();
	          }
	        }).setNegativeButton("È·¶¨", new DialogInterface.OnClickListener()
	        { 
	          @Override 
	          public void onClick(DialogInterface d, int which)
	          { 
	            d.dismiss(); 
	          } 
		    }).show();
	    }
	};
}
