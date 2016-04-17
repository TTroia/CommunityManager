package com.hdc.model;

import java.util.Date;

import com.hdc.util.Constant;
/**
 * 社团登记表
 * 默认社长人数为一个
 * 经费根据财务表实时更新
 * @author chang
 *
 */
public class Community {
	private String tableName=Constant.COMMUNITY_TABLE_NAME;
	
	private Integer id;
	private String name;
	private String createUser;
	private Date createDate;
	private Integer nature;  //性质
	private Integer num;  //当前人数
	private String president;  //社长
	private String pre_phone;  //社长电话
	private String teacher;  //指导教师
	private String partof;  //隶属于
	private String honor; //所获荣誉
	private Double funds;  //实时经费
	private Integer his_num;  //历史总人数
	private String description;  //描述
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getNature() {
		return nature;
	}
	public void setNature(Integer nature) {
		this.nature = nature;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getPresident() {
		return president;
	}
	public void setPresident(String president) {
		this.president = president;
	}
	public String getPre_phone() {
		return pre_phone;
	}
	public void setPre_phone(String pre_phone) {
		this.pre_phone = pre_phone;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getPartof() {
		return partof;
	}
	public void setPartof(String partof) {
		this.partof = partof;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	public Double getFunds() {
		return funds;
	}
	public void setFunds(Double funds) {
		this.funds = funds;
	}
	public Integer getHis_num() {
		return his_num;
	}
	public void setHis_num(Integer his_num) {
		this.his_num = his_num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
