package com.hdc.model.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hdc.model.Analysis_finance;

public class FinanceAnalysisRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet result, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Analysis_finance analysis_finance = new Analysis_finance();
		analysis_finance.setExpend(Math.abs(result.getDouble("a")));
		analysis_finance.setIncome(result.getDouble("b"));
		return analysis_finance;
	}

}
