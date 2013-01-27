package com.example.myphone;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;

public class MatrixActivity extends Activity {
	private ImageView mImageView;
	private Button mButton01;
	private Button mButton02;
	private AbsoluteLayout layout1;
	private Bitmap bmp;
	private int id=0;
	private int displayWidth;
	private int displayHeight;
	private float scaleWidth=1;
	private float scaleHeight=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matrix);

	    DisplayMetrics dm=new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(dm);
	    displayWidth=dm.widthPixels;
	    displayHeight=dm.heightPixels-80;

	    bmp=BitmapFactory.decodeResource(getResources(),R.drawable.ex04_23);
	    mImageView = (ImageView)findViewById(R.id.myImageView);
	    layout1 = (AbsoluteLayout)findViewById(R.id.layout1); 
	    mButton01 = (Button)findViewById(R.id.myButton1);
	    mButton02 = (Button)findViewById(R.id.myButton2);
	    
	    mButton01.setOnClickListener(new Button.OnClickListener() {
	      @Override
	      public void onClick(View v)
	      {
	        small();
	      }
	    });
	    mButton02.setOnClickListener(new Button.OnClickListener() {
	      @Override
	      public void onClick(View v)
	      {
	        big();
	      }
	    });    
	  }
	  /*Ðý×ªÍ¼Æ¬
        Matrix matrix = new Matrix(); 
        matrix.postScale(scaleWidth, scaleHeight);
        
        matrix.setRotate(5*ScaleAngle);

        Bitmap resizedBitmap = Bitmap.createBitmap(mySourceBmp, 0, 0, widthOrig, heightOrig, matrix, true);
        BitmapDrawable myNewBitmapDrawable = new BitmapDrawable(resizedBitmap); 
        mImageView1.setImageDrawable(myNewBitmapDrawable);
        mTextView1.setText(Integer.toString(5*ScaleAngle));
	   */
	  private void small() {
	    int bmpWidth=bmp.getWidth();
	    int bmpHeight=bmp.getHeight();
		double scale=0.8;  
	    scaleWidth=(float) (scaleWidth*scale);
	    scaleHeight=(float) (scaleHeight*scale);
	    
	    Matrix matrix = new Matrix();  
	    matrix.postScale(scaleWidth, scaleHeight); 
	    Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true); 
	     
	    if(id==0) {
	      layout1.removeView(mImageView);
	    }
	    else {
	      layout1.removeView((ImageView)findViewById(id));
	    }
	    id++;
	    ImageView imageView = new ImageView(this);  
	    imageView.setId(id);
	    imageView.setImageBitmap(resizeBmp);
	    layout1.addView(imageView); 
	    setContentView(layout1);
	    
	    mButton02.setEnabled(true);
	  }
	  
	  private void big()
	  {   
	    int bmpWidth=bmp.getWidth();
	    int bmpHeight=bmp.getHeight();
	    double scale=1.25;  

	    scaleWidth=(float)(scaleWidth*scale);
	    scaleHeight=(float)(scaleHeight*scale);
	    
	    Matrix matrix = new Matrix();  
	    matrix.postScale(scaleWidth, scaleHeight); 
	    Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true); 
	      
	    if(id==0) {
	      layout1.removeView(mImageView);
	    }
	    else {
	      layout1.removeView((ImageView)findViewById(id));
	    }
	    id++; 
	    ImageView imageView = new ImageView(this);  
	    imageView.setId(id);
	    imageView.setImageBitmap(resizeBmp);
	    layout1.addView(imageView); 
	    setContentView(layout1); 
	    
	    if(scaleWidth*scale*bmpWidth>displayWidth||scaleHeight*scale*bmpHeight>displayHeight)
	    {
	      mButton02.setEnabled(false);
	    }
	  }
}
