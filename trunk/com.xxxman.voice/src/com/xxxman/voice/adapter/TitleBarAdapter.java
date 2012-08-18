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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 标题栏表适配器
 * @author xxxman
 *
 */
public class TitleBarAdapter extends BaseAdapter  {


	private List<String> list ;
	private Context context ;
	private String item;

	/**
	 * 构造函数,传入context 和 数据list
	 * @param context
	 * @param list
	 */
	public TitleBarAdapter(Context context,List<String> list){
		super();
		this.list = list;
		this.context = context;	
	}
	/**
	 * 获得数量
	 */
	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		String item = list.get(position);
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
			inflater.inflate(R.layout.title_bar_item, view, true);
		}else {
			view=(LinearLayout) convertView;
		}
		//设置标题,分类,点击数,更新日期
		item = (String)getItem(position);
		TextView textView_title_bar_item=(TextView) view.findViewById(R.id.textView_title_bar_item);
		textView_title_bar_item.setText(item);
		return view;
	}
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
