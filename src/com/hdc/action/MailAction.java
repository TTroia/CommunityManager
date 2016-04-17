package com.hdc.action;

import java.util.Date;
import java.util.List;

import com.hdc.action.base.BaseAction;
import com.hdc.model.Notice;
import com.hdc.model.User;
import com.hdc.service.BaseService;
import com.hdc.service.UserService;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;

public class MailAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private BaseService baseService;
	private UserService userService;
	
	private Notice notice;
	private String[] toUsers;
	private List<User> userList;
	
	public String list(){
		User user = new User();
		if(super.getUser().getRoleId()==1){
			user.setRoleId(2);
		}else if(super.getUser().getRoleId()==2) {
			user.setRoleId(3);
			user.setPartof(super.getUser().getPartof()+"%' or a.roleId like '%1");
		}else if(super.getUser().getRoleId()==3){
			user.setRoleId(2);
			user.setPartof(super.getUser().getPartof());
		}
		this.userList=this.userService.queryUserList(user, null);
		return "list";
	}
	
	public String sendMail(){
		try {
			if(null==toUsers){
				System.out.println("没有选择接收方");
				super.outPrintJson(getJsonInfo("发送失败", Constant.ERROR));
				return null;
			}
			for(int i=0;i<toUsers.length;i++){
				notice.setSendDate(new Date());
				notice.setFromUser(super.getUser().getUsername());
				notice.setToUser(toUsers[i]);
				notice.setIsRead(0);
				int k=this.baseService.insert(notice);
				if(k>0){
					super.outPrintJson(getJsonInfo("发送成功", Constant.SUCCESS));
				}else{
					super.outPrintJson(getJsonInfo("发送失败", Constant.ERROR));
				}
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	public String isRead(){
		try {
			notice.setIsRead(1);
			int k = this.baseService.update(notice);
			if(k>0){
				super.outPrintJson(getJsonInfo("成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("失败", Constant.ERROR));
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

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String[] getToUsers() {
		return toUsers;
	}

	public void setToUsers(String[] toUsers) {
		this.toUsers = toUsers;
	}
	
}
