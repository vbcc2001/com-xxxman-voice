package com.xxxman.voice.helper;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseObject;
import com.xxxman.voice.object.VoiceObject;

public class VoiceObjectHelper {
	/**
	 * paresObject 转换为 voiceObject
	 * @param list
	 * @return  声音对象list
	 */
	static public List<VoiceObject> getVoiceObject(List<ParseObject> list){
		List<VoiceObject> voList = new ArrayList<VoiceObject>();
		for (ParseObject object : list){
			voList.add(new VoiceObject(object));
		}
		return voList;
	}
}
