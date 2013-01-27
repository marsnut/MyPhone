package com.example.myphone;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class SdcardActivity extends Activity
{
  private Button myButton1;
  private Button myButton2;
  private File fileDir;
  private File sdcardDir;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sdcard);

    myButton1 = (Button) findViewById(R.id.myButton1);
    myButton2 = (Button) findViewById(R.id.myButton2);

    fileDir = this.getFilesDir();

    sdcardDir = Environment.getExternalStorageDirectory();

    if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED))
    {
      myButton2.setEnabled(false);
    }

    myButton1.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        String path = fileDir.getParent() + java.io.File.separator
            + fileDir.getName();
        showListActivity(path);
      }
    });
    myButton2.setOnClickListener(new Button.OnClickListener()
    {

      @Override
      public void onClick(View arg0)
      {
        String path = sdcardDir.getParent() + sdcardDir.getName();
        showListActivity(path);
      }
    });
  }

  private void showListActivity(String path)
  {
    Intent intent = new Intent();
    intent.setClass(this, Sdcard_ex1.class);
    Bundle bundle = new Bundle();
    bundle.putString("path", path);
    intent.putExtras(bundle);
    startActivity(intent);
  }
}