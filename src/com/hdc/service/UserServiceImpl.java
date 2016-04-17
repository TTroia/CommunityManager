package com.hdc.service;

import java.util.List;

import com.hdc.dao.UserDao;
import com.hdc.model.User;
import com.hdc.page.Page;

public class UserServiceImpl  implements UserService {

	private UserDao userDao;
	
	@Override
	public User getUserByLogin(String userId, String pwd) {
		// TODO Auto-generated method stub
		return this.userDao.getUserByLogin(userId, pwd);
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public List<User> queryUserList(User user, Page page) {
		// TODO Auto-generated method stub
		return this.userDao.queryUserList(user, page);
	}
	@Override
	public Integer queryPersonCount(int roleId,String key) {
		// TODO Auto-generated method stub
		return this.userDao.queryPersonCount(roleId,key);
	}

}
