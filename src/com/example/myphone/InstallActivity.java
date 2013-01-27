package com.example.myphone;

import android.app.Activity; 
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle; 
import android.util.Log; 
import android.view.View; 
import android.webkit.URLUtil; 
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File; 
import java.io.FileOutputStream; 
import java.io.InputStream; 
import java.net.URL; 
import java.net.URLConnection; 

public class InstallActivity extends Activity 
{ 
  private TextView mTextView01;
  private EditText mEditText01;
  private Button mButton01;
  private static final String TAG = "DOWNLOADAPK"; 
  private String currentFilePath = ""; 
  private String currentTempFilePath = ""; 
  private String strURL="";
  private String fileEx="";
  private String fileNa="";
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.activity_install); 
     
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mButton01 = (Button)findViewById(R.id.myButton1);
    mEditText01 =(EditText)findViewById(R.id.myEditText1);
 
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v) 
      {
        mTextView01.setText("正在安装...");
        strURL = mEditText01.getText().toString(); 
        fileEx = strURL.substring(strURL.lastIndexOf(".")+1,strURL.length()).toLowerCase();
        fileNa = strURL.substring(strURL.lastIndexOf("/")+1,strURL.lastIndexOf("."));
        getFile(strURL);
       }
     }
    );
    
    mEditText01.setOnClickListener(new EditText.OnClickListener()
    {
      public void onClick(View arg0)
      {
        mEditText01.setText("");
        mTextView01.setText("环狠杆祘Α(叫块URL)");
      }
      
    });
  }

  private void getFile(final String strPath) 
  { 
    try 
    { 
      if (strPath.equals(currentFilePath) ) 
      { 
       getDataSource(strPath);  
      }        
      currentFilePath = strPath;      
      Runnable r = new Runnable() 
      {   
        public void run() 
        {   
          try 
          { 
             getDataSource(strPath); 
          } 
          catch (Exception e) 
          { 
            Log.e(TAG, e.getMessage(), e); 
          } 
        } 
      };   
      new Thread(r).start(); 
    } 
    catch(Exception e) 
    { 
      e.printStackTrace(); 
    }
  } 
  
  private void getDataSource(String strPath) throws Exception 
  { 
    if (!URLUtil.isNetworkUrl(strPath)) 
    { 
      mTextView01.setText("不正确的URL"); 
    } 
    else 
    { 
        URL myURL = new URL(strPath); 
        URLConnection conn = myURL.openConnection();   
        conn.connect();   
        InputStream is = conn.getInputStream();   
        if (is == null) 
        { 
          throw new RuntimeException("stream is null"); 
        } 
        File myTempFile = File.createTempFile(fileNa, "."+fileEx); 
        currentTempFilePath = myTempFile.getAbsolutePath(); 
        FileOutputStream fos = new FileOutputStream(myTempFile); 
        byte buf[] = new byte[128];   
        do 
        {   
          int numread = is.read(buf);   
          if (numread <= 0) 
          { 
            break; 
          } 
          fos.write(buf, 0, numread);   
        }while (true);  
        
        openFile(myTempFile);
        try 
        { 
          is.close(); 
        } 
        catch (Exception ex) 
        { 
          Log.e(TAG, "error: " + ex.getMessage(), ex); 
        } 
      }
    }  
   
  private void openFile(File f) 
  {
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setAction(android.content.Intent.ACTION_VIEW);
    
    String type = getMIMEType(f);
    intent.setDataAndType(Uri.fromFile(f),type);
    startActivity(intent); 
  }

  private String getMIMEType(File f) 
  { 
    String type="";
    String fName=f.getName();
    String end=fName.substring(fName.lastIndexOf(".")+1,fName.length()).toLowerCase(); 
    
    if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")||end.equals("xmf")||end.equals("ogg")||end.equals("wav"))
    {
      type = "audio"; 
    }
    else if(end.equals("3gp")||end.equals("mp4"))
    {
      type = "video";
    }
    else if(end.equals("jpg")||end.equals("gif")||end.equals("png")||end.equals("jpeg")||end.equals("bmp"))
    {
      type = "image";
    }
    else if(end.equals("apk")) 
    { 
      /* android.permission.INSTALL_PACKAGES */ 
      type = "application/vnd.android.package-archive"; 
    } 
    else
    {
      type="*";
    }
    if(end.equals("apk")) 
    { 
    } 
    else 
    { 
      type += "/*";  
    } 
    return type;  
  } 

  private void delFile(String strFileName) 
  { 
    File myFile = new File(strFileName); 
    if(myFile.exists()) 
    { 
      myFile.delete(); 
    } 
  } 
  
  @Override 
  protected void onPause()
  {
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mTextView01.setText("暂停");
    super.onPause();
  }

  @Override 
  protected void onResume() 
  { 
    delFile(currentTempFilePath); 
    super.onResume(); 
  }
} 
