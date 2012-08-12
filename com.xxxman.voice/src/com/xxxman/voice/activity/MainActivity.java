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
 * ������
 * @author kkkaiser 20120724
 *
 */
public class MainActivity extends Activity  {
	//�б���ͼ
    private ListView listView=null;
    //�б���ͼ������
    private ListViewAdapter adapter = null;
    //listView�����ݶ���List
    private List<VoiceObject> voiceObjectlist = null;
    //��������������
    private VoicePlayerService voicePlayerService = null;
    //����ͼ����������������ͨѶʵ����
    private ParseApplication app = null ;
    //��ǰ���ŵ�Item��imageView
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
     * ��������ͼ����
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //���ô�������:�Զ����������
        final boolean isCustom = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        //�Զ��������
        Window window = getWindow();
        if(isCustom){
        	window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlebar);
        }
        app = (ParseApplication) getApplication();
        //�������ͼ�ϵ��б���ͼ
        listView = (ListView) findViewById(R.id.listView);
        /*
         * �����б���ͼÿ�еĵ�������:
         * 1 �����,����������ŵ�ȫ�ֲ��������,
         * 2 �趨Ϊ��ǰ���ű������Ϊ������������,
         * 3 ͨ���첽���񲥷���������
         */     
        listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				VoiceObject voiceObject = voiceObjectlist.get(position);
				app.getVoiceObjectList().add(voiceObject);
				//�������ǵ�ǰ���ŵ���������ʱ��
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
     * ���ݳ�ʼ��
     */
    public void initListView(){
    	//��ȡ����������
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
     * �����������ʼ��,����
     */
    public void initService(){
	    Intent intent = new Intent(this,VoicePlayerService.class);
	    this.startService(intent);
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }   
    /**
     * ��������䵽��ͼ��
     * @param object
     */
    public void setData(List<VoiceObject> voiceObjectlist){
    	if(voiceObjectlist!=null && voiceObjectlist.size()>0){
    		adapter = new ListViewAdapter(this,voiceObjectlist);
	        listView.setAdapter(adapter);
    	}
    }
    /**
     * �������ò˵�
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }  
}
