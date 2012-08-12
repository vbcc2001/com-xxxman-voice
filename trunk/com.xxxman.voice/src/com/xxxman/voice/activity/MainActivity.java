package com.xxxman.voice.activity;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.xxxman.voice.R;
import com.xxxman.voice.adapter.ListViewAdapter;
import com.xxxman.voice.asyncTask.VoicePlayAsyncTask;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
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
    private ParseApplication app = null ;
    //当前播放的Item的imageView
    private ImageView playingImageView = null;
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
        //设置窗体属性:自定义标题属性
        final boolean isCustom = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        //自定义标题栏
        Window window = getWindow();
        if(isCustom){
        	window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlebar);
        }
        app = (ParseApplication) getApplication();
        //获得主视图上的列表视图
        listView = (ListView) findViewById(R.id.listView);
        /*
         * 设置列表视图每列的单击监听:
         * 1 点击后,将声音对象放到全局播放类表中,
         * 2 设定为当前播放编号设置为最后的声音对象,
         * 3 通过异步任务播放声音对象
         */     
        listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				VoiceObject voiceObject = voiceObjectlist.get(position);
				app.getVoiceObjectList().add(voiceObject);
				//当对象不是当前播放的声音对象时候
				if(voiceObject.equals(app.getNowPlayingVoiceObject())){
					if(voicePlayerService.isPlaying()){
						voicePlayerService.pause();
						ImageView imageView = (ImageView)view.findViewById(R.id.title_list_item_imageView);
						imageView.setImageResource(R.drawable.pause);
					}else {
						voicePlayerService.start();
						ImageView imageView = (ImageView)view.findViewById(R.id.title_list_item_imageView);
						imageView.setImageResource(R.drawable.playing);						
					}
				}else{
					app.setNowPlayingVoiceObject(voiceObject);
					VoicePlayAsyncTask task = new VoicePlayAsyncTask(voicePlayerService,voiceObject);
					task.execute(position);
					ImageView imageView = (ImageView)view.findViewById(R.id.title_list_item_imageView);
					if(playingImageView!=null){
						playingImageView.setImageResource(R.drawable.ic_launcher);
					}
					imageView.setImageResource(R.drawable.playing);
					playingImageView = imageView;
					voiceObject.setCheckedCount(voiceObject.getCheckedCount()+1);
					voiceObject.getParseObject().saveInBackground();
				}
			}
        });
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
}
