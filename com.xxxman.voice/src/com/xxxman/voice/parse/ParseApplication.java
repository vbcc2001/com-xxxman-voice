package com.xxxman.voice.parse;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		//��ʼ����parse��ע���Application ID �� Client Key
		Parse.initialize(this, "7Pl8cPh7yQQQjhblyAyf9Bg7tt4H0Sm5OvDAAW14", "H7XGAaNHogyuYvDxXGBlkUqmQbmCEtgR2c0Ql62M");
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		ParseACL.setDefaultACL(defaultACL, true);
	}

}
