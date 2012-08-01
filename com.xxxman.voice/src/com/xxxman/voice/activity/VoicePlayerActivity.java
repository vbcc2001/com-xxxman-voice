package com.xxxman.voice.activity;

import java.io.IOException;

import com.xxxman.voice.R;
import com.xxxman.voice.R.layout;
import com.xxxman.voice.R.menu;
import com.xxxman.voice.service.VoiceStreamingService;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VoicePlayerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_player);
//        VoiceStreamingService vsService = new VoiceStreamingService(this);
//        try {
//			vsService.startStreaming("http://mp3.mwap8.com/destdir/Music/2009/20090601/ZuiXuanMinZuFeng20090601119.mp3");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }
}
