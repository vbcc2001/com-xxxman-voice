package com.xxxman.voice.object;

import java.util.ArrayList;
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
	@SuppressWarnings("rawtypes")
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
}
