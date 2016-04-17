package com.hdc.model;

import java.util.Date;

import com.hdc.util.Constant;

/**
 * 站内信表
 * 校社联只能发送给各个系社联，
 * 系社联能发送给校社联和下属社团
 * 社团只能发送给本系社联
 * @author chang
 *
 */

public class Notice {
	private String tableName = Constant.NOTICE_TABLE_NAME;
	
	private Integer id; 
	private String fromUser;
	private String toUser;
	private Date sendDate;
	private String title;
	private String content;
	private Integer isRead;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	
	
}
