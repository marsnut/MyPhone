package com.example.myphone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageviewActivity extends Activity {
	private ImageView mImageView01;
	private ImageView mImageView02;
	private ImageView mImageView03;
	private Button mButton;
	private TextView mText;
	private static int[] s1=new int[]{R.drawable.photo1,R.drawable.photo2,R.drawable.photo3};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageview);
		
	    mText=(TextView)findViewById(R.id.mText);
	    mImageView01=(ImageView)findViewById(R.id.mImage01);
	    mImageView02=(ImageView)findViewById(R.id.mImage02);
	    mImageView03=(ImageView)findViewById(R.id.mImage03);
	    mButton=(Button)findViewById(R.id.mButton);

	    randon();
	    mImageView01.setOnClickListener(new View.OnClickListener() {
	      public void onClick(View v) {
	        mImageView01.setImageDrawable(getResources().getDrawable(s1[0]));
	        mImageView02.setImageDrawable(getResources().getDrawable(s1[1]));
	        mImageView03.setImageDrawable(getResources().getDrawable(s1[2]));
	        mImageView02.setAlpha(100);
	        mImageView03.setAlpha(100);
	        if(s1[0]==R.drawable.photo4){
	          mText.setText("OK!");
	        }
	        else
	        {
	          mText.setText("try again?");
	        }
	      }
	    });
	    
	    mImageView02.setOnClickListener(new View.OnClickListener() {
	      public void onClick(View v) {
	        mImageView01.setImageDrawable(getResources().getDrawable(s1[0]));
	        mImageView02.setImageDrawable(getResources().getDrawable(s1[1]));
	        mImageView03.setImageDrawable(getResources().getDrawable(s1[2]));
	        mImageView01.setAlpha(100);
	        mImageView03.setAlpha(100);

	        if(s1[1]==R.drawable.photo1){
	          mText.setText("OK!");
	        }
	        else
	        {
	          mText.setText("try again?");
	        }
	      }
	    });
	    
	    mImageView03.setOnClickListener(new View.OnClickListener()
	    {
	      public void onClick(View v)
	      {
	        mImageView01.setImageDrawable(getResources().getDrawable(s1[0]));
	        mImageView02.setImageDrawable(getResources().getDrawable(s1[1]));
	        mImageView03.setImageDrawable(getResources().getDrawable(s1[2]));
	        mImageView01.setAlpha(100);
	        mImageView02.setAlpha(100);

	        if(s1[2]==R.drawable.photo1){
	          mText.setText("OK!");
	        }
	        else
	        {
	          mText.setText("try again?");
	        }
	      }
	    });
	    
	    mButton.setOnClickListener(new Button.OnClickListener() {
	      public void onClick(View v) {
	        mText.setText("select one?");
	        mImageView01.setImageDrawable(getResources()
	        		    .getDrawable(R.drawable.photo4));
	        mImageView02.setImageDrawable(getResources()
	        		    .getDrawable(R.drawable.photo4));
	        mImageView03.setImageDrawable(getResources()
	        		    .getDrawable(R.drawable.photo4));
	        mImageView01.setAlpha(255);
	        mImageView02.setAlpha(255);
	        mImageView03.setAlpha(255);
	        randon();
	        }
	      });    
	}
	
	private void randon() {
	    for(int i=0;i<3;i++) {
	        int tmp=s1[i];
	        int s=(int)(Math.random()*2);
	        s1[i]=s1[s];
	        s1[s]=tmp;
	      }        
	}
}
