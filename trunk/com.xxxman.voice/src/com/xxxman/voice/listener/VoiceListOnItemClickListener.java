package com.xxxman.voice.listener;

import com.xxxman.voice.R;
import com.xxxman.voice.adapter.ListViewAdapter;
import com.xxxman.voice.asyncTask.VoicePlayAsyncTask;
import com.xxxman.voice.object.VoiceObject;
import com.xxxman.voice.parse.ParseApplication;
import com.xxxman.voice.service.VoicePlayerService;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 声音列表点击监听
 * @author xxxman
 *
 */
public class VoiceListOnItemClickListener implements OnItemClickListener {
	
    private ImageView playingImageView = null;
	private VoicePlayerService voicePlayerService ;
	
	public VoiceListOnItemClickListener(VoicePlayerService voicePlayerService){
		this.voicePlayerService = voicePlayerService;
	}
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ListViewAdapter adapter = (ListViewAdapter) parent.getAdapter();
		VoiceObject voiceObject = (VoiceObject)adapter.getItem(position);
		ParseApplication app = (ParseApplication)adapter.getContext().getApplicationContext();
		app.getVoiceObjectList().add(voiceObject);
		Context context = (Context)adapter.getContext();
		//当对象不是当前播放的声音对象时候
		if(voiceObject.equals(app.getNowPlayingVoiceObject())){
			if(voicePlayerService.isPlaying()){
				voicePlayerService.pause();
				ImageView imageView = (ImageView)view.findViewById(R.id.title_list_item_imageView);
				imageView.setImageResource(R.drawable.pause);
			}else {
				voicePlayerService.start();
				ImageView imageView = (ImageView)view.findViewById(R.id.title_list_item_imageView);
				imageView.setImageResource(R.drawable.playing);						
			}
		}else{
			app.setNowPlayingVoiceObject(voiceObject);
			VoicePlayAsyncTask task = new VoicePlayAsyncTask(voicePlayerService,voiceObject);
			task.execute(position);
			ImageView imageView = (ImageView)view.findViewById(R.id.title_list_item_imageView);
			if(playingImageView!=null){
				playingImageView.setImageResource(R.drawable.ic_launcher);
			}
			imageView.setImageResource(R.drawable.playing);
			playingImageView = imageView;
			voiceObject.setCheckedCount(voiceObject.getCheckedCount()+1);
			voiceObject.getParseObject().saveInBackground();
		}
	}

}
