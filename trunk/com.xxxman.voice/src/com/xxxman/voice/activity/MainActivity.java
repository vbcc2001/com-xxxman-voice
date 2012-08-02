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
        setContentView(R.layout.activity_main);
        //�������ͼ�ϵ��б���ͼ
        listView = (ListView) findViewById(R.id.listView);
        /*
         * �����б���ͼÿ�еĵ�������:
         * 1 �����,����������ŵ�ȫ�ֲ��������,
         * 2 �趨Ϊ��ǰ���ű������Ϊ������������,
         * 3 ������������
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
        vo.setTitle("���������"+new Date().getTime());
//        String url = "http://excerpts.contentreserve.com/FormatType-425/0017-1/172247-Sail.mp3";
//        vo.setUri(url);
//        vo.setTitle("sail"+new Date().getTime());        
        vo.setType("����");
        vo.getParseObject().saveInBackground();
        
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
			publishProgress(4); //ִ�й����н���
    		return null;
    	}
        @Override  
        protected void onProgressUpdate(Integer... progress) {  
            //���������doInBackground����publishProgressʱ��������Ȼ����ʱֻ��һ������   
            super.onProgressUpdate(progress);  
        }
        @Override  
        protected void onPostExecute(String result) {  
            //doInBackground����ʱ���������仰˵������doInBackgroundִ����󴥷�  
            //�����result��������doInBackgroundִ�к�ķ���ֵ������������"ִ�����"   
            super.onPostExecute(result);
        }
    }     
}
