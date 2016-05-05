package com.hdc.model.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hdc.model.Analysis_activity;

public class ActivityAnalysisRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet result, int arg1) throws SQLException {
		Analysis_activity analysis_activity = new Analysis_activity();
		analysis_activity.setCom_name(result.getString("com_name"));
		analysis_activity.setNum(result.getInt("count(*)"));
		return analysis_activity;
	}

}
