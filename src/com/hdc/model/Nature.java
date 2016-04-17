package com.hdc.model;

import com.hdc.util.Constant;
/**
 * 社团性质表
 * @author chang
 *
 */
public class Nature {
	private String tableName=Constant.NATURE_TABLE_NAME;
	
	private Integer id;
	private String name;
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
	
	
}
