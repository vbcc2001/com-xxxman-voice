package com.xxxman.voice.object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.parse.ParseObject;
/**
 * 声音对象的实体类
 * @author xxxman 20120729
 *
 */
@SuppressWarnings("rawtypes")
public class VoiceObject  implements Map<String,Object> {

	private ParseObject parseObject ;
	/**
	 * 默认方法：objectName为“VoiceObject”
	 * super("VoiceObject");
	 */
	public VoiceObject() {
		parseObject = new ParseObject("VoiceObject");
		this.setTitle("未知");
		this.setCheckedCount(0);
		this.setUri("https://www.google.com");
		this.setType("未知");
	}
	/**
	 * 默认方法：objectName为“VoiceObject”
	 * super("VoiceObject");
	 */
	public VoiceObject(ParseObject parseObject) {
		this.parseObject =parseObject ;
	}	
	/**
	 * 实现Map接口，清除VoiceObject内容
	 */
	public void clear() {
		for(String key:parseObject.keySet() ){
			parseObject.remove(key);
		}
	}
	/**
	 * 实现Map接口,判断是否包含key键
	 * 注意：该方法仅实现了key值为字符串的请求
	 */
	public boolean containsKey(String key) {
		return parseObject.containsKey(key);
	}

	/**
	 * 实现Map接口,判断是否包含value值
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
	 * 未实现该方法
	 */
	@SuppressWarnings("unchecked")
	public Set entrySet() {
		return null;
	}

	/**
	 * 实现Map接口,通过key键获得对象
	 * 注意：该方法仅实现了key值为字符串的请求
	 */
	public Object get(Object key) {
		return parseObject.get(key.toString());
	}
	/**
	 * 实现Map接口,是否为空值
	 */
	public boolean isEmpty() {
		return parseObject.keySet().size()==0;
	}
	/**
	 * 实现Map接口,返回值为null 
	 */
	public Object put(String key, Object value) {
		parseObject.put(key, value);
		return null;
	}
	/**
	 * 实现Map接口
	 */
	public void putAll( Map<? extends String ,? extends Object> map) {
		@SuppressWarnings("unchecked")
		Set<String> keySet = (Set<String>) map.keySet();
		for(String key:keySet){
			this.put(key, map.get(key));
		}
	}
	/**
	 * 实现Map接口,返回值为null 
	 */
	public Object remove(Object key) {
		parseObject.remove(key.toString());
		return null;
	}
	/**
	 * 实现Map接口,返回key的数量 
	 */
	public int size() {
		return parseObject.keySet().size();
	}
	/**
	 * 实现Map接口,有重复值
	 */
	public ArrayList<Object> values() {
		ArrayList<Object> values = new  ArrayList<Object>();
		for(String key:parseObject.keySet()){
			values.add(parseObject.get(key));
		}
		return values;
	}
	/**
	 * 实现Map接口
	 */
	public boolean containsKey(Object key) {
		return parseObject.containsKey(key.toString());
	}
	/**
	 * 实现Map接口
	 */
	public Set<String> keySet() {
		return parseObject.keySet();
	}
	/**
	 * 连接地址
	 * @return 连接地址
	 */
	public String getUri() {
		return parseObject.getString("uri");
	}
	/**
	 * 连接地址
	 */
	public void setUri(String uri) {
		parseObject.put("uri", uri);
	}
	/**
	 * 标题
	 * @return 标题
	 */
	public String getTitle() {
		return parseObject.getString("title");
	}
	/**
	 * 标题
	 */
	public void setTitle(String title) {
		parseObject.put("title", title);
	}
	/**
	 * 点击量
	 * @return 点击量
	 */
	public int getCheckedCount() {
		return parseObject.getInt("checkedCount");
	}
	/**
	 * 点击量
	 */
	public void setCheckedCount(int checkedCount) {
		parseObject.put("checkedCount", checkedCount);
	}
	/**
	 * 分类
	 * @return
	 */
	public String getType() {
		return parseObject.getString("type");
	}
	/**
	 * 分类
	 * @return
	 */
	public void setType(String type) {
		parseObject.put("type", type);
	}
	/**
	 * 创建日期
	 * @return
	 */
	public Date getCreateDate() {
		return parseObject.getCreatedAt();
	}
	/**
	 * 创建日期 yyyy-MM-dd
	 * @return
	 */
	public String getCreateDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		return sdf.format(parseObject.getCreatedAt());
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
