package com.xxxman.voice.object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.parse.ParseObject;
import com.parse.ParseUser;
/**
 * 用户对象的实体类
 * @author xxxman 20120802
 *
 */
@SuppressWarnings("rawtypes")
public class UserObject  implements Map<String,Object> {

	private ParseUser parseObject ;
	/**
	 * 默认构造方法：
	 * super("VoiceObject");
	 */
	public UserObject() {
		parseObject = new ParseUser();
	}
	/**
	 * 通过ParseUser构造方法：
	 * super("VoiceObject");
	 */
	public UserObject(ParseUser parseObject) {
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
	 * 用户名
	 * @return 用户名
	 */
	public String getUserName() {
		return parseObject.getUsername();
	}
	/**
	 * 用户名
	 */
	public void setUserName(String userName) {
		parseObject.put("uri", userName);
	}
	/**
	 * 密码
	 */
	public void setPassword(String password) {
		parseObject.setPassword(password);
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
	 * paresObject 转换为 UserObject
	 * @param parseObject
	 */
	public void setParseObject(ParseUser parseObject){
		this.parseObject = parseObject;
	}
	/**
	 * UserObject 转换为  paresObject 
	 * @param 
	 * @return  ParseObject
	 */
	public ParseObject getParseObject(){
		return parseObject;
	}	
}
