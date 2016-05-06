package com.hdc.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Community;
import com.hdc.model.Nature;
import com.hdc.model.User;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;
import com.hdc.service.UserService;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;

public class CommunityAction extends PaginationAction {

	private static final long serialVersionUID = 8031422320811748478L;
	
	private BaseService baseService;
	private CustomService customService;
	private UserService userService;
	
	private Community community;
	private List<Community> resultList;
	private List<Nature> natureList;
	private List<String> partofList;
	
	public String list(){
		return "list";
	}
	
	public String _list(){
		if(community==null){
			this.community=new Community();
		}
		if(super.getUser().getRoleId()==2){
			community.setPartof(super.getUser().getUsername());
		}
		this.resultList=this.customService.queryCommunitieList(community, super.getPage());
		
		this.natureList=this.baseService.getObjectList(new Nature(), null);
		this.partofList=new ArrayList<String>();
		List<User> users=new ArrayList<User>();
		User u=new User();
		u.setRoleId(12);
		users=this.userService.queryUserList(u, null);
		for (User user : users) {
			partofList.add(user.getUsername());
		}
		return "_list";
	}
	
	public String delete(){
		try {
			int k =this.baseService.delete(community);
			if(k>0){
				super.outPrintJson(getJsonInfo("删除成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("删除失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	public String edit(){
		return null;
	}
	
	public String update(){
		try {
			int k= this.baseService.update(community);
			if(k>0){
				super.outPrintJson(getJsonInfo("修改成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("修改失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	public String inAdd(){
		
		return null;
	}
	
	public String add(){
		try {
			community.setCreateDate(new Date());
			community.setCreateUser(super.getUserId());
			int k = this.baseService.insert(community);
			if(k>0){
				super.outPrintJson(getJsonInfo("添加成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
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
	public Community getCommunity() {
		return community;
	}
	public void setCommunity(Community community) {
		this.community = community;
	}
	public List<Community> getResultList() {
		return resultList;
	}
	public void setResultList(List<Community> resultList) {
		this.resultList = resultList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Nature> getNatureList() {
		return natureList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<String> getPartofList() {
		return partofList;
	}
	
	

}
