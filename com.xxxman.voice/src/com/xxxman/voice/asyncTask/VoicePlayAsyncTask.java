package com.xxxman.voice.asyncTask;

import com.xxxman.voice.object.VoiceObject;
import com.xxxman.voice.parse.ParseApplication;
import com.xxxman.voice.service.VoicePlayerService;

import android.os.AsyncTask;
/**
 * �����������첽����
 * @author kkkaiser 20120803
 *
 */
public class VoicePlayAsyncTask extends AsyncTask<Integer, Integer, String> {
	
	private ParseApplication app;
	private VoicePlayerService voicePlayerService ;
	private VoiceObject voiceObject ;
	
    @Override  
    protected void onPreExecute() {  
        super.onPreExecute();  
    } 
	@Override
	protected String doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		int position = params[0];
//		ParseApplication app =(ParseApplication) getApplication();
		app.getVoiceObjectList().add(voiceObject);
		app.setCurrentNumber(app.getVoiceObjectList().size());
		voicePlayerService.newPlay(voiceObject);
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