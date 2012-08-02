package com.xxxman.voice.service;


import java.io.IOException;
import java.util.List;

import com.xxxman.voice.object.VoiceObject;
import com.xxxman.voice.parse.ParseApplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
/**
 * 后台声音播放服务
 * @author kkkaiser 20120802
 *
 */
public class VoicePlayerService extends Service{
	
	private List<VoiceObject> voiceObjectList ;
	private MediaPlayer mediaPlayer = new MediaPlayer();
	private int i = 0 ;
	private VBinder vBinder = new VBinder();
	@Override
	public IBinder onBind(Intent intent) {
		return vBinder;
	}
	public class VBinder extends Binder {
		public VoicePlayerService getService(){
		   return VoicePlayerService.this;
		  }
	}
	 @Override  
	 public void onCreate() {
			ParseApplication app= (ParseApplication)this.getApplication();
			this.voiceObjectList = app.getVoiceObjectList();
	 }
	 /**
	  * 从头播放一个 voiceObject
	  * @param voiceObject
	  */
	public void newPlay(VoiceObject voiceObject){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(voiceObject.getUri());
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 停止播放
	 */
	public void stop(){
		mediaPlayer.stop();
	}
	/**
	 * 获得当前播放的VoiceObject
	 * @return
	 */
	public VoiceObject getCurrentVoiceObject(){
		return null;
	}
}
