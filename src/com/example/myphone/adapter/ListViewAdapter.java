package com.example.myphone.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListViewAdapter extends BaseAdapter {

	private Context 		context;
	private LayoutInflater	inflater;
	private int				itemResource;
	private int				itemThrumbResource;
	private List<String>	listItems;
	
	static class ListViewItem {
		TextView titile;
		TextView author;
		ImageView thumb;
	}
	
	public ListViewAdapter(Context context, List<String> listItems, int itemResource, int itemThrumbResource) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.itemResource = itemResource;
		this.itemThrumbResource = itemThrumbResource;
		this.listItems = listItems;
	}
	public int getCount() {
		return listItems.size();
	}
	public Object getItem(int arg) {
		return null;
	}
	public long getItemId(int arg) {
		return 0;
	}
	public View getView(int pos, View convertView, ViewGroup parent) {
		ListViewItem listViewItem = null;
		
		if(convertView != null) {
			convertView = inflater.inflate(itemResource, null);
			
			listViewItem = new ListViewItem();
			//listViewItem.titile = converView.findViewById() 
		}
		else {
			
		}
		return convertView;
	}
	
}
