package com.xxxman.voice.object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.parse.ParseObject;
/**
 * ���������ʵ����
 * @author xxxman 20120729
 *
 */
@SuppressWarnings("rawtypes")
public class VoiceObject extends ParseObject implements Map {

	/**
	 * ͨ��objectName��ø��ࣺParseObject����
	 * @param objectName
	 */
	public VoiceObject(String objectName) {
		super(objectName);
	}
	/**
	 * Ĭ�Ϸ�����objectNameΪ��VoiceObject��
	 * super("VoiceObject");
	 */
	public VoiceObject() {
		super("VoiceObject");
		this.setTitle("δ֪");
		this.setCheckedCount(0);
		this.setUri("https://www.google.com");
		this.setType("δ֪");
	}
	/**
	 * ʵ��Map�ӿڣ����VoiceObject����
	 */
	public void clear() {
		for(String key:this.keySet() ){
			super.remove(key);
		}
	}

	/**
	 * ʵ��Map�ӿ�,�ж��Ƿ����key��
	 * ע�⣺�÷�����ʵ����keyֵΪ�ַ���������
	 */
	public boolean containsKey(Object key) {
		return this.containsKey(key);
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
	public Set entrySet() {
		return null;
	}

	/**
	 * ʵ��Map�ӿ�,ͨ��key����ö���
	 * ע�⣺�÷�����ʵ����keyֵΪ�ַ���������
	 */
	public Object get(Object key) {
		return super.get(key.toString());
	}
	/**
	 * ʵ��Map�ӿ�,�Ƿ�Ϊ��ֵ
	 */
	public boolean isEmpty() {
		return super.keySet().size()==0;
	}

	/**
	 * ʵ��Map�ӿ�,����ֵΪnull 
	 */
	public Object put(Object key, Object value) {
		super.put(key.toString(), value);
		return null;
	}
	/**
	 * ʵ��Map�ӿ�
	 */
	public void putAll( Map map) {
		Set keySet = map.keySet();
		for(Object key:keySet){
			this.put(key, map.get(key));
		}
	}
	
	/**
	 * ʵ��Map�ӿ�,����ֵΪnull 
	 */
	public Object remove(Object key) {
		super.remove(key.toString());
		return null;
	}
	/**
	 * ʵ��Map�ӿ�,����key������ 
	 */
	public int size() {
		return super.keySet().size();
	}
	/**
	 * ʵ��Map�ӿ�,���ظ�ֵ
	 */
	public ArrayList<Object> values() {
		ArrayList<Object> values = new  ArrayList<Object>();
		for(String key:super.keySet()){
			values.add(super.get(key));
		}
		return values;
	}
	
	/**
	 * ���ӵ�ַ
	 * @return ���ӵ�ַ
	 */
	public String getUri() {
		return super.getString("uri");
	}
	/**
	 * ���ӵ�ַ
	 */
	public void setUri(String uri) {
		super.add("uri", uri);
	}
	/**
	 * ����
	 * @return ����
	 */
	public String getTitle() {
		return super.getString("title");
	}
	/**
	 * ����
	 */
	public void setTitle(String title) {
		super.add("title", title);
	}
	/**
	 * �����
	 * @return �����
	 */
	public int getCheckedCount() {
		return super.getInt("checkedCount");
	}
	/**
	 * �����
	 */
	public void setCheckedCount(int checkedCount) {
		super.add("checkedCount", checkedCount);
	}
	/**
	 * ����
	 * @return
	 */
	public String getType() {
		return super.getString("type");
	}
	/**
	 * ����
	 * @return
	 */
	public void setType(String type) {
		super.add("type", type);
	}
	/**
	 * ��������
	 * @return
	 */
	public Date getCreateDate() {
		return super.getCreatedAt();
	}
	/**
	 * �������� yyyy-MM-dd
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
	 * paresObject ת��Ϊ voiceObject
	 * @param parseObject
	 * @return  ��������
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
	 * paresObject ת��Ϊ voiceObject
	 * @param list
	 * @return  ��������list
	 */
	static public List<VoiceObject> getVoiceObject(List<ParseObject> list){
		List<VoiceObject> voList = new ArrayList<VoiceObject>();
		for (ParseObject object : list){
			voList.add(getVoiceObject(object));
		}
		return voList;
	}
	
}
