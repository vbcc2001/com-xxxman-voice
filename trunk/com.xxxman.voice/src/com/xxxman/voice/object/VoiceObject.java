package com.xxxman.voice.object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.parse.ParseObject;
/**
 * 声音对象的实体类
 * @author xxxman 20120729
 *
 */
@SuppressWarnings("rawtypes")
public class VoiceObject extends ParseObject implements Map {

	/**
	 * 通过objectName获得父类：ParseObject对象
	 * @param objectName
	 */
	public VoiceObject(String objectName) {
		super(objectName);
	}
	/**
	 * 默认方法：objectName为“VoiceObject”
	 * super("VoiceObject");
	 */
	public VoiceObject() {
		super("VoiceObject");
		this.setTitle("未知");
		this.setCheckedCount(0);
		this.setUri("https://www.google.com");
		this.setType("未知");
	}
	/**
	 * 实现Map接口，清除VoiceObject内容
	 */
	public void clear() {
		for(String key:this.keySet() ){
			super.remove(key);
		}
	}

	/**
	 * 实现Map接口,判断是否包含key键
	 * 注意：该方法仅实现了key值为字符串的请求
	 */
	public boolean containsKey(Object key) {
		return this.containsKey(key);
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
	public Set entrySet() {
		return null;
	}

	/**
	 * 实现Map接口,通过key键获得对象
	 * 注意：该方法仅实现了key值为字符串的请求
	 */
	public Object get(Object key) {
		return super.get(key.toString());
	}
	/**
	 * 实现Map接口,是否为空值
	 */
	public boolean isEmpty() {
		return super.keySet().size()==0;
	}

	/**
	 * 实现Map接口,返回值为null 
	 */
	public Object put(Object key, Object value) {
		super.put(key.toString(), value);
		return null;
	}
	/**
	 * 实现Map接口
	 */
	public void putAll( Map map) {
		Set keySet = map.keySet();
		for(Object key:keySet){
			this.put(key, map.get(key));
		}
	}
	
	/**
	 * 实现Map接口,返回值为null 
	 */
	public Object remove(Object key) {
		super.remove(key.toString());
		return null;
	}
	/**
	 * 实现Map接口,返回key的数量 
	 */
	public int size() {
		return super.keySet().size();
	}
	/**
	 * 实现Map接口,有重复值
	 */
	public ArrayList<Object> values() {
		ArrayList<Object> values = new  ArrayList<Object>();
		for(String key:super.keySet()){
			values.add(super.get(key));
		}
		return values;
	}
	
	/**
	 * 连接地址
	 * @return 连接地址
	 */
	public String getUri() {
		return super.getString("uri");
	}
	/**
	 * 连接地址
	 */
	public void setUri(String uri) {
		super.add("uri", uri);
	}
	/**
	 * 标题
	 * @return 标题
	 */
	public String getTitle() {
		return super.getString("title");
	}
	/**
	 * 标题
	 */
	public void setTitle(String title) {
		super.add("title", title);
	}
	/**
	 * 点击量
	 * @return 点击量
	 */
	public int getCheckedCount() {
		return super.getInt("checkedCount");
	}
	/**
	 * 点击量
	 */
	public void setCheckedCount(int checkedCount) {
		super.add("checkedCount", checkedCount);
	}
	/**
	 * 分类
	 * @return
	 */
	public String getType() {
		return super.getString("type");
	}
	/**
	 * 分类
	 * @return
	 */
	public void setType(String type) {
		super.add("type", type);
	}
	/**
	 * 创建日期
	 * @return
	 */
	public Date getCreateDate() {
		return super.getCreatedAt();
	}
	/**
	 * 创建日期 yyyy-MM-dd
	 * @return
	 */
	public String getCreateDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		if(super.getCreatedAt()!=null){
		return sdf.format(super.getCreatedAt());
		}else{
			return "0000-00-00";
		}
		
	}
	/**
	 * paresObject 转换为 voiceObject
	 * @param parseObject
	 * @return  声音对象
	 */
	static public VoiceObject getVoiceObject(ParseObject parseObject){
		VoiceObject vo = new VoiceObject();
		for(String key:parseObject.keySet()){
			//System.out.println("key:"+key);
			//System.out.println("parseObject.get(key):"+parseObject.get(key));
			if(!"ACL".equals(key)){
			vo.add(key,parseObject.get(key) );}
		}
		return vo;
	}
	/**
	 * paresObject 转换为 voiceObject
	 * @param list
	 * @return  声音对象list
	 */
	static public List<VoiceObject> getVoiceObject(List<ParseObject> list){
		List<VoiceObject> voList = new ArrayList<VoiceObject>();
		for (ParseObject object : list){
			voList.add(getVoiceObject(object));
		}
		return voList;
	}
	
}
