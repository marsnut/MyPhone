package com.example.myphone;

import android.app.Activity; 
import android.content.Intent; 
import android.net.Uri; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView; 

public class UriActivity extends Activity 
{
  private ListView mListView1; 
  private TextView mTextView1; 
  private String[] myFavor;
  private String  myUrl;
   
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.activity_uri); 
     
    mListView1 =(ListView) findViewById(R.id.myListView1); 
    mTextView1 = (TextView) findViewById(R.id.myTextView1); 
    mTextView1.setText("hello");
    myFavor = new String[] { 
                               "http://www.dubblogs.cc", 
                               "http://www.google.com", 
                               "http://www.yahoo.com", 
                               "http://www.msn.com", 
                             }; 
    ArrayAdapter<String> adapter = new ArrayAdapter<String> 
    	(this, android.R.layout.simple_list_item_1, myFavor); 
    
    mListView1.setAdapter(adapter);
    mListView1.setItemsCanFocus(true);  
    mListView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
    mListView1.setOnItemClickListener(new ListView.OnItemClickListener()
    { 
      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
          long arg3)
      {
    	  myUrl = arg0.getAdapter().getItem(arg2).toString();
    	  goToUrl(myUrl);
      }
    }); 
  } 
    private void goToUrl(String url)
    {
      Uri uri = Uri.parse(url); 
      Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
      startActivity(intent); 
    }
} 
