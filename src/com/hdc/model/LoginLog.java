package com.hdc.model;

import java.util.Date;

import com.hdc.util.Constant;

public class LoginLog {
	private String tableName = Constant.LOGIN_LOG;
	private Integer id;
	private Date createDate;
	private String wangwangId;
	private String contact;
	private String ip;
	private String loginAddress;
	
	private String startDate;
	private String endDate;
	
	//--辅助字段
	private String realAddress;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getWangwangId() {
		return wangwangId;
	}
	public void setWangwangId(String wangwangId) {
		this.wangwangId = wangwangId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLoginAddress() {
		return loginAddress;
	}
	public void setLoginAddress(String loginAddress) {
		this.loginAddress = loginAddress;
	}
	public String getRealAddress() {
		return realAddress;
	}
	public void setRealAddress(String realAddress) {
		this.realAddress = realAddress;
	}
	
}
