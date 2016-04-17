package com.hdc.service;

import java.util.List;

import com.hdc.model.User;
import com.hdc.page.Page;

public interface UserService  {
	
	public User getUserByLogin(String userId,String pwd);
	
	public List<User> queryUserList(User user ,Page page);
	public Integer queryPersonCount(int roleId,String key);
}
