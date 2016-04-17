package com.hdc.model;

import java.util.Date;

import com.hdc.util.Constant;

public class Role {
	
	private String tableName = Constant.ROLE_TABLE_NAME;
	private String name;
	private Integer roleId ;
	private Date createDate;
	private String userId;
	private String moduleIds;//权限集
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
	
	
	
}
