package com.xxxman.voice.activity;

import java.io.IOException;

import com.xxxman.voice.R;
import com.xxxman.voice.R.layout;
import com.xxxman.voice.R.menu;
import com.xxxman.voice.service.VoiceStreamingService;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class VoicePlayerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final boolean isCustom = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_voice_player);
        Window mWindow = getWindow();
        mWindow.setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlebar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }
}
