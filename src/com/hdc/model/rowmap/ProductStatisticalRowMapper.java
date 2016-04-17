package com.hdc.model.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hdc.model.Column;

public class ProductStatisticalRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet result, int arg1) throws SQLException {
		
		Column vo = new Column();
		vo.setColumnName(result.getString("COLUMN_NAME"));
		vo.setPrikey(result.getString("COLUMN_KEY"));
		return vo;
	}

}
