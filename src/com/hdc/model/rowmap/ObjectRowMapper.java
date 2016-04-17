package com.hdc.model.rowmap;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.hdc.model.Column;
import com.hdc.util.BeanReflectionUtil;

public class ObjectRowMapper implements RowMapper{
	
	
	private Object object;
	
	private List<Column>  list;
	
	public ObjectRowMapper(Object object ,List<Column>  list){
		
		this.object = object;
		this.list = list;
	}

	@Override
	public Object mapRow(ResultSet result, int arg1) throws SQLException {
		
		try {
			Field[] fields = BeanReflectionUtil.getBeanDeclaredFields(object
					.getClass().getName());
			//db字段
			Map<String, String> map = new HashMap<String, String>();
			for (Column vo : list) {
				map.put(vo.getColumnName(), vo.getColumnName());
			}
			//硬生产了一个对象
			Object ret = BeanReflectionUtil.newInstance(object.getClass()
					.getName());
			//求bean字段和db字段交集 
			for (Field field : fields) {
				if (map.get(field.getName()) != null) {
					field.setAccessible(true);
					field.set(ret, result.getObject(field.getName()));
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
