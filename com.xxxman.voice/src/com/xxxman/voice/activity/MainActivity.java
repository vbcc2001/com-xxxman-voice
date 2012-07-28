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
 * ������
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
     * ���ݳ�ʼ��
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
     * ��������䵽��ͼ��
     * @param object
     */
    public void setData(ParseObject object){
    	if(object!=null){
    		title = object.getString("playerName");
    	}        
    	ListViewAdapter adapter = new ListViewAdapter();
        //���ɶ�̬���飬����ת������  
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
