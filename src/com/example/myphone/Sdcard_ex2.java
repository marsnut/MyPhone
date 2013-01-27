package com.example.myphone;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Sdcard_ex2 extends Activity
{
  private String path;
  private String data;
  private EditText myEditText1;

  private EditText myDialogEditText;
  protected final static int MENU_SAVE = Menu.FIRST;
  private String fileName;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sdcard_ex2);

    myEditText1 = (EditText) findViewById(R.id.myEditText1);

    Bundle bunde = this.getIntent().getExtras();
    path = bunde.getString("path");
    data = bunde.getString("data");
    fileName = bunde.getString("fileName");
    myEditText1.setText(data);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    super.onOptionsItemSelected(item);
    switch (item.getItemId())
    {
      case MENU_SAVE:
        saveFile();
        break;
    }
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    super.onCreateOptionsMenu(menu);
    menu.add(Menu.NONE, MENU_SAVE, 0, R.string.str_SaveMenu);
    return true;
  }

  private void saveFile()
  {
    LayoutInflater factory = LayoutInflater.from(this);
    final View textEntryView = factory.inflate(R.layout.sdcard_save_dialog, null);
    Builder mBuilder1 = new AlertDialog.Builder(this);
    mBuilder1.setView(textEntryView);
    myDialogEditText = (EditText) textEntryView
        .findViewById(R.id.myDialogEditText);
    myDialogEditText.setText(fileName);

    mBuilder1.setPositiveButton(R.string.str_ok,
        new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialoginterface, int i)
          {
            String Filename = path + java.io.File.separator
                + myDialogEditText.getText().toString();

            java.io.BufferedWriter bw;
            try
            {
              bw = new java.io.BufferedWriter(new java.io.FileWriter(
                  new java.io.File(Filename)));

              String str = myEditText1.getText().toString();
              bw.write(str, 0, str.length());
              bw.newLine();
              bw.close();
            } catch (IOException e)
            {
              e.printStackTrace();
            }
            Intent intent = new Intent();
            intent.setClass(Sdcard_ex2.this, Sdcard_ex1.class);
            Bundle bundle = new Bundle();
            bundle.putString("path", path);
            intent.putExtras(bundle);
            startActivity(intent);

            finish();
          }
        });
    mBuilder1.setNegativeButton(R.string.str_cancel, null);
    mBuilder1.show();
  }
}
