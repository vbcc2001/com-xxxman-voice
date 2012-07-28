package com.xxxman.voice.object;

import java.util.Date;
import java.util.HashMap;

import com.parse.ParseObject;

public class VoiceObject extends HashMap<String,String> {
	
	
	
	private HashMap<String,String> voiceObject = new HashMap<String,String>(); 
	

	
	//连接地址
	private String uri;
	//标题
	private String title;
	//点击量
	private int checkedCount;
	//分类
	private String type;
	//创建日期
	private Date CreateDate;
	
	/**
	 * 连接地址
	 * @return
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * 连接地址
	 * @return
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * 标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 标题
	 * @return
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 点击量
	 * @return
	 */
	public int getCheckedCount() {
		return checkedCount;
	}
	/**
	 * 点击量
	 * @return
	 */
	public void setCheckedCount(int checkedCount) {
		this.checkedCount = checkedCount;
	}
	/**
	 * 分类
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 分类
	 * @return
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 创建日期
	 * @return
	 */
	public Date getCreateDate() {
		return CreateDate;
	}
	/**
	 * 创建日期
	 * @return
	 */
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public void setParseObject(ParseObject voiceObject) {
		uri = ParseObject.
	}
}
