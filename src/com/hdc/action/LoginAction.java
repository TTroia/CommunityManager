package com.hdc.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hdc.action.base.BaseAction;
import com.hdc.model.Module;
import com.hdc.model.Notice;
import com.hdc.model.User;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;
import com.hdc.service.UserService;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;
public class LoginAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Logger log = Logger.getLogger(LoginAction.class);
	
	private String userId;
	
	private String password;
	
	private String authImg;
	private List<Module> secondList;
	private List<Notice> noticeList;
	// set
	private UserService userService;
	private BaseService baseService;
	private CustomService customService;
//	private RecodeService recodeService;
	
	private int personCount; //人数统计，校社联看到的是全部社团的总人数，系社联看到的是本系社团总人数，社长看到的是本社团总人数
	
	// get 
	
	private List oneList;// 父级菜单
	
	
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 登录
	 * @return
	 */
	public String login(){
		try {
			
			HttpSession session = request.getSession();
			String authCode = (String) session.getAttribute("authCode");
//			if(authCode==null || !authCode.equals(authImg)){
//				super.outPrintJson(super.getJsonInfo("验证码错误",Constant.ERROR));
//				return null;
//			}
			User user = this.userService.getUserByLogin(userId, password);
			
			if(user==null){
				super.outPrintJson(super.getJsonInfo("用户名或密码错误",Constant.ERROR));
			}else{
				session.setAttribute("user",user);
				super.outPrintJson(super.getJsonInfo("登录成功",Constant.SUCCESS));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(super.getJsonInfo("系统异常",Constant.ERROR));
		}
		
		return null;
	}

	/**
	 * 退出按钮
	 * @return
	 */
	public String loginOut(){
		
		try {
			HttpSession session = request.getSession();
			session.setAttribute("user",null);
			session.removeAttribute("user");
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		
		return "loginOut";
	}
	
	public String goMian(){
		
		try {
			
			User user = super.getUser();
			Module module = new Module();
			//query fu Id
			module.setPartentId(0);
			List<Module> tempList =  this.baseService.getObjectList(module, null);
			this.oneList = new ArrayList<Module>();
			
			// 超级管理员身份
			if(user.getRoleId()==Constant.SUPPER_ADMIN_ROLEiD){
				if(tempList!=null && tempList.size()>0){
					Collections.sort(tempList);//排好序
					for(Module vo :tempList){
							getUserMenuList(vo.getId());
							vo.setSecondList(secondList);
							this.oneList.add(vo);
						}
					}
				Notice notice = new Notice();
				notice.setToUser(super.getUserId());
				this.noticeList=this.customService.queryNoticeList(notice);
				System.out.println("notice="+noticeList.size());
				return "index";
			}
			
			//取用户的权限集合
			String  moduleIds = user.getModuleIds();
			Map<String,String> temp = new HashMap<String,String>();
			if(moduleIds!=null && moduleIds.length()>0){
				String[] strs = moduleIds.split(",");
				for(String str : strs){
					temp.put(str, str);
				}
				
			}
			if(tempList!=null && tempList.size()>0){
				Collections.sort(tempList);//排好序
				for(Module vo :tempList){
					if(temp.get(vo.getId().toString())!=null){
						getUserMenuList(vo.getId());
						vo.setSecondList(secondList);
						this.oneList.add(vo);
					}
				}
			}
			if(super.getUser().getRoleId()==1){
				this.personCount=this.userService.queryPersonCount(super.getUser().getRoleId(),null);
			}else if(super.getUser().getRoleId()==2) {
				this.personCount=this.userService.queryPersonCount(super.getUser().getRoleId(),super.getUser().getPartof());
			}else if(super.getUser().getRoleId()==3){
				this.personCount=this.userService.queryPersonCount(super.getUser().getRoleId(),super.getUser().getCommunityId().toString());
			}
			
			Notice notice = new Notice();
			notice.setToUser(super.getUserId());
			this.noticeList=this.customService.queryNoticeList(notice);
			
			
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(super.getJsonInfo("系统异常",Constant.ERROR));
		}
		
		return "index";
	}
	
	public String getUserMenuList(int partentId){
		try {
			Module module = new Module();
			//query fu Id
			module.setPartentId(partentId);
			
			List<Module> tempList =  this.baseService.getObjectList(module, null);
			this.secondList =  new ArrayList<Module>();
			
			//判断是超级管理员
			User user = super.getUser();
			if(user.getRoleId()==Constant.SUPPER_ADMIN_ROLEiD){
				if(tempList!=null && tempList.size()>0){
					Collections.sort(tempList);//排好序
					this.secondList.addAll(tempList);
				}
				
				return  "getUserMenuList";
			}
			
			//取用户的权限集合
			String  moduleIds = user.getModuleIds();
			Map<String,String> temp = new HashMap<String,String>();
			if(moduleIds!=null && moduleIds.length()>0){
				String[] strs = moduleIds.split(",");
				for(String str : strs){
					temp.put(str, str);
				}
				
			}
			if(tempList!=null && tempList.size()>0){
				Collections.sort(tempList);//排好序
				for(Module vo :tempList){
					if(temp.get(vo.getId().toString())!=null){
						this.secondList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		
		return  "getUserMenuList";
	}

	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthImg(String authImg) {
		this.authImg = authImg;
	}

	public List getOneList() {
		return oneList;
	}

	public int getPersonCount() {
		return personCount;
	}

	public List<Notice> getNoticeList() {
		return noticeList;
	}

	public void setCustomService(CustomService customService) {
		this.customService = customService;
	}

//	public void setRecodeService(RecodeService recodeService) {
//		this.recodeService = recodeService;
//	}

}
