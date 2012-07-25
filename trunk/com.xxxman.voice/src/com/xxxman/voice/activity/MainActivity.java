package com.xxxman.voice.activity;

import com.xxxman.voice.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * Ö÷½çÃæ
 * @author kkkaiser 20120724
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
