package com.example.myphone;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

public class ListenerActivity extends Activity {
	
	private TextView mTextView;
	private EditText mEditText;
	private Button mButton;
	private ImageButton mImageButton;
	private CheckBox mCheckBox1;
	private CheckBox mCheckBox2;
	private CheckBox mCheckBox3;
	private RadioGroup mRadioGroup;
	private RadioButton mRadioButton0;
	private RadioButton mRadioButton1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listener);
	
		mTextView = (TextView)findViewById(R.id.textView1);
		mEditText = (EditText)findViewById(R.id.editText1);
		mButton = (Button)findViewById(R.id.button1);
		mImageButton = (ImageButton)findViewById(R.id.imageButton1);
		mCheckBox1 = (CheckBox)findViewById(R.id.checkBox1);
		mCheckBox2 = (CheckBox)findViewById(R.id.checkBox2);
		mCheckBox3 = (CheckBox)findViewById(R.id.checkBox3);
		mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		mRadioButton0 = (RadioButton)findViewById(R.id.radio0);
		mRadioButton1 = (RadioButton)findViewById(R.id.radio1);
		
		mEditText.setOnKeyListener(new EditText.OnKeyListener() {
			@Override
			public boolean onKey(View v, int arg1, KeyEvent arg2) {
				mTextView.setText(mEditText.getText());
				return false;
			}
		});
		mImageButton.setOnFocusChangeListener(new ImageButton.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean isFocused) {
				if(isFocused)
					mImageButton.setImageResource(R.drawable.qq01);
				else
					mImageButton.setImageResource(R.drawable.qq02);
			}
		});
		mImageButton.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
		        mImageButton.setImageDrawable(getResources().getDrawable(
		                R.drawable.qq01));
		        mImageButton.setImageDrawable(getResources().getDrawable(
		                R.drawable.qq02));				
			}
		});
		mButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
			    /*
			    Toast.makeText(
			       ListenerActivity.this,
			       "toast "+mEditText.getText().toString()+" ok",
			       Toast.LENGTH_LONG
			    ).show();
			    */
			    
		        ImageView mView01 = new ImageView(ListenerActivity.this);
		        TextView mTextView = new TextView(ListenerActivity.this);
		        LinearLayout lay = new LinearLayout(ListenerActivity.this);
		        
		        //validate
		        Linkify.addLinks(mTextView,Linkify.WEB_URLS|
			            Linkify.EMAIL_ADDRESSES|Linkify.PHONE_NUMBERS);
		        
		        Toast toast = Toast.makeText(ListenerActivity.this, 
		        		"toast "+mEditText.getText().toString()+" ok", Toast.LENGTH_LONG);        
		        View textView = toast.getView();         
		        lay.setOrientation(LinearLayout.HORIZONTAL);
		        mView01.setImageResource(R.drawable.icon);
		        lay.addView(mView01);
		        lay.addView(textView);    
		        toast.setView(lay);        
		        toast.show(); 
			        
			    mEditText.setText("");
			}
		});
		
		mCheckBox1.setOnCheckedChangeListener(mCheckBoxChanged);
		mCheckBox2.setOnCheckedChangeListener(mCheckBoxChanged);
		mCheckBox3.setOnCheckedChangeListener(mCheckBoxChanged);
		
		mRadioGroup.setOnCheckedChangeListener(mRadioChanged);
	}
	
	private CheckBox.OnCheckedChangeListener mCheckBoxChanged =
	new CheckBox.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton button, boolean isChecked) {
			String str="";
			if( mCheckBox1.isChecked()) str += "checked1";
			if( mCheckBox2.isChecked()) str += "checked2";
			if( mCheckBox3.isChecked()) str += "checked3";
			mTextView.setText(str);
		}
	};
	private RadioGroup.OnCheckedChangeListener mRadioChanged = 
	new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			String str="";
			if( mRadioButton0.isChecked()) str = "radio0";
			if( mRadioButton1.isChecked()) str = "radio1";
			mTextView.setText(str);
		}
	};

}
