package com.xxxman.voice.parse;

import java.util.ArrayList;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.xxxman.voice.object.VoiceObject;

import android.app.Application;

public class ParseApplication extends Application {
	
	//播放列表
	private List<VoiceObject> voiceObjectList ; 
	//当前播放的编号
	private int currentNumber = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		voiceObjectList = new ArrayList<VoiceObject>();
		//初始化在parse中注册的Application ID 和 Client Key
		Parse.initialize(this, "7Pl8cPh7yQQQjhblyAyf9Bg7tt4H0Sm5OvDAAW14", "H7XGAaNHogyuYvDxXGBlkUqmQbmCEtgR2c0Ql62M");
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		ParseACL.setDefaultACL(defaultACL, true);
	}

	/**
	 * 播放列表
	 * @return
	 */
	public  List<VoiceObject> getVoiceObjectList() {
		return voiceObjectList;
	}
	/**
	 * 播放列表
	 * @param voiceObjectList
	 */
	public  void setVoiceObjectList(List<VoiceObject> voiceObjectList) {
		this.voiceObjectList = voiceObjectList;
	}
	/*
	 * 当前播放的编号
	 */
	public  int getCurrentNumber() {
		return currentNumber;
	}
	/**
	 * 当前播放的编号
	 * @param currentNumber
	 */
	public  void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}
}
