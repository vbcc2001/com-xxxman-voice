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
import com.xxxman.voice.object.VoiceObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
/**
 * 主界面
 * @author kkkaiser 20120724
 *
 */
public class MainActivity extends Activity {
	
    private String title=null;
    private ListView listView=null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        
        VoiceObject vo = new VoiceObject();
        vo.setUri("http://fmn012.xnimg.cn/fmn012/tribe/20080922/16/28/A209620258381PEP.mp3");
        vo.setTitle("听海"+new Date().getTime());
        vo.saveInBackground();
        
        init();
        
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
	    			setData(VoiceObject.getVoiceObject(list));
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
    public void setData(List<VoiceObject> list){
    	if(list!=null && list.size()>0){
    		ListViewAdapter adapter = new ListViewAdapter();
	        adapter.setDataList(list);
	        adapter.setContext(this);
	        listView.setAdapter(adapter);
    	}
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
