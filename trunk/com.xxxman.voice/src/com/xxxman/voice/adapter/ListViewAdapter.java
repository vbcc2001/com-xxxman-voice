package com.xxxman.voice.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xxxman.voice.R;
import com.xxxman.voice.object.VoiceObject;

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


	private List<VoiceObject> dataList ;
	private Context context ;

	/**
	 * 获得数量
	 */
	public int getCount() {
		return dataList.size();
	}

	public Object getItem(int position) {
		VoiceObject item = dataList.get(position);
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
		VoiceObject item = (VoiceObject)getItem(position);
		TextView title_list_item_title=(TextView) view.findViewById(R.id.title_list_item_title);	
		TextView title_list_item_date=(TextView) view.findViewById(R.id.title_list_item_date);			
		title_list_item_title.setText(item.getTitle());
		title_list_item_date.setText(item.getCreateDateString());
		return view;
	}

	public List<VoiceObject> getDataList() {
		return dataList;
	}

	public void setDataList(List<VoiceObject> dataList) {
		this.dataList = dataList;
	}
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
