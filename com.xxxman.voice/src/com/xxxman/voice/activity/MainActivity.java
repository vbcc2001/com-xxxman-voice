package com.xxxman.voice.activity;

import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.xxxman.voice.R;
import com.xxxman.voice.adapter.ListViewAdapter;
import com.xxxman.voice.helper.VoiceObjectHelper;
import com.xxxman.voice.object.VoiceObject;
import com.xxxman.voice.parse.ParseApplication;
import com.xxxman.voice.service.VoicePlayerService;
import com.xxxman.voice.service.VoicePlayerService.VBinder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
/**
 * 主界面
 * @author kkkaiser 20120724
 *
 */
public class MainActivity extends Activity  {
	//列表视图
    private ListView listView=null;
    //列表视图适配器
    private ListViewAdapter adapter = null;
    //listView的数据对象List
    private List<VoiceObject> voiceObjectlist = null;
    //声音播放器服务
    private VoicePlayerService voicePlayerService = null;
    //主视图和声音播放器服务通讯实现类
	private ServiceConnection serviceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder service) {
        	VBinder vBinder = (VBinder) service;
        	voicePlayerService = (VoicePlayerService) vBinder.getService();
        }
		public void onServiceDisconnected(ComponentName name) {
			voicePlayerService = null ;
		}
    };
    /**
     * 创建主视图方法
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得主视图上的列表视图
        listView = (ListView) findViewById(R.id.listView);
        /*
         * 设置列表视图每列的单击监听:
         * 1 点击后,将声音对象放到全局播放类表中,
         * 2 设定为当前播放编号设置为最后的声音对象,
         * 3 播放声音对象
         */     
        listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				VoicePlayAsyncTask task = new VoicePlayAsyncTask();
				task.execute(position);
			}
        });
        
        VoiceObject vo = new VoiceObject();
        vo.setUri("http://mp3.mwap8.com/destdir/Music/2009/20090601/ZuiXuanMinZuFeng20090601119.mp3");
        vo.setTitle("最炫民族风"+new Date().getTime());
//        String url = "http://excerpts.contentreserve.com/FormatType-425/0017-1/172247-Sail.mp3";
//        vo.setUri(url);
//        vo.setTitle("sail"+new Date().getTime());        
        vo.setType("音乐");
        vo.getParseObject().saveInBackground();
        
        initListView();
        initService();
    }
    /**
     * 数据初始化
     */
    public void initListView(){
    	//获取服务器数据
        ParseQuery query = new ParseQuery("VoiceObject");
        query.findInBackground(new FindCallback  () {
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
     * 播放器服务初始化,并绑定
     */
    public void initService(){
	    Intent intent = new Intent(this,VoicePlayerService.class);
	    this.startService(intent);
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
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
    /**
     * 创建设置菜单
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    public class VoicePlayAsyncTask extends AsyncTask<Integer, Integer, String> {
    	
        @Override  
        protected void onPreExecute() {  
            super.onPreExecute();  
        } 
    	@Override
    	protected String doInBackground(Integer... params) {
    		// TODO Auto-generated method stub
    		int position = params[0];
			ParseApplication app =(ParseApplication) getApplication();
			app.getVoiceObjectList().add(voiceObjectlist.get(position));
			app.setCurrentNumber(app.getVoiceObjectList().size());
			voicePlayerService.newPlay(voiceObjectlist.get(position));
			publishProgress(4); //执行过程中交换
    		return null;
    	}
        @Override  
        protected void onProgressUpdate(Integer... progress) {  
            //这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数   
            super.onProgressUpdate(progress);  
        }
        @Override  
        protected void onPostExecute(String result) {  
            //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发  
            //这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"   
            super.onPostExecute(result);
        }
    }     
}
