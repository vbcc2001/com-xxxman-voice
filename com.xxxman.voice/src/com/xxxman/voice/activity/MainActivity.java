package com.xxxman.voice.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.xxxman.voice.R;
import com.xxxman.voice.adapter.ListViewAdapter;

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
        
        ParseObject voiceObject = new ParseObject("VoiceObject");

        
    }
    /**
     * 数据初始化
     */
    public void init(){
    	
        ParseQuery query = new ParseQuery("GameScore");
        query.getInBackground("A10CCWqumk", new GetCallback() {
        	@Override
        	public void done(ParseObject object, ParseException e) {
	            if (e == null) {
	    			setData(object);
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
    public void setData(ParseObject object){
    	if(object!=null){
    		title = object.getString("playerName");
    	}        
    	ListViewAdapter adapter = new ListViewAdapter();
        //生成动态数组，并且转载数据  
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
        for(int i=0;i<300;i++)
        {  
            HashMap<String, String> map = new HashMap<String, String>();  
            map.put("title", title);   
            list.add(map);  
        }  
        adapter.setDataList(list);
        adapter.setContext(this);
        listView.setAdapter(adapter);
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
