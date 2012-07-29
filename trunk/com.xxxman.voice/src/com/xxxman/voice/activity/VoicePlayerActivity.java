package com.xxxman.voice.activity;

import com.xxxman.voice.R;
import com.xxxman.voice.R.layout;
import com.xxxman.voice.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VoicePlayerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_player);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }
}
