package com.xxxman.voice.object;


import com.parse.ParseObject;
/**
 * 声音分类对象的实体类
 * @author xxxman 20120805
 *
 */
public class VoiceTypeObject  {

	private ParseObject parseObject ;
	/**
	 * 默认方法：
	 * parseObject 对象为 new ParseObject("VoiceTypeObject");
	 * 
	 */
	public VoiceTypeObject() {
		parseObject = new ParseObject("VoiceTypeObject");
	}
	/**
	 * 带参数方法：
	 * @param parseObject
	 */
	public VoiceTypeObject(ParseObject parseObject) {
		this.parseObject = parseObject;
	}	
	/**
	 * paresObject 转换为 voiceObject
	 * @param parseObject
	 */
	public void setParseObject(ParseObject parseObject){
		this.parseObject = parseObject;
	}
	/**
	 * voiceObject 转换为  paresObject 
	 * @param 
	 * @return  ParseObject
	 */
	public ParseObject getParseObject(){
		return parseObject;
	}	
}
