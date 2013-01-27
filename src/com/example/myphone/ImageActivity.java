package com.example.myphone;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends Activity {
	private ImageView mImageView01;
	private ImageView mImageView02;
	private ImageView mImageView03;
	private Button mButton01;
	private Button mButton02;
	private Button mButton03;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
	    mImageView01 = (ImageView)findViewById(R.id.myImageView1);
	    mImageView02 = (ImageView)findViewById(R.id.myImageView2);
	    mImageView03 = (ImageView)findViewById(R.id.myImageView3);
	    mButton01 = (Button) findViewById(R.id.myButton1);
	    mButton02 = (Button) findViewById(R.id.myButton2);
	    mButton03 = (Button) findViewById(R.id.myButton3);
	    
    	mImageView01.setImageDrawable(getResources().getDrawable(R.drawable.right)); 
	    mImageView02.setImageDrawable(getResources().getDrawable(R.drawable.oa));
	    
	    mButton01.setOnClickListener(new Button.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	    	  mImageView01.setImageDrawable(getResources().getDrawable(R.drawable.right));
	      }
	    });
	    mButton02.setOnClickListener(new Button.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	    	  mImageView01.setImageDrawable(getResources().getDrawable(R.drawable.left));
	      }
	    });
	    mButton03.setOnClickListener(new Button.OnClickListener() {
		      @Override
		      public void onClick(View v) {
		    	  loadNetImage();
		      }
		    });
	}
	
	private static HttpClient getHttpClient() {        
        HttpClient httpClient = new HttpClient();
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        // 设置 默认的超时重试处理策略
		httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// 设置 连接超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(1000);
		// 设置 读数据超时时间 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(1000);
		// 设置 字符集
		httpClient.getParams().setContentCharset("UTF-8");
		return httpClient;
	}	
	
	private static GetMethod getHttpGet(String url, String cookie, String userAgent) {
		GetMethod httpGet = new GetMethod(url);
		// 设置 请求超时时间
		httpGet.getParams().setSoTimeout(1000);
		//httpGet.setRequestHeader("Host", URLs.HOST);
		httpGet.setRequestHeader("Connection","Keep-Alive");
		httpGet.setRequestHeader("Cookie", cookie);
		httpGet.setRequestHeader("User-Agent", userAgent);
		return httpGet;
	}
	
	protected void loadNetImage() {
        String url = "http://ww2.sinaimg.cn/thumbnail/675e5a2bjw1e0pydwgwgnj.jpg";
		HttpClient httpClient = null;
		GetMethod httpGet = null;
		Bitmap bitmap = null;
        
        try 
        {          
    		httpClient = getHttpClient();
    		httpGet = getHttpGet(url, null, null);
			if (httpClient.executeMethod(httpGet) == HttpStatus.SC_OK) {
				InputStream inStream = httpGet.getResponseBodyAsStream();
				bitmap = BitmapFactory.decodeStream(inStream);
				inStream.close();
				
				mImageView03.setImageBitmap(bitmap);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpGet.releaseConnection();
			httpClient = null;
		}
	}
}

