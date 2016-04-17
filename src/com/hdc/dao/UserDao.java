package com.hdc.dao;

import java.util.List;

import com.hdc.model.User;
import com.hdc.page.Page;


public interface UserDao {
	
	public User getUserByLogin(String name,String pwd);
	
	public List<User> queryUserList(User user ,Page page);
	public Integer queryPersonCount(int roleId,String key);
	
}
