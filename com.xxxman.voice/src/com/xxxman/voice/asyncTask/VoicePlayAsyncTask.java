package com.xxxman.voice.asyncTask;

import com.xxxman.voice.object.VoiceObject;
import com.xxxman.voice.parse.ParseApplication;
import com.xxxman.voice.service.VoicePlayerService;

import android.os.AsyncTask;
/**
 * 播放声音的异步任务
 * @author kkkaiser 20120803
 *
 */
public class VoicePlayAsyncTask extends AsyncTask<Integer, Integer, String> {
	
	private ParseApplication app;
	private VoicePlayerService voicePlayerService ;
	private VoiceObject voiceObject ;
	public VoicePlayAsyncTask(VoicePlayerService voicePlayerService, VoiceObject voiceObject ){
		super();
		this.voiceObject=voiceObject;
		this.voicePlayerService=voicePlayerService;
		
	}
    @Override  
    protected void onPreExecute() {  
        super.onPreExecute();  
    } 
	@Override
	protected String doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		//int position = params[0];
		voicePlayerService.newPlay(voiceObject);
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