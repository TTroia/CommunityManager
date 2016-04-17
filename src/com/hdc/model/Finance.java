package com.hdc.model;

import java.util.Date;

import com.hdc.util.Constant;
/**
 * 社团财务表
 * 所有社团财务信息存储在一张表中，通过所属社团id进行分类
 * 每次产生的消费记录必须记录在案，
 * 目前由于技术限制，每次填写消费记录时，剩余金额由记录人手动计算，后期加入自动计算功能
 * @author chang
 *
 */
public class Finance {
	private String tableName=Constant.FINANCE_TABLE_NAME;
	
	private Integer id;
	private Integer com_id;
	private String com_name;
	private String editUser;
	private Date editDate;
	private String purpose;  //用途
	private Double spend;  //本次花费
	private Double balance;  //余额
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
	public Integer getCom_id() {
		return com_id;
	}
	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Double getSpend() {
		return spend;
	}
	public void setSpend(Double spend) {
		this.spend = spend;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
