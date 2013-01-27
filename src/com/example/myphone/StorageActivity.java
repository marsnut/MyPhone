package com.example.myphone;

import java.io.File;
import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StorageActivity extends Activity {
	  private Button myButton;
	  private ProgressBar myProgressBar;
	  private TextView myTextView;

	  /** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState)
	  {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_storage);

	    myButton = (Button) findViewById(R.id.myButton);
	    myProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
	    myTextView = (TextView) findViewById(R.id.myTextView);

	    myButton.setOnClickListener(new Button.OnClickListener()
	    {
	      @Override
	      public void onClick(View arg0)
	      {
	        showSize();
	      }

	    });

	  }

	  private void showSize()
	  {
	    myTextView.setText("");
	    myProgressBar.setProgress(0);
	    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
	    {
	      File path = Environment.getExternalStorageDirectory();

	      StatFs statFs = new StatFs(path.getPath());
	      long blockSize = statFs.getBlockSize();
	      long totalBlocks = statFs.getBlockCount();
	      long availableBlocks = statFs.getAvailableBlocks();

	      String[] total = fileSize(totalBlocks * blockSize);
	      String[] available = fileSize(availableBlocks * blockSize);

	      int ss = Integer.parseInt(available[0]) * myProgressBar.getMax()
	          / Integer.parseInt(total[0]);

	      myProgressBar.setProgress(ss);
	      String text = "总内存" + total[0] + total[1] + "\n";
	      text += "可用内存" + available[0] + available[1];
	      myTextView.setText(text);

	    } else if (Environment.getExternalStorageState().equals(
	        Environment.MEDIA_REMOVED))
	    {
	      String text = "SD CARD";
	      myTextView.setText(text);
	    }
	  }

	  private String[] fileSize(long size)
	  {
	    String str = "";
	    if (size >= 1024)
	    {
	      str = "KB";
	      size /= 1024;
	      if (size >= 1024)
	      {
	        str = "MB";
	        size /= 1024;
	      }
	    }

	    DecimalFormat formatter = new DecimalFormat();
	    formatter.setGroupingSize(3);
	    String result[] = new String[2];
	    result[0] = formatter.format(size);
	    result[1] = str;

	    return result;
	  }	
}
