package com.example.myphone;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class WebviewActivity extends Activity 
{
  
  private ImageButton mImageButton1;
  private Button mButton;
  private EditText mEditText1;
  private WebView mWebView1;  
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);
   
    mImageButton1 = (ImageButton)findViewById(R.id.myImageButton1);
    mButton = (Button)findViewById(R.id.button1);
    mEditText1 = (EditText)findViewById(R.id.myEditText1);
    mWebView1 = (WebView) findViewById(R.id.myWebView1);
      
    mImageButton1.setOnClickListener(new ImageButton.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
          mImageButton1.setImageResource(R.drawable.go_2);
          String strURI = (mEditText1.getText().toString()); 
          mWebView1.loadUrl(strURI);
          Toast.makeText( 
        		  WebviewActivity.this,"ÕýÔÚ¼ÓÔØ"+strURI,
                  Toast.LENGTH_LONG
                  ).show();          
      }      
    });
    mButton.setOnClickListener(new Button.OnClickListener() {
    	@Override
    	public void onClick(View v) {
    	    String strIFrame = 
    	    	      "<iframe name=\"Hippo_ImageNote_frame\"" +
    	    	      "width=\"320\" height=\"480\"" +
    	    	      "frameborder=\"0\" src=" +
    	    	      "\"http://www.dubblogs.cc:8751/Android/Test/API/TestAjaxForm/\""+
    	    	      "marginwidth=\"0\" marginheight=\"0\" vspace=\"0\" " +
    	    	      "hspace=\"0\" allowtransparency=\"true\"" +
    	    	      "scrolling=\"no\"></iframe>";

    	    mWebView1.loadData(
    	    	"<html><body>"+strIFrame+"</body></html>", "text/html", "utf-8");    	    
    	}
    	
    });
  }
}
