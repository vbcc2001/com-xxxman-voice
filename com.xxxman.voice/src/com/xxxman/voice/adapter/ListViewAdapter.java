package com.xxxman.voice.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.xxxman.voice.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 通用列表适配器
 * @author kkkaiser
 *
 */
public class ListViewAdapter extends BaseAdapter  {


	private ArrayList<HashMap<String, String>> dataList ;
	private Context context ;

	/**
	 * 获得数量
	 */
	public int getCount() {
		return dataList.size();
	}

	public Object getItem(int position) {
		HashMap<String, String> item = dataList.get(position);
		return item;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout view;	
		if (convertView==null) {
			view =new LinearLayout(context);
			String inflatorKey=Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(inflatorKey);
			inflater.inflate(R.layout.list_item, view, true);
		}else {
			view=(LinearLayout) convertView;
		}
		TextView textView=(TextView) view.findViewById(R.id.title_list_item_title);	
		@SuppressWarnings("unchecked")
		HashMap<String, String> item = (HashMap<String, String>)getItem(position);
		textView.setText(item.get("title"));
		return view;
	}

	public ArrayList<HashMap<String, String>> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<HashMap<String, String>> dataList) {
		this.dataList = dataList;
	}
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
