package com.example.myphone;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FilelistActivity extends Activity {
	private Button mButton;
	private EditText mKeyword;
	private TextView mResult;
	private TextView mTextView;
	AutoCompleteTextView mAutoCompleteTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filelist);
		
	    mKeyword=(EditText)findViewById(R.id.mKeyword);
	    mButton=(Button)findViewById(R.id.mButton);
	    mResult=(TextView) findViewById(R.id.mResult);
	    mResult=(TextView) findViewById(R.id.textView1);
	    
	    mButton.setOnClickListener(new Button.OnClickListener() {
	      public void onClick(View v) {
	        String keyword = mKeyword.getText().toString();
	        if(keyword.equals("")){
	          mResult.setText("search conditions?");
	        }else{
	      	  mResult.setText(searchFile(keyword));
	      	}
	      }
	    });

	    String[] autoStr = new String[] { "a", "abc", "abcd", "abcde", "awww" };

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_dropdown_item_1line, autoStr);
	    
        mAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        mAutoCompleteTextView.setAdapter(adapter);

        mAutoCompleteTextView.setOnKeyListener(new AutoCompleteTextView.OnKeyListener() {
        	@Override
        	public boolean onKey(View v, int arg1, KeyEvent arg2) {
        		mTextView.setText(mAutoCompleteTextView.getText());
        		Linkify.addLinks(mTextView, Linkify.WEB_URLS|Linkify.EMAIL_ADDRESSES|Linkify.PHONE_NUMBERS);
        		return false;
        	}
        });
	}

	private String searchFile(String keyword)
	{
	    String result="";
	    File[] files=new File("/").listFiles();
	    for( File f : files ){
		  if(f.getName().indexOf(keyword)>=0){	
		    result+=f.getPath()+"\n";
		  }
	    }
	    if(result.equals("")) result="§ä¤£¨ìÀÉ®×!!";
	    return result;
	}
}
