package com.example.myphone;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrientActivity extends Activity
{
  private TextView mTextView01;
  private Button mButton01;
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_orient);
    
    final Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
    
    mButton01 = (Button)findViewById(R.id.myButton1); 
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    if(getRequestedOrientation()==-1)
    {
      mTextView01.setText("err_1001");
    }
    
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        if(getRequestedOrientation()==-1)
        {
          mTextView01.setText("err_1001");
        }
        else
        {
          if(getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
          {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
          }
          else if(getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
          {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
          }
        }
        
        int h= defaultDisplay.getHeight();
        int w = defaultDisplay.getWidth();
        
        mTextView01.setText(Integer.toString(h)+"x"+Integer.toString(w));
      }      
    });
  }

  @Override
  public void setRequestedOrientation(int requestedOrientation)
  {
    switch(requestedOrientation)
    {
      case (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE):
        mMakeTextToast
        (
          getResources().getText(R.string.str_msg1).toString(),
          false
        );
        break;
      case (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT):
        mMakeTextToast
        (
          getResources().getText(R.string.str_msg2).toString(),
          false
        );
        break;
    }
    super.setRequestedOrientation(requestedOrientation);
  }

  @Override
  public int getRequestedOrientation()
  {
    return super.getRequestedOrientation();
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
  }
}
