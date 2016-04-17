package com.hdc.model.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hdc.model.User;

public class UserListRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet result, int arg1) throws SQLException {
		User user = new User();
		user.setUserId(result.getString("userId"));
		user.setRoleId(result.getInt("roleId"));
		user.setStatus(result.getInt("status"));
		user.setUsername(result.getString("username"));
		user.setCreateDate(result.getTimestamp("createDate"));
		user.setRoleName(result.getString("roleName"));
		user.setModuleIds(result.getString("moduleIds"));
		user.setAttr(result.getString("attr"));
		user.setCommunityId(result.getInt("communityId"));
		user.setCommunityName(result.getString("communityName"));
		user.setPartof(result.getString("partof"));
		user.setPhone(result.getString("phone"));
		user.setQq(result.getString("qq"));
		return user;
	}

}
