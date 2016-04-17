package com.hdc.model;

import java.util.Date;
import java.util.List;

import com.hdc.util.Constant;

public class Module implements Comparable{

	private String tableName = Constant.MODULE_TABLE_NAME;
	
	private Integer id ;
	private String name;
	private String url;
	private Date createDate;
	private String userId;
	private Integer level;//级别 1 第一级菜单 
	private Integer orderBy;//排序
	private Integer partentId;// 0是代表的是顶级菜单
	private String partentName;
	private List<Module> secondList;
	
	public List<Module> getSecondList() {
		return secondList;
	}
	public void setSecondList(List<Module> secondList) {
		this.secondList = secondList;
	}
	public String getPartentName() {
		return partentName;
	}
	public void setPartentName(String partentName) {
		this.partentName = partentName;
	}
	public Integer getPartentId() {
		return partentId;
	}
	public void setPartentId(Integer partentId) {
		this.partentId = partentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	@Override
	public int compareTo(Object o) {
		 if (o instanceof Module) {
			 Module target  = (Module) o;
			   if(orderBy<target.getOrderBy()) {
				   return -1;
			   }else if(orderBy>target.getOrderBy()) {
				   return 1;
			   }else{
				   return 0;
			   }
			 } 
	 	else {
		  //非TestComparable对象与之比较,则抛出异常
		  throw new ClassCastException("Can't compare");
		 }
	}
	

}
