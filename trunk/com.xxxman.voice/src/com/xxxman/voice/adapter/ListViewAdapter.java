package com.xxxman.voice.adapter;

import java.util.List;

import com.xxxman.voice.R;
import com.xxxman.voice.activity.VoicePlayerActivity;
import com.xxxman.voice.object.VoiceObject;
import com.xxxman.voice.parse.ParseApplication;
import com.xxxman.voice.service.VoicePlayerService;
import com.xxxman.voice.service.VoiceStreamingService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * ListView�б�������
 * @author xxxman
 *
 */
public class ListViewAdapter extends BaseAdapter  {


	private List<VoiceObject> dataList ;
	private Context context ;
	private VoiceObject item;

	/**
	 * ���캯��,����context �� ����list
	 * @param context
	 * @param list
	 */
	public ListViewAdapter(Context context,List<VoiceObject> list){
		super();
		this.dataList = list;
		this.context = context;	
	}
	/**
	 * �������
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
		//���ñ���,����,�����,��������
		item = (VoiceObject)getItem(position);
		TextView title_list_item_title=(TextView) view.findViewById(R.id.title_list_item_title);
		TextView title_list_item_type=(TextView) view.findViewById(R.id.title_list_item_type);	
		TextView title_list_item_checked=(TextView) view.findViewById(R.id.title_list_item_checked);	
		TextView title_list_item_date=(TextView) view.findViewById(R.id.title_list_item_date);	
		title_list_item_title.setText(item.getTitle());
		title_list_item_type.setText(context.getString(R.string.title_list_item_type_left_half)+item.getType()+context.getString(R.string.title_list_item_type_right_harf));
		title_list_item_checked.setText(context.getString(R.string.title_list_item_checked)+item.getCheckedCount());
		title_list_item_date.setText(context.getString(R.string.title_list_item_date)+item.getCreateDateString());
		view.setTag(position);
//		view.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				ParseApplication a= (ParseApplication)context.getApplicationContext();
//				a.getVoiceObjectList().add(item);
//				a.setCurrentNumber(a.getVoiceObjectList().size());
////			    Bundle bundle = new Bundle();
//			    Intent intent = new Intent(context,VoicePlayerService.class);
////			    intent.putExtras(bundle);
//			    context.startService(intent);
//			}
//		});
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
