package com.hdc.service;

import java.util.List;
import java.util.Map;

import com.hdc.dao.BaseDao;
import com.hdc.page.Page;

public class BaseServiceImpl implements BaseService {
	
	private BaseDao baseDao;
	

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int insert(Object obj) {
		// TODO Auto-generated method stub
		return this.baseDao.insert(obj);
	}

	@Override
	public int update(Object obj) {
		// TODO Auto-generated method stub
		return this.baseDao.update(obj);
	}

	@Override
	public int delete(Object obj) {
		// TODO Auto-generated method stub
		return this.baseDao.delete(obj);
	}

	@Override
	public Object getObjectById(Object obj) {
		// TODO Auto-generated method stub
		return this.baseDao.getObjectById(obj);
	}

	@Override
	public List getObjectList(Object obj,Page page) {
		// TODO Auto-generated method stub
		return this.baseDao.getObjectList(obj,page);
	}

	@Override
	public List getObjectListByOrderByPro(Object obj, Page page, String orderBy) {
		// TODO Auto-generated method stub
		return this.baseDao.getObjectListByOrderByPro(obj, page, orderBy);
	}
	
	@Override
	public List getObjectListByOrderByPro(Object obj, Page page, String orderBy,String desc) {
		// TODO Auto-generated method stub
		return this.baseDao.getObjectListByOrderByPro(obj, page, orderBy,desc);
	}

	@Override
	public int updateByMap(String tableName, Map<String,Object> updateMap,Map<String,Object> wherMap){
		// TODO Auto-generated method stub
		return this.baseDao.updateByMap(tableName, updateMap, wherMap);
	}

	//清空表
	@Override
	public int deleteAll(Object obj) {
		// TODO Auto-generated method stub
		return this.baseDao.deleteAll(obj);
	}

}
