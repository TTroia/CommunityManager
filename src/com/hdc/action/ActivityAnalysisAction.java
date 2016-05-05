package com.hdc.action;

import java.util.ArrayList;
import java.util.List;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Analysis_activity;
import com.hdc.model.User;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;
import com.hdc.service.UserService;
import com.hdc.util.Constant;

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
	
	/**
	 * 活动次数前十的社团
	 * 1.先筛选出活动总数前十的社团  SELECT com_name, COUNT(*) from activity group by com_id LIMIT 0,10
	 * 2.前台需要 列名，数据 --> 用ajax请求数据 将返回值拼接成 [社团名:活动数量……] 
	 * @return
	 */
	public String top10Com(){
		List<Analysis_activity> list=this.customService.queryAnalysis_activities(new Analysis_activity());
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		for(int i=0;i<list.size();i++){
			sb.append("\""+list.get(i).getCom_name()+"\":\"");
			sb.append(list.get(i).getNum()+"\",");
		}
		sb.append("}");
		sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",")+1, "");
		System.out.println(sb.toString());
		System.out.println("json:"+sb.toString());
		super.outPrintJson(sb.toString());
		return null;
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
