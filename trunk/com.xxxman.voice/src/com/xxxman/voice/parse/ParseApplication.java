package com.xxxman.voice.parse;

import java.util.ArrayList;
import java.util.List;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseRole;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.xxxman.voice.object.UserObject;
import com.xxxman.voice.object.VoiceObject;

import android.app.Application;

public class ParseApplication extends Application {
	
	//�����б�
	private List<VoiceObject> voiceObjectList = new ArrayList<VoiceObject>(); ; 
	//��ǰ���ŵı��
	private VoiceObject nowPlayingVoiceObject ;
	//�û���¼��Ϣ
	private UserObject userObject ;
	@Override
	public void onCreate() {
		super.onCreate();

		//��ʼ����parse��ע���Application ID �� Client Key
		Parse.initialize(this, "7Pl8cPh7yQQQjhblyAyf9Bg7tt4H0Sm5OvDAAW14", "H7XGAaNHogyuYvDxXGBlkUqmQbmCEtgR2c0Ql62M");
		//Ĭ���û���¼
		ParseUser.logInInBackground("guest", "guest", new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {	
				if(e==null){
					userObject =  new UserObject(user);
				}else{
					e.printStackTrace();
				}
			}
		});
		ParseACL defaultACL = new ParseACL();
		defaultACL.setPublicReadAccess(true);
		// Moderators can also modify these objects
//		defaultACL.setRoleWriteAccess("guest",true);
		ParseACL.setDefaultACL(defaultACL, true);
//		ParseACL roleACL = new ParseACL();
//		roleACL.setPublicReadAccess(true);
//		ParseRole role = new ParseRole("guest", roleACL);
//		role.saveInBackground();
		
//        ParseUser user = new ParseUser();
//        user.setUsername("guest");
//        user.setPassword("guest");
//		user.signUpInBackground(new SignUpCallback(){
//			@Override
//			public void done(ParseException arg0) {
//			}} );		
	}
	/**
	 * �û�����
	 * @return
	 */
	public UserObject getUserObject() {
		return userObject;
	}
	/**
	 * �û�����
	 * @param userObject
	 */
	public void setUserObject(UserObject userObject) {
		this.userObject = userObject;
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
	/**
	 * ��ǰ���ŵ���������
	 */
	public VoiceObject getNowPlayingVoiceObject() {
		return nowPlayingVoiceObject;
	}
	/**
	 * ��ǰ���ŵ���������
	 * @param currentNumber
	 */
	public void setNowPlayingVoiceObject(VoiceObject nowPlayingVoiceObject) {
		this.nowPlayingVoiceObject = nowPlayingVoiceObject;
	}
}
