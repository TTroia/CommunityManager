package com.hdc.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.hdc.model.Column;
import com.hdc.page.Page;
import com.hdc.util.BeanReflectionUtil;
import com.hdc.util.StringUtil;

public class BaseDaoImpl extends CommonDaoImpl implements BaseDao {
	
	protected static Logger log = Logger.getLogger(BaseDaoImpl.class);

	@Override
	public int insert(Object obj) {
		
		try {
			Object tableName = BeanReflectionUtil.getPrivatePropertyValue(obj, "tableName");
			StringBuffer property = new StringBuffer();
			StringBuffer value = new StringBuffer();
			List<Object> propertyValue = new ArrayList<Object>();
			List<Column>  list = getColumnList(tableName);
			
			for(Column vo : list ){
				// 主键
				if(vo.getPrikey()!=null && vo.getPrikey().trim().equals("PRI")){
					//有赋值的不是自动增长的
					Object o = BeanReflectionUtil.getPrivatePropertyValue(obj, vo.getColumnName());
					if(o!=null){
						property.append(",").append(vo.getColumnName());
						value.append(",").append("?");
						propertyValue.add(o);
					}
					continue;
				}
				// 不为null
				Object o = BeanReflectionUtil.getPrivatePropertyValue(obj, vo.getColumnName());
				if(o!=null){
					property.append(",").append(vo.getColumnName());
					value.append(",").append("?");
					propertyValue.add(o);
				}
			}
			
			String sql = "insert into "+tableName+ "("+property.toString().substring(1)+") values("+value.toString().substring(1)+")";
			log.info("insert: "+sql);
			log.info("vaule: "+StringUtil.getArrayValue(propertyValue.toArray()));
			
			return this.getJdbcTemplate().update(sql, propertyValue.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		return 0;
	}

	@Override
	public int update(Object obj) {
		
		try {
			Object tableName = BeanReflectionUtil.getPrivatePropertyValue(obj, "tableName");
			StringBuffer property = new StringBuffer();
			StringBuffer where = new StringBuffer();
			List<Object> propertyValue = new ArrayList<Object>();
			List<Column>  list = getColumnList(tableName);
			for(Column vo : list ){
				
				Object o = BeanReflectionUtil.getPrivatePropertyValue(obj, vo.getColumnName());
				
				if(vo.getPrikey()!=null && vo.getPrikey().trim().equals("PRI")){
					where.append(" and ");
					where.append(vo.getColumnName()+" = '").append(o).append("'");
//					continue;
				}
				
				else if(o!=null ){
					property.append(",").append(vo.getColumnName()).append("=?");
					propertyValue.add(o);
				}
			}
			
			String sql = "update "+tableName+ " set " +property.toString().substring(1) +" where "+ where.toString().substring(5);
			log.info("update: "+sql);
			log.info("vaule: "+StringUtil.getArrayValue(propertyValue.toArray()));
			
			return this.getJdbcTemplate().update(sql, propertyValue.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		return 0;
	}

	@Override
	public int delete(Object obj) {
		
		try {
			Object tableName = BeanReflectionUtil.getPrivatePropertyValue(obj, "tableName");
			StringBuffer where = new StringBuffer(" 1=1 ");
			List<Column>  list = getColumnList(tableName);
			for(Column vo : list ){
				if(vo.getPrikey()!=null && !vo.getPrikey().trim().equals("")){
					where.append(" and ").append( vo.getColumnName()+"='").append(BeanReflectionUtil.getPrivatePropertyValue(obj, vo.getColumnName())).append("'");
				}
			}
			
			String sql = "delete from  "+tableName+ " where "+ where.toString();
			log.info("delete: "+sql);
			return this.getJdbcTemplate().update(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		return 0;
	}

	@Override
	public Object getObjectById(Object obj) {
		
		Object temp  =  null;
		boolean isexist = false;
		try {
			
			temp = BeanReflectionUtil.newInstance(obj.getClass().getName());
			
			Object tableName = BeanReflectionUtil.getPrivatePropertyValue(obj, "tableName");
			StringBuffer where = new StringBuffer();
			List<Column>  list = getColumnList(tableName);
			for(Column vo : list ){
				if(vo.getPrikey()!=null && !vo.getPrikey().trim().equals("") && !vo.getPrikey().equals("UNI")){
					where.append(" and ").append( vo.getColumnName()+"='").append(BeanReflectionUtil.getPrivatePropertyValue(obj, vo.getColumnName())).append("'");
				}
			}
			
			String sql = "select *  from  "+tableName+ " where "+ where.toString().substring(4);
			log.info("getObjectById: "+sql);
			
			SqlRowSet result  =   this.getJdbcTemplate().queryForRowSet(sql);
			Field[] fields = BeanReflectionUtil.getBeanDeclaredFields(obj.getClass().getName());
			Map<String,String> map = new HashMap<String,String>();
			for(Column vo : list ){
				map.put(vo.getColumnName(), vo.getColumnName());
			}
			while(result.next()){
				isexist = true;
				for(Field field : fields){
					//表字段存在才有意义
					if(map.get(field.getName())!=null){
						field.setAccessible(true);
						field.set(temp, result.getObject(field.getName()));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		if(isexist){
			return temp;
		}
		return null;
	}

	@Override
	public List getObjectList(Object obj,Page page) {
		List<Object> list = new ArrayList<Object>();
		try { 
			Field[] fields = BeanReflectionUtil.getBeanDeclaredFields(obj.getClass().getName());
			Object tableName = BeanReflectionUtil.getPrivatePropertyValue(obj, "tableName");
			StringBuffer where = new StringBuffer();
			List<Object> propertyValue = new ArrayList<Object>();
			List<Column>  clist = getColumnList(tableName);
			for(Column vo : clist ){
				Object o = BeanReflectionUtil.getPrivatePropertyValue(obj, vo.getColumnName());
				if(o!=null && !o.toString().equals("")){
					where.append(" and ").append(vo.getColumnName()).append(" =?");
					propertyValue.add(o);
				}
			}
			String sql =  null;
			SqlRowSet result  = null;
			//带条件的查询
			if(propertyValue.size()>0){
				sql = "select *  from  "+tableName+ " where "+ where.toString().substring(4);
			}
			else{
				sql = "select *  from  "+tableName;
			}
//			 分页信息
			if(page !=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(*) from (");
				buffer.append(sql);
				buffer.append(") a");
				int pageTotal = this.getJdbcTemplate().queryForInt(buffer.toString(),propertyValue.toArray());
				page.setTotalRows(pageTotal);
				sql = sql+ " limit " + page.getOffset()+","+page.getLimit();
			}
			
			if(propertyValue.size()>0){
				result  =   this.getJdbcTemplate().queryForRowSet(sql,propertyValue.toArray());
				log.info("getObjectById: "+sql);
			}
			else{
				result  =   this.getJdbcTemplate().queryForRowSet(sql);
				log.info("getObjectById: "+sql);
			}
			
			Map<String,String> map = new HashMap<String,String>();
			for(Column vo : clist ){
				map.put(vo.getColumnName(), vo.getColumnName());
			}
			while(result.next()){
				Object temp = BeanReflectionUtil.newInstance(obj.getClass().getName());
				for(Field field : fields){
					
					if(map.get(field.getName())!=null){
						field.setAccessible(true);
						field.set(temp, result.getObject(field.getName()));
					}
				}
				list.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		
		if(page !=null){
			page.setResultList(list);
		}
		return list;
	}

	@Override
	public List getObjectListByOrderByPro(Object obj, Page page, String orderBy) {
		List<Object> list = new ArrayList<Object>();
		try { 
			Field[] fields = BeanReflectionUtil.getBeanDeclaredFields(obj.getClass().getName());
			Object tableName = BeanReflectionUtil.getPrivatePropertyValue(obj, "tableName");
			StringBuffer where = new StringBuffer();
			List<Object> propertyValue = new ArrayList<Object>();
			List<Column>  clist = getColumnList(tableName);
			for(Column vo : clist ){
				Object o = BeanReflectionUtil.getPrivatePropertyValue(obj, vo.getColumnName());
				if(o!=null){
					where.append(" and ").append(vo.getColumnName()).append(" =?");
					propertyValue.add(o);
				}
			}
			String sql =  null;
			SqlRowSet result  = null;
			//带条件的查询
			if(propertyValue.size()>0){
				sql = "select *  from  "+tableName+ " where "+ where.toString().substring(4);
			}
			else{
				sql = "select *  from  "+tableName;
			}
			if(orderBy!=null && orderBy.length()>0){
				sql = sql +" order by "+ orderBy;
			}
//			 分页信息
			if(page !=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(*) from (");
				buffer.append(sql);
				buffer.append(") a");
				int pageTotal = this.getJdbcTemplate().queryForInt(buffer.toString(),propertyValue.toArray());
				page.setTotalRows(pageTotal);
				sql = sql+ " limit " + page.getOffset()+","+page.getLimit();
			}
			
			if(propertyValue.size()>0){
				result  =   this.getJdbcTemplate().queryForRowSet(sql,propertyValue.toArray());
				log.info("getObjectById: "+sql);
			}
			else{
				result  =   this.getJdbcTemplate().queryForRowSet(sql);
				log.info("getObjectById: "+sql);
			}
			
			Map<String,String> map = new HashMap<String,String>();
			for(Column vo : clist ){
				map.put(vo.getColumnName(), vo.getColumnName());
			}
			while(result.next()){
				Object temp = BeanReflectionUtil.newInstance(obj.getClass().getName());
				for(Field field : fields){
					
					if(map.get(field.getName())!=null){
						field.setAccessible(true);
						field.set(temp, result.getObject(field.getName()));
					}
				}
				list.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		
		if(page !=null){
			page.setResultList(list);
		}
		return list;
	}
	

	@Override
	public List getObjectListByOrderByPro(Object obj, Page page, String orderBy,String desc) {
		List<Object> list = new ArrayList<Object>();
		try { 
			Field[] fields = BeanReflectionUtil.getBeanDeclaredFields(obj.getClass().getName());
			Object tableName = BeanReflectionUtil.getPrivatePropertyValue(obj, "tableName");
			StringBuffer where = new StringBuffer();
			List<Object> propertyValue = new ArrayList<Object>();
			List<Column>  clist = getColumnList(tableName);
			for(Column vo : clist ){
				Object o = BeanReflectionUtil.getPrivatePropertyValue(obj, vo.getColumnName());
				if(o!=null){
					where.append(" and ").append(vo.getColumnName()).append(" =?");
					propertyValue.add(o);
				}
			}
			String sql =  null;
			SqlRowSet result  = null;
			//带条件的查询
			if(propertyValue.size()>0){
				sql = "select *  from  "+tableName+ " where "+ where.toString().substring(4);
			}
			else{
				sql = "select *  from  "+tableName;
			}
			if(orderBy!=null && orderBy.length()>0){
				sql = sql +" order by "+ orderBy;
			}
			if(desc!=null&&desc.length()>0){
				sql=sql+" "+desc;
			}
//			 分页信息
			if(page !=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(*) from (");
				buffer.append(sql);
				buffer.append(") a");
				int pageTotal = this.getJdbcTemplate().queryForInt(buffer.toString(),propertyValue.toArray());
				page.setTotalRows(pageTotal);
				sql = sql+ " limit " + page.getOffset()+","+page.getLimit();
			}
			
			if(propertyValue.size()>0){
				result  =   this.getJdbcTemplate().queryForRowSet(sql,propertyValue.toArray());
				log.info("getObjectById: "+sql);
			}
			else{
				result  =   this.getJdbcTemplate().queryForRowSet(sql);
				log.info("getObjectById: "+sql);
			}
			
			Map<String,String> map = new HashMap<String,String>();
			for(Column vo : clist ){
				map.put(vo.getColumnName(), vo.getColumnName());
			}
			while(result.next()){
				Object temp = BeanReflectionUtil.newInstance(obj.getClass().getName());
				for(Field field : fields){
					
					if(map.get(field.getName())!=null){
						field.setAccessible(true);
						field.set(temp, result.getObject(field.getName()));
					}
				}
				list.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		
		if(page !=null){
			page.setResultList(list);
		}
		return list;
	}

	@Override
	public int updateByMap(String tableName, Map<String,Object> updateMap,Map<String,Object> wherMap) {
		try {
			StringBuffer property = new StringBuffer();
			StringBuffer where = new StringBuffer();
			for(Entry<String, Object> entry : updateMap.entrySet()){
				property.append(",").append(entry.getKey()+" = '").append(entry.getValue()).append("'");
			}
			
			for(Entry<String, Object> entry : wherMap.entrySet()){
				where.append(" and ").append(entry.getKey()+" = '").append(entry.getValue()).append("'");
			}
			
			String sql = "update "+tableName+ " set " +property.toString().substring(1) +" where "+ where.toString().substring(5);
			log.info("update: "+sql);
			return this.getJdbcTemplate().update(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		return 0;
	}
	
	//清空表
	@Override
	public int deleteAll(Object obj) {
		try {
			Object tableName = BeanReflectionUtil.getPrivatePropertyValue(obj, "tableName");
			String sql = "delete from  "+tableName;
			log.info("delete: "+sql);
			return this.getJdbcTemplate().update(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(StringUtil.outputException(e));
		}
		return 0;
	}
}
