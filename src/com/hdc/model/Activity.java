package com.hdc.model;

import java.util.Date;

import com.hdc.util.Constant;
/**
 * 活动申请表
 * 可上传word版申请表
 * @author chang
 *
 */

public class Activity {
	private String tableName=Constant.ACTIVITY_TABLE_NAME;
	
	private Integer id;
	private String name;   //活动
	private String com_name;  //所属社团名称
	private Integer com_id;  //所属社团id
	private String partof;  //所属系
	private String applyUser;  //申请人 一般是社长
	private Date applyDate;		//申请时间
	private String location;  //举办地点
	private Date holdDate;  //举办时间
	private Integer applyState;  //申请状态 系
	private Integer applyState2;  //申请状态 校
	private String teacher;		//指导教师
	private String approveUser;  //审批人  系社联
	private Date approveDate;  //系审批时间
	private String approveUser2;  //校审批人
	private Date approveDate2;  //校审批时间
	private Integer completeState;  //活动完成状态
	private String tableAdd;  //上传word的地址
	private String attr; //审批 不准理由
	
	
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getPartof() {
		return partof;
	}
	public void setPartof(String partof) {
		this.partof = partof;
	}
	public Date getHoldDate() {
		return holdDate;
	}
	public void setHoldDate(Date holdDate) {
		this.holdDate = holdDate;
	}
	public Integer getApplyState2() {
		return applyState2;
	}
	public void setApplyState2(Integer applyState2) {
		this.applyState2 = applyState2;
	}
	public String getApproveUser2() {
		return approveUser2;
	}
	public void setApproveUser2(String approveUser2) {
		this.approveUser2 = approveUser2;
	}
	public Date getApproveDate2() {
		return approveDate2;
	}
	public void setApproveDate2(Date approveDate2) {
		this.approveDate2 = approveDate2;
	}
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
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public Integer getCom_id() {
		return com_id;
	}
	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getApplyState() {
		return applyState;
	}
	public void setApplyState(Integer applyState) {
		this.applyState = applyState;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getApproveUser() {
		return approveUser;
	}
	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public Integer getCompleteState() {
		return completeState;
	}
	public void setCompleteState(Integer completeState) {
		this.completeState = completeState;
	}
	public String getTableAdd() {
		return tableAdd;
	}
	public void setTableAdd(String tableAdd) {
		this.tableAdd = tableAdd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
