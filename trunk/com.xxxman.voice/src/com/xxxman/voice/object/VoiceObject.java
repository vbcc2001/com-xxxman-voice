package com.xxxman.voice.object;

import java.util.Date;
import java.util.HashMap;

import com.parse.ParseObject;

public class VoiceObject extends HashMap<String,String> {
	
	
	
	private HashMap<String,String> voiceObject = new HashMap<String,String>(); 
	

	
	//���ӵ�ַ
	private String uri;
	//����
	private String title;
	//�����
	private int checkedCount;
	//����
	private String type;
	//��������
	private Date CreateDate;
	
	/**
	 * ���ӵ�ַ
	 * @return
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * ���ӵ�ַ
	 * @return
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * ����
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * ����
	 * @return
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * �����
	 * @return
	 */
	public int getCheckedCount() {
		return checkedCount;
	}
	/**
	 * �����
	 * @return
	 */
	public void setCheckedCount(int checkedCount) {
		this.checkedCount = checkedCount;
	}
	/**
	 * ����
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * ����
	 * @return
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * ��������
	 * @return
	 */
	public Date getCreateDate() {
		return CreateDate;
	}
	/**
	 * ��������
	 * @return
	 */
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public void setParseObject(ParseObject voiceObject) {
		uri = ParseObject.
	}
}
