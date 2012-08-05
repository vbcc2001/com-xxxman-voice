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
	
	//播放列表
	private List<VoiceObject> voiceObjectList = new ArrayList<VoiceObject>(); ; 
	//当前播放的编号
	private VoiceObject nowPlayingVoiceObject ;
	//用户登录信息
	private UserObject userObject ;
	@Override
	public void onCreate() {
		super.onCreate();

		//初始化在parse中注册的Application ID 和 Client Key
		Parse.initialize(this, "7Pl8cPh7yQQQjhblyAyf9Bg7tt4H0Sm5OvDAAW14", "H7XGAaNHogyuYvDxXGBlkUqmQbmCEtgR2c0Ql62M");
		//默认用户登录
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
	 * 用户对象
	 * @return
	 */
	public UserObject getUserObject() {
		return userObject;
	}
	/**
	 * 用户对象
	 * @param userObject
	 */
	public void setUserObject(UserObject userObject) {
		this.userObject = userObject;
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
	/**
	 * 当前播放的声音对象
	 */
	public VoiceObject getNowPlayingVoiceObject() {
		return nowPlayingVoiceObject;
	}
	/**
	 * 当前播放的声音对象
	 * @param currentNumber
	 */
	public void setNowPlayingVoiceObject(VoiceObject nowPlayingVoiceObject) {
		this.nowPlayingVoiceObject = nowPlayingVoiceObject;
	}
}
