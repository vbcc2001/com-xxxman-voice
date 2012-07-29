package com.xxxman.voice.object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.parse.ParseObject;
/**
 * ���������ʵ����
 * @author xxxman 20120729
 *
 */
@SuppressWarnings("rawtypes")
public class VoiceObject  implements Map<String,Object> {

	private ParseObject parseObject ;
	/**
	 * Ĭ�Ϸ�����objectNameΪ��VoiceObject��
	 * super("VoiceObject");
	 */
	public VoiceObject() {
		parseObject = new ParseObject("VoiceObject");
		this.setTitle("δ֪");
		this.setCheckedCount(0);
		this.setUri("https://www.google.com");
		this.setType("δ֪");
	}
	/**
	 * Ĭ�Ϸ�����objectNameΪ��VoiceObject��
	 * super("VoiceObject");
	 */
	public VoiceObject(ParseObject parseObject) {
		this.parseObject =parseObject ;
	}	
	/**
	 * ʵ��Map�ӿڣ����VoiceObject����
	 */
	public void clear() {
		for(String key:parseObject.keySet() ){
			parseObject.remove(key);
		}
	}
	/**
	 * ʵ��Map�ӿ�,�ж��Ƿ����key��
	 * ע�⣺�÷�����ʵ����keyֵΪ�ַ���������
	 */
	public boolean containsKey(String key) {
		return parseObject.containsKey(key);
	}

	/**
	 * ʵ��Map�ӿ�,�ж��Ƿ����valueֵ
	 */
	public boolean containsValue(Object value) {
		boolean flag =false;
		if(value!=null){
			for(String key:this.keySet() ){
				if(value.equals(this.get(key))){
					flag =  true;
					break;
				}
			}
		}
		return flag;
	}
	/**
	 * δʵ�ָ÷���
	 */
	@SuppressWarnings("unchecked")
	public Set entrySet() {
		return null;
	}

	/**
	 * ʵ��Map�ӿ�,ͨ��key����ö���
	 * ע�⣺�÷�����ʵ����keyֵΪ�ַ���������
	 */
	public Object get(Object key) {
		return parseObject.get(key.toString());
	}
	/**
	 * ʵ��Map�ӿ�,�Ƿ�Ϊ��ֵ
	 */
	public boolean isEmpty() {
		return parseObject.keySet().size()==0;
	}
	/**
	 * ʵ��Map�ӿ�,����ֵΪnull 
	 */
	public Object put(String key, Object value) {
		parseObject.put(key, value);
		return null;
	}
	/**
	 * ʵ��Map�ӿ�
	 */
	public void putAll( Map<? extends String ,? extends Object> map) {
		@SuppressWarnings("unchecked")
		Set<String> keySet = (Set<String>) map.keySet();
		for(String key:keySet){
			this.put(key, map.get(key));
		}
	}
	/**
	 * ʵ��Map�ӿ�,����ֵΪnull 
	 */
	public Object remove(Object key) {
		parseObject.remove(key.toString());
		return null;
	}
	/**
	 * ʵ��Map�ӿ�,����key������ 
	 */
	public int size() {
		return parseObject.keySet().size();
	}
	/**
	 * ʵ��Map�ӿ�,���ظ�ֵ
	 */
	public ArrayList<Object> values() {
		ArrayList<Object> values = new  ArrayList<Object>();
		for(String key:parseObject.keySet()){
			values.add(parseObject.get(key));
		}
		return values;
	}
	/**
	 * ʵ��Map�ӿ�
	 */
	public boolean containsKey(Object key) {
		return parseObject.containsKey(key.toString());
	}
	/**
	 * ʵ��Map�ӿ�
	 */
	public Set<String> keySet() {
		return parseObject.keySet();
	}
	/**
	 * ���ӵ�ַ
	 * @return ���ӵ�ַ
	 */
	public String getUri() {
		return parseObject.getString("uri");
	}
	/**
	 * ���ӵ�ַ
	 */
	public void setUri(String uri) {
		parseObject.put("uri", uri);
	}
	/**
	 * ����
	 * @return ����
	 */
	public String getTitle() {
		return parseObject.getString("title");
	}
	/**
	 * ����
	 */
	public void setTitle(String title) {
		parseObject.put("title", title);
	}
	/**
	 * �����
	 * @return �����
	 */
	public int getCheckedCount() {
		return parseObject.getInt("checkedCount");
	}
	/**
	 * �����
	 */
	public void setCheckedCount(int checkedCount) {
		parseObject.put("checkedCount", checkedCount);
	}
	/**
	 * ����
	 * @return
	 */
	public String getType() {
		return parseObject.getString("type");
	}
	/**
	 * ����
	 * @return
	 */
	public void setType(String type) {
		parseObject.put("type", type);
	}
	/**
	 * ��������
	 * @return
	 */
	public Date getCreateDate() {
		return parseObject.getCreatedAt();
	}
	/**
	 * �������� yyyy-MM-dd
	 * @return
	 */
	public String getCreateDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		return sdf.format(parseObject.getCreatedAt());
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
