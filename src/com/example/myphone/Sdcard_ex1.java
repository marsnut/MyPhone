package com.example.myphone;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Sdcard_ex1 extends ListActivity
{
  private List<String> items = null;
  private String path;
  protected final static int MENU_NEW = Menu.FIRST;
  protected final static int MENU_DELETE = Menu.FIRST + 1;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sdcard_ex1);

    Bundle bunde = this.getIntent().getExtras();
    path = bunde.getString("path");

    java.io.File file = new java.io.File(path);
    if (!file.exists())
      file.mkdir();
    fill(file.listFiles());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    super.onOptionsItemSelected(item);
    switch (item.getItemId())
    {
      case MENU_NEW:
        showListActivity(path, "", "");
        break;
      case MENU_DELETE:
        deleteFile();
        break;
    }
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    super.onCreateOptionsMenu(menu);
    menu.add(Menu.NONE, MENU_NEW, 0, R.string.str_NewMenu);
    menu.add(Menu.NONE, MENU_DELETE, 0, R.string.str_DeleteMenu);
    return true;
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id)
  {
    File file = new File(path + java.io.File.separator + items.get(position));
    if (file.isFile())
    {
      String data = "";
      try
      {
        FileInputStream stream = new FileInputStream(file);
        StringBuffer sb = new StringBuffer();
        int c;
        while ((c = stream.read()) != -1)
        {
          sb.append((char) c);
        }
        stream.close();
        data = sb.toString();
      } catch (Exception e)
      {
        e.printStackTrace();

      }
      showListActivity(path, file.getName(), data);
    }
  }

  private void fill(File[] files)
  {
    items = new ArrayList<String>();
    if (files == null)
      return;
    for (File file : files)
      items.add(file.getName());
    ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, items);
    setListAdapter(fileList);
  }

  private void showListActivity(String path, String fileName, String data)
  {
    Intent intent = new Intent();
    intent.setClass(this, Sdcard_ex2.class);
    Bundle bundle = new Bundle();
    bundle.putString("path", path);
    bundle.putString("fileName", fileName);
    bundle.putString("data", data);
    intent.putExtras(bundle);
    startActivity(intent);
  }

  private void deleteFile()
  {
    int position = this.getSelectedItemPosition();
    if (position >= 0)
    {
      File file = new File(path + java.io.File.separator + items.get(position));
      file.delete();
      items.remove(position);
      getListView().invalidateViews();
    }

  }
}
