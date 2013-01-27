package com.example.myphone;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SimAdapter extends BaseAdapter
{
  private LayoutInflater mInflater;
  private List<String> items;
  private List<String> values;

  public SimAdapter(Context context,List<String> item,List<String> value)
  {
    mInflater = LayoutInflater.from(context);
    items = item;
    values = value;
  }
  
  @Override
  public int getCount()
  {
    return items.size();
  }

  @Override
  public Object getItem(int position)
  {
    return items.get(position);
  }
  
  @Override
  public long getItemId(int position)
  {
    return position;
  }
  
  @Override
  public View getView(int position,View convertView,ViewGroup parent)
  {
    ViewHolder holder;
  
    if(convertView == null)
    {
      convertView = mInflater.inflate(R.layout.sim_row_layout,null);
      holder = new ViewHolder();
      holder.text1=(TextView)convertView.findViewById(R.id.myText1);
      holder.text2=(TextView)convertView.findViewById(R.id.myText2);
    
      convertView.setTag(holder);
    }
    else
    {
      holder = (ViewHolder) convertView.getTag();
    }
    holder.text1.setText(items.get(position).toString());
    holder.text2.setText(values.get(position).toString());
  
    return convertView;
  }
  
  private class ViewHolder
  {
    TextView text1;
    TextView text2;
  }
}