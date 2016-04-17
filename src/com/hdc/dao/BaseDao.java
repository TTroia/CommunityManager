package com.hdc.dao;

import java.util.List;
import java.util.Map;

import com.hdc.page.Page;


public interface BaseDao {
	
	public int insert(Object obj);
	
	public int update(Object obj);
	
	public int updateByMap(String tableName, Map<String,Object> updateMap,Map<String,Object> wherMap);
	
	public int delete(Object obj );
	
	public int deleteAll(Object obj);

	public Object getObjectById(Object obj);
	
	public List getObjectList(Object obj,Page page);
	
	public List getObjectListByOrderByPro(Object obj,Page page,String orderBy);
	
	//降序
	public List getObjectListByOrderByPro(Object obj,Page page,String orderBy,String desc);
}
