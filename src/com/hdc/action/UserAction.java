package com.hdc.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Module;
import com.hdc.model.Role;
import com.hdc.model.User;
import com.hdc.service.BaseService;
import com.hdc.service.UserService;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;

public class UserAction extends PaginationAction {

	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(UserAction.class);
	// set
	private Integer id;
	private  String pwd;
	private String newPwd;
	private Integer roleId;//所属角色
	private Integer status;//用户状态  0 未禁用  1为启用
	private String username;
	private String userId;
	private String qq;
	private String phone;
	private String zTreeData;
	private String moduleIds;
	private Integer partentId;
	//get
	private List resultList;
	private List roleList;
	private List<Module> secondList;// 二级菜
	private List<String> partofList;
	
	// servcie
	private BaseService baseService;
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//get
	private User user;
	
	
	
	/**
	 * 取得列表
	 * @return
	 */
	public String list(){
		
		
		return "list";
	}
	
	public String _list(){
		try {
			if(user==null){
				user = new User();
			}
			this.userService.queryUserList(user, super.getPage());
			Role vo = new Role();
			this.roleList = this.baseService.getObjectList(vo, null);
			this.partofList=new ArrayList<String>();
			List<User> users=new ArrayList<User>();
			User u=new User();
			u.setRoleId(12);
			users=this.userService.queryUserList(u, null);
			for (User user : users) {
				partofList.add(user.getUsername());
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return "_list";
	}
	
	/**
	 * 添加用户信息
	 */
	public String inAdd(){
		
		try {
			Role vo = new Role();
			this.roleList = this.baseService.getObjectList(vo, null);
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
		}
		
		return  "inAdd";
	}

	/**
	 * 添加用户信息
	 */
	public String add(){
		
		try {
			User newUser=new User();
			newUser=this.user;
			newUser.setCreateDate(new Date());
			newUser.setStatus(1);
			Role temp = new Role();
			temp.setRoleId(roleId);
			temp = (Role) this.baseService.getObjectById(temp);
			if(temp!=null){
				newUser.setModuleIds(temp.getModuleIds());
			}
			int  k  = this.baseService.insert(newUser);
			if(k>0){
				super.outPrintJson(getJsonInfo("添加成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
		}
		
		return  null;
	}
	/**
	 * 删除用户
	 * @return
	 */
	public String delete(){
		
		try {
			User user = new User();
			user.setUserId(userId);
			int  k  = this.baseService.delete(user);
			if(k>0){
				super.outPrintJson(getJsonInfo("删除成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("删除失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
		}
		
		return  null;
	}
	
	
	public String edit(){
		
		try {
			User user = new User();
			user.setUserId(userId);
			//user.setPwd(pwd);
			this.user  = (User) this.baseService.getObjectById(user);
			super.outPrintJson(getJsonInfo_byUser(Constant.SUCCESS, "userId",this.user.getUserId(),"username",this.user.getUsername(),"pwd",this.user.getPwd(),
					"phone",this.user.getPhone(),"QQ",this.user.getQq()));
			
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		
		return  null;
	}
	
	public String edit_pwd(){
		try {
			System.out.println("进入edit_pwd方法");
			User user = new User();
			user.setUserId(userId);
			user.setPwd(pwd);
			this.user  = (User) this.baseService.getObjectById(user);
			super.outPrintJson(getJsonInfo_byUser(Constant.SUCCESS,"pwd",user.getPwd(),"userId",user.getUserId()));
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	/**
	 * 修改用户信息
	 */
	public String update(){
		try {
			int  k  = this.baseService.update(user);
			if(k>0){
				super.outPrintJson(getJsonInfo("更新成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("更新失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("更新失败", Constant.ERROR));
		}
		
		return  null;
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String editAuth(){
		
		try {
			User temp = new User();
			temp.setUserId(userId);
			this.user = (User) this.baseService.getObjectById(temp);
			
			Module module = new Module();
			module.setPartentId(0);
			List<Module> tempList =  this.baseService.getObjectList(module, null);
			resultList = new ArrayList<Module>();
			if(tempList!=null && tempList.size()>0){
				Collections.sort(tempList);//排好序
				for(Module vo :tempList){
					resultList.add(vo);
					module = new Module();
					module.setPartentId(vo.getId());
					List<Module> tempList2 =  this.baseService.getObjectList(module, null);
					Collections.sort(tempList2);//排好序
					resultList.addAll(tempList2);
				}
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		
		JSONArray ja = new JSONArray();
		String  moduleIds = user.getModuleIds();
		Map<String,String> temp = new HashMap<String,String>();
		if(moduleIds!=null && moduleIds.length()>0){
			String[] strs = moduleIds.split(",");
			for(String str : strs){
				temp.put(str, str);
			}			
		}
		if(resultList!=null && resultList.size()>0){
			
			for(int i=0;i<resultList.size();i++ ){
				JSONObject jo = new JSONObject();
				Module vo = (Module) resultList.get(i);
				jo.put("id", vo.getId());
				jo.put("pId", vo.getPartentId());
				jo.put("name", vo.getName());
				jo.put("open", true);
				if(temp.get(vo.getId().toString())!=null){
					jo.put("checked",true);
				}
				ja.add(jo);
			}
		}
		
		this.zTreeData = ja.toJSONString();
		request.setAttribute("zTreeData", zTreeData);
		
		return  "editAuth";
	}
	
	/**
	 * 更新权限
	 */
	public String updateAuth(){
		
		try {
			User user = new User();
			user.setUserId(userId);
			user.setModuleIds(moduleIds);
			int  k  = this.baseService.update(user);
			if(k>0){
				super.outPrintJson(getJsonInfo("更新成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("更新失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("更新失败", Constant.ERROR));
		}
		
		return  null;
	}
	
	/**
	 * 取得用的权限
	 * @return
	 */
	public String getUserMenuList(){
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
				JSONArray arr = new JSONArray();
				for (Module m : secondList) {
					JSONObject json = new JSONObject();
					json.put(m.getId(), m.getName());
					arr.add(json);
				}
				
				super.outPrintJson(super.getJsonInfo(arr.toString(), Constant.SUCCESS));
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

	/**
	 * 修改密码
	 * @return
	 */
	public String updatePwd(){
		
		try {
			int k = this.baseService.update(user);
			if(k>0){
				super.outPrintJson(getJsonInfo("更新成功", Constant.SUCCESS));
				user.setPwd(newPwd);
			}else{
				super.outPrintJson(getJsonInfo("更新失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("更新失败", Constant.ERROR));
		}
		
		return  null;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List getRoleList() {
		return roleList;
	}

	public String getzTreeData() {
		return zTreeData;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
	
	public List<Module> getSecondList() {
		return secondList;
	}

	public void setPartentId(Integer partentId) {
		this.partentId = partentId;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getPartofList() {
		return partofList;
	}

	public void setPartofList(List<String> partofList) {
		this.partofList = partofList;
	}
	
}
