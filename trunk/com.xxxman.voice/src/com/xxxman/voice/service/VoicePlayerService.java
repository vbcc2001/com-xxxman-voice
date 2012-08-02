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
 * ��̨�������ŷ���
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
	  * ��ͷ����һ�� voiceObject
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
	 * ֹͣ����
	 */
	public void stop(){
		mediaPlayer.stop();
	}
	/**
	 * ��õ�ǰ���ŵ�VoiceObject
	 * @return
	 */
	public VoiceObject getCurrentVoiceObject(){
		return null;
	}
}
