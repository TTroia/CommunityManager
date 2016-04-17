package com.hdc.model;

import java.util.Date;

import com.hdc.util.Constant;

public class User {
	
	private String tableName = Constant.USER_TABLE_NAME;
	
	private String userId;

	private String  username ;
	private  String pwd;
	private Integer roleId;//所属角色
	private Date  createDate;
	private Integer status;//用户状态  0 未禁用  1为启用
	private String roleName ;//
	private String partof;  //所属系
	private String moduleIds;
	private String qq;
	private String phone;
	private String attr;//供应商联系方式
	private Integer communityId;
	private String communityName;
	
	
	public String getPartof() {
		return partof;
	}
	public void setPartof(String partof) {
		this.partof = partof;
	}
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	
}
