package com.hdc.action;

import java.util.ArrayList;
import java.util.List;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.User;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;
import com.hdc.service.UserService;

public class ActivityAnalysisAction extends PaginationAction {

	private static final long serialVersionUID = 1L;
	
	private BaseService baseService;
	private CustomService customService;
	private UserService userService;
	private List<String> partofList;
	
	public String list(){
		List<User> users=new ArrayList<User>();
		partofList=new ArrayList<String>();
		User u=new User();
		u.setRoleId(2);
		users=this.userService.queryUserList(u, null);
		for (User user : users) {
			partofList.add(user.getUsername());
		}
		return "list";
	}
	
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	public CustomService getCustomService() {
		return customService;
	}
	public void setCustomService(CustomService customService) {
		this.customService = customService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<String> getPartofList() {
		return partofList;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
