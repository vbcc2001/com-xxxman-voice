package com.xxxman.voice.parse;

import java.util.ArrayList;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.xxxman.voice.object.VoiceObject;

import android.app.Application;

public class ParseApplication extends Application {
	
	//�����б�
	private List<VoiceObject> voiceObjectList ; 
	//��ǰ���ŵı��
	private int currentNumber = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		voiceObjectList = new ArrayList<VoiceObject>();
		//��ʼ����parse��ע���Application ID �� Client Key
		Parse.initialize(this, "7Pl8cPh7yQQQjhblyAyf9Bg7tt4H0Sm5OvDAAW14", "H7XGAaNHogyuYvDxXGBlkUqmQbmCEtgR2c0Ql62M");
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		ParseACL.setDefaultACL(defaultACL, true);
	}

	/**
	 * �����б�
	 * @return
	 */
	public  List<VoiceObject> getVoiceObjectList() {
		return voiceObjectList;
	}
	/**
	 * �����б�
	 * @param voiceObjectList
	 */
	public  void setVoiceObjectList(List<VoiceObject> voiceObjectList) {
		this.voiceObjectList = voiceObjectList;
	}
	/*
	 * ��ǰ���ŵı��
	 */
	public  int getCurrentNumber() {
		return currentNumber;
	}
	/**
	 * ��ǰ���ŵı��
	 * @param currentNumber
	 */
	public  void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}
}
