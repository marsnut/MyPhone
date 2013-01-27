package com.example.myphone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse; 
import org.apache.http.NameValuePair; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.message.BasicNameValuePair; 
import org.apache.http.protocol.HTTP; 
import org.apache.http.util.EntityUtils; 

import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity; 
import android.os.Bundle; 
import android.util.Log;
import android.view.View; 
import android.widget.Button; 
import android.widget.TextView; 

public class HttpActivity extends Activity 
{ 
  private Button mButton1,mButton2; 
  private TextView mTextView1; 
   
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.activity_http); 
     
    mButton1 =(Button) findViewById(R.id.myButton1); 
    mButton2 =(Button) findViewById(R.id.myButton2);
    mTextView1 = (TextView) findViewById(R.id.myTextView1); 
     
    mButton1.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        //String uriAPI = "http://www.dubblogs.cc:8751/Android/Test/API/Post/index.php";
    	String uriAPI = "http://10.0.2.2/mobil_action.php"; 
        HttpPost httpRequest = new HttpPost(uriAPI); 
        List <NameValuePair> params = new ArrayList <NameValuePair>(); 
        params.add(new BasicNameValuePair("mod", "member")); 
        params.add(new BasicNameValuePair("act", "signin")); 
        params.add(new BasicNameValuePair("username", "admin")); 
        params.add(new BasicNameValuePair("password", "admin")); 
        params.add(new BasicNameValuePair("livetime", "1")); 
        params.add(new BasicNameValuePair("goto", "/mobil_member.php")); 
        try 
        { 
          httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
          DefaultHttpClient httpclient = new DefaultHttpClient();
          HttpResponse httpResponse = httpclient.execute(httpRequest); 
          if(httpResponse.getStatusLine().getStatusCode() == 200)  
          {
          	HttpEntity entity = httpResponse.getEntity();

            String strResult = EntityUtils.toString(entity); 
            mTextView1.setText(strResult);
            

            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
            if (entity != null) {
                entity.consumeContent();
            }
    
            if (cookies.isEmpty()) {
                Log.v("******", "NONE");
            } else {
                 for (int i = 0; i < cookies.size(); i++) {             
                   Log.v("***","- domain " + cookies.get(i).getDomain());
                   Log.v("***","- path " + cookies.get(i).getPath());
                   Log.v("***","- value " + cookies.get(i).getValue());
                   Log.v("***","- name " + cookies.get(i).getName());
                   Log.v("***","- port " + cookies.get(i).getPorts());
                   Log.v("***","- comment " + cookies.get(i).getComment());
                   Log.v("***","- commenturl" + cookies.get(i).getCommentURL());
                   Log.v("***","- all " + cookies.get(i).toString());
                 }
            }
            httpclient.getConnectionManager().shutdown();    
            
          } 
          else 
          { 
            mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString()); 
          } 
        } 
        catch (ClientProtocolException e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace(); 
        } 
        catch (IOException e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace(); 
        } 
        catch (Exception e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace();  
        }  
         
      } 
    }); 
    mButton2.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        //String uriAPI = "http://www.dubblogs.cc:8751/Android/Test/API/Get/index.php?str=I+am+Get+String";
    	  String uriAPI = "http://10.0.2.2/-5--1-5-1";
        HttpGet httpRequest = new HttpGet(uriAPI); 
        try 
        {
          DefaultHttpClient httpclient = new DefaultHttpClient();
          HttpResponse httpResponse = httpclient.execute(httpRequest); 
          if(httpResponse.getStatusLine().getStatusCode() == 200)  
          { 
        	HttpEntity entity = httpResponse.getEntity();
            String strResult = EntityUtils.toString(entity);
            strResult = eregi_replace("(\r\n|\r|\n|\n\r)","",strResult);
            mTextView1.setText(strResult);
            
            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
            if (entity != null) {
                entity.consumeContent();
            }
    
            if (cookies.isEmpty()) {
                Log.v("******", "NONE");
            } else {
                 for (int i = 0; i < cookies.size(); i++) {             
                   Log.v("***","- domain " + cookies.get(i).getDomain());
                   Log.v("***","- path " + cookies.get(i).getPath());
                   Log.v("***","- value " + cookies.get(i).getValue());
                   Log.v("***","- name " + cookies.get(i).getName());
                   Log.v("***","- port " + cookies.get(i).getPorts());
                   Log.v("***","- comment " + cookies.get(i).getComment());
                   Log.v("***","- commenturl" + cookies.get(i).getCommentURL());
                   Log.v("***","- all " + cookies.get(i).toString());
                 }
            }
            httpclient.getConnectionManager().shutdown();    
          } 
          else 
          { 
            mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString()); 
          } 
        } 
        catch (ClientProtocolException e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace(); 
        } 
        catch (IOException e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace(); 
        } 
        catch (Exception e) 
        {  
          mTextView1.setText(e.getMessage().toString()); 
          e.printStackTrace();  
        }  
      } 
    }); 
  }
  
    public String eregi_replace(String strFrom, String strTo, String strTarget)
    {
      String strPattern = "(?i)"+strFrom;
      Pattern p = Pattern.compile(strPattern);
      Matcher m = p.matcher(strTarget);
      if(m.find())
      {
        return strTarget.replaceAll(strFrom, strTo);
      }
      else
      {
        return strTarget;
      }
    }
} 
