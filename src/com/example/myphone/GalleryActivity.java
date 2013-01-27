package com.example.myphone;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GalleryActivity extends Activity {

	private TextView mText;
	Gallery mGallery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		
		Debug.startMethodTracing("yourGalleryTrace");
		
		mText = (TextView)findViewById(R.id.textView1);
		mGallery = (Gallery)findViewById(R.id.gallery1);
		mGallery.setAdapter(new ImageAdapter(this));
		mGallery.setOnItemClickListener(new Gallery.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		        Toast.makeText(GalleryActivity.this, getString(R.string.my_gallery_text_pre) 
		                + pos+ getString(R.string.my_gallery_text_post), 
		                Toast.LENGTH_SHORT
		                ).show();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		Debug.stopMethodTracing();
		super.onDestroy();
	}
	@Override
	protected void onStop() {
		//Debug.stopMethodTracing();
		super.onStop();
	}
	
	public class ImageAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	    private Context mContext; 
	    
	    private int[] myImageIds =
	                  {
	            R.drawable.photo1,
	            R.drawable.photo2,
	            R.drawable.photo3,
	            R.drawable.photo4,
	            R.drawable.photo5,
	            R.drawable.photo6,
	    		
	    				/*
	                    android.R.drawable.btn_minus,
	                    android.R.drawable.btn_plus,
	                    android.R.drawable.ic_lock_idle_low_battery, 
	                    android.R.drawable.ic_menu_camera
	                    */
	                  }; 
	    public ImageAdapter(Context c) {
	    	this.mContext = c;
	        TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
	        mGalleryItemBackground = a.getResourceId(
	            R.styleable.Gallery_android_galleryItemBackground, 0);
	        a.recycle();
	    } 
	    public int getCount() { return this.myImageIds.length; } 

	    public Object getItem(int position) { return position; } 
	    public long getItemId(int position) { return position; }
	    
	    public View getView(int position, View convertView, ViewGroup parent)
	    {
	        ImageView i = new ImageView(mContext);
	        i.setImageResource(myImageIds[position]);
	        i.setScaleType(ImageView.ScaleType.FIT_XY);
	        i.setLayoutParams(new Gallery.LayoutParams(136, 88));
	        i.setBackgroundResource(mGalleryItemBackground);
	    	
	      /*
	      ImageView i = new ImageView(this.mContext);
	      i.setImageResource(this.myImageIds[position]);
	      i.setScaleType(ImageView.ScaleType.FIT_XY); 
	      i.setLayoutParams(new Gallery.LayoutParams(120, 120));
	      */
	      return i; 
	    } 

	    public float getScale(boolean focused, int offset)
	    { 
	      return Math.max(0,1.0f/(float)Math.pow(2,Math.abs(offset)));
	    } 
	}
}