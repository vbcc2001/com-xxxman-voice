package com.xxxman.voice.object;


import com.parse.ParseObject;
/**
 * ������������ʵ����
 * @author xxxman 20120805
 *
 */
public class VoiceTypeObject  {

	private ParseObject parseObject ;
	/**
	 * Ĭ�Ϸ�����
	 * parseObject ����Ϊ new ParseObject("VoiceTypeObject");
	 * 
	 */
	public VoiceTypeObject() {
		parseObject = new ParseObject("VoiceTypeObject");
	}
	/**
	 * ������������
	 * @param parseObject
	 */
	public VoiceTypeObject(ParseObject parseObject) {
		this.parseObject = parseObject;
	}	
	/**
	 * paresObject ת��Ϊ voiceObject
	 * @param parseObject
	 */
	public void setParseObject(ParseObject parseObject){
		this.parseObject = parseObject;
	}
	/**
	 * voiceObject ת��Ϊ  paresObject 
	 * @param 
	 * @return  ParseObject
	 */
	public ParseObject getParseObject(){
		return parseObject;
	}	
}
