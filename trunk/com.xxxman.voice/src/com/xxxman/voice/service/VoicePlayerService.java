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

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public  class VoicePlayerService extends Service{
	private List<VoiceObject> voiceObjectList;
	private MediaPlayer mediaPlayer ;
	
	public VoicePlayerService() {
	     mediaPlayer = new MediaPlayer();
	     voiceObjectList = new ArrayList<VoiceObject>();
	     mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
	          public boolean onError(MediaPlayer mp, int what, int extra) {
	        	  showDialog
	              return false;
	          }
	      });
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	/**
	 * Ìí¼ÓÐÂÉùÒô
	 */
	public void addVoiceObject(VoiceObject voiceObject){
		voiceObjectList.add(voiceObject);
	}
	
	public void play(){

	}
}
