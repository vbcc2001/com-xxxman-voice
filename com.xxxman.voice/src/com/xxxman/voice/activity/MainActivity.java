package com.xxxman.voice.activity;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        ListViewAdapter adapter = new ListViewAdapter();
        //adapter.
        //生成动态数组，并且转载数据  
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
        for(int i=0;i<300;i++)  
        {  
            HashMap<String, String> map = new HashMap<String, String>();  
            map.put("title", "This is Title....."+i);  
            map.put("ItemText", "20100102:"+i);  
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
