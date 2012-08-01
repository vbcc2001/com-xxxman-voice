package com.xxxman.voice.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.xxxman.voice.object.VoiceObject;
import com.xxxman.voice.parse.ParseApplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public  class VoicePlayerService extends Service{
	private List<VoiceObject> voiceObjectList = new ArrayList<VoiceObject>();
	private MediaPlayer mediaPlayer = new MediaPlayer(); ;
	private int i = 0;
	private VBinder vBinder = new VBinder();
//	public VoicePlayerService() {
//		if(mediaPlayer == null){
//		     mediaPlayer = new MediaPlayer();			
//		}
//		if(voiceObjectList == null){
//			voiceObjectList = new ArrayList<VoiceObject>();			
//		}
//		ParseApplication app= (ParseApplication)this.getApplication();
//		this.voiceObjectList = app.getVoiceObjectList();
//		this.i = app.getCurrentNumber();
//	}
	@Override
	public IBinder onBind(Intent intent) {
		return vBinder;
	}
	class VBinder extends Binder {
		VoicePlayerService getService(){
		   return VoicePlayerService.this;
		  }
	}
	 @Override  
	 public void onCreate() {
			if(mediaPlayer == null){
			     mediaPlayer = new MediaPlayer();			
			}
			if(voiceObjectList == null){
				voiceObjectList = new ArrayList<VoiceObject>();			
			}
			ParseApplication app= (ParseApplication)this.getApplication();
			this.voiceObjectList = app.getVoiceObjectList();
			//this.i = app.getCurrentNumber();
			play();
	 }

	/**
	 * Ìí¼ÓÐÂÉùÒô
	 */
	public void addVoiceObject(VoiceObject voiceObject){
		voiceObjectList.add(voiceObject);
	}
	public void play(){
		Uri uri = Uri.parse(voiceObjectList.get(i).getUri());
		try {
			mediaPlayer.setDataSource(this.getApplicationContext(), uri);
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
		
		//mediaPlayer.addTimedTextSource(context, uri, mimeType)
	}
}
