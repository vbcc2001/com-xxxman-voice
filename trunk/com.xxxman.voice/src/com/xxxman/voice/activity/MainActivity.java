package com.xxxman.voice.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.xxxman.voice.R;
import com.xxxman.voice.adapter.ListViewAdapter;
import com.xxxman.voice.helper.VoiceObjectHelper;
import com.xxxman.voice.object.VoiceObject;
import com.xxxman.voice.parse.ParseApplication;
import com.xxxman.voice.service.VoicePlayerService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
/**
 * 主界面
 * @author kkkaiser 20120724
 *
 */
public class MainActivity extends Activity  {
	
    private String title=null;
    private ListView listView=null;
    private ListViewAdapter adapter = null;
    private List<VoiceObject> voiceObjectlist = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				ParseApplication app =(ParseApplication) getApplication();
				app.getVoiceObjectList().add(voiceObjectlist.get(position));
				app.setCurrentNumber(app.getVoiceObjectList().size());
				startService();
			}
        	
        });
        VoiceObject vo = new VoiceObject();
        vo.setUri("http://mp3.mwap8.com/destdir/Music/2009/20090601/ZuiXuanMinZuFeng20090601119.mp3");
        vo.setTitle("不知道"+new Date().getTime());
        vo.setType("音乐");
        vo.getParseObject().saveInBackground();
        init();
    }
    public void startService(){
	    Intent intent = new Intent(this,VoicePlayerService.class);
	    this.startService(intent);
    }
    /**
     * 数据初始化
     */
    public void init(){
    	
        ParseQuery query = new ParseQuery("VoiceObject");
        query.findInBackground(new FindCallback  () {
			@Override
			public void done(List<ParseObject> list, ParseException e) {
	            if (e == null) {
	            	voiceObjectlist = VoiceObjectHelper.getVoiceObject(list);
	    			setData(voiceObjectlist);
	            } else {
	            	e.printStackTrace();
	            }				
			}
        });

    }
    /**
     * 将数据填充到视图中
     * @param object
     */
    public void setData(List<VoiceObject> voiceObjectlist){
    	if(voiceObjectlist!=null && voiceObjectlist.size()>0){
    		adapter = new ListViewAdapter(this,voiceObjectlist);
	        listView.setAdapter(adapter);
    	}
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
