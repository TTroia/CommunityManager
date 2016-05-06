package com.hdc.model.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hdc.model.Analysis_member;

public class MemberAnalysisRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet result, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Analysis_member analysis_member = new Analysis_member();
		analysis_member.setCom_name(result.getString("com_name"));
		analysis_member.setNum(result.getInt("num"));
		return analysis_member;
	}

}
