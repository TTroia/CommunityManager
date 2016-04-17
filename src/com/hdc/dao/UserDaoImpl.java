package com.hdc.dao;

import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.hdc.model.User;
import com.hdc.model.rowmap.ObjectRowMapper;
import com.hdc.model.rowmap.UserListRowMapper;
import com.hdc.model.rowmap.UserRowMapper;
import com.hdc.page.Page;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;

public class UserDaoImpl extends CommonDaoImpl implements UserDao {
	
	protected static Logger log = Logger.getLogger(UserDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByLogin(String name, String pwd) {
		String sql ="select a.* , b.name roleName from "+Constant.USER_TABLE_NAME +" a left join "+ Constant.ROLE_TABLE_NAME +" b on a.roleId = b.roleId where a.userId =? and a.pwd=?";
		List<User> list = this.getJdbcTemplate().query(sql, new Object[]{name,pwd}, new UserRowMapper());
		if(list!=null && list.size()>0){
			
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<User> queryUserList(User user, Page page) {
		
		String sql =" select a.* , b.name roleName from "+Constant.USER_TABLE_NAME +" a left join "+ Constant.ROLE_TABLE_NAME +" b on a.roleId = b.roleId where 1=1 ";
		if(user!=null && !StringUtil.isNullOrBlank(user.getUserId())){
			sql = sql +" and  a.userId like '%"+user.getUserId()+"%'";
		}
		if(user!=null && !StringUtil.isNullOrBlank(user.getUsername())){
			sql = sql +" and  a.username like '%"+user.getUsername()+"%'";
		}
		if(user!=null && !StringUtil.isNullOrBlank(user.getPartof())){
			sql = sql +" and  a.partof like '%"+user.getPartof()+"%'";
		}
		if(user!=null && user.getRoleId()!=null && user.getRoleId()!=0){
			if(user.getRoleId()==12){
				sql=sql + " and a.roleId != 3";
			}else {
				sql=sql +" and a.roleId="+user.getRoleId();
			}
		}
		if(page !=null){
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from (");
			buffer.append(sql);
			buffer.append(") a");
			int pageTotal = this.getJdbcTemplate().queryForInt(buffer.toString());
			page.setTotalRows(pageTotal);
			sql = sql+ " limit " + page.getOffset()+","+page.getLimit();
		}
		log.info(sql);
		List<User> list = this.getJdbcTemplate().query(sql, new UserListRowMapper());
		if(page !=null){
			page.setResultList(list);
		}
		return list;
	}

	@Override
	public Integer queryPersonCount(int roleId,String key) {
		String sql = "select COUNT(1) from "+Constant.MEMBER_TABLE_NAME+" where 1 = 1";
		if(roleId>=0){
			if(roleId==1){
				
			}else if(roleId== 2){
				sql = sql+ " and department = '"+key+"' ";
			}else if(roleId == 3 ){
				sql =sql + " and com_id = "+Integer.parseInt(key);
			}
		}
		log.info(sql);
			//sql=sql+" and roleId = "+roleId;
		int personCount = this.getJdbcTemplate().queryForInt(sql);
		return personCount;
	}


	
}
