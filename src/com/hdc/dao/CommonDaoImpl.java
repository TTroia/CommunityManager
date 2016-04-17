package com.hdc.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.hdc.model.Column;
import com.hdc.model.rowmap.ColumnRowMapper;
import com.hdc.util.Constant;

public class CommonDaoImpl extends JdbcDaoSupport {
	
	protected static Logger log = Logger.getLogger(CommonDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	public List<Column>  getColumnList(Object tableName){
		String sql = "select COLUMN_NAME ,COLUMN_KEY from information_schema.COLUMNS where table_name = ? and table_schema = ?";
		return this.getJdbcTemplate().query(sql, new Object[]{tableName,Constant.DB_NAME}, new ColumnRowMapper());
	}
	
}
