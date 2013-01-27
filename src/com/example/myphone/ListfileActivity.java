package com.example.myphone;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListfileActivity extends ListActivity {
	  private List<String> items=null;
	  private List<String> paths=null;
	  private String rootPath="/";
	  private TextView mPath;
	  
	  @Override
	  protected void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    
	    setContentView(R.layout.activity_listfile);
	    mPath=(TextView)findViewById(R.id.mPath);
	    
	    getFileDir(rootPath);
	  }
	  
	  private void getFileDir(String filePath) {
		mPath.setText(filePath);
		  
		items=new ArrayList<String>();
		paths=new ArrayList<String>();  
		File f=new File(filePath);  
		File[] files=f.listFiles();
		
	    if(!filePath.equals(rootPath)) {
	      items.add("Back to "+rootPath);
	      paths.add(rootPath);
	      items.add("Back to ../");
	      paths.add(f.getParent());
	    }
	    for(int i=0;i<files.length;i++) {
	      File file=files[i];
	      items.add(file.getName());
	      paths.add(file.getPath());
	    }
	    
	    ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,R.layout.simple_textview, items);
	    setListAdapter(fileList);
	  }
	  
	  @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	    File file = new File(paths.get(position));
	    if (file.isDirectory()) {
	      getFileDir(paths.get(position));
	    }
	    else {
	    	new AlertDialog.Builder(this).setIcon(R.drawable.icon)
	                       .setTitle("["+file.getName()+"] is File!")
	                       .setPositiveButton("OK",
	                       new DialogInterface.OnClickListener() {
	                         public void onClick(DialogInterface dialog,int whichButton) {
	                         }
	                       }).show();         
	    }
	  }
}
