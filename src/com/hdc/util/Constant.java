package com.hdc.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class Constant {
	
	public static final String 	DB_NAME="communitymanager";
	
	public static final  String  ROLE_TABLE_NAME = "role";
	
	public static final  String  USER_TABLE_NAME ="user";
	
	public static final  String  MODULE_TABLE_NAME="module";
	/**
	 * 登录日志
	 */
	public static final String LOGIN_LOG = "login_log";
	
	/**
	 * 活动记录表
	 * 申请+批复
	 */
	public static final String ACTIVITY_TABLE_NAME="activity";
	
	/**
	 * 社团登记表
	 */
	public static final String COMMUNITY_TABLE_NAME="community";
	
	/**
	 * 社团财务表
	 */
	public static final String FINANCE_TABLE_NAME="finance";
	
	/**
	 * 社团成员表
	 */
	public static final String MEMBER_TABLE_NAME="member";
	
	/**
	 * 社团类型表
	 */
	public static final String NATURE_TABLE_NAME="nature";
	
	/**
	 * 站内信表
	 */
	public static final String NOTICE_TABLE_NAME = "notice";
	
	
	//超级管理员 角色Id
	public static final  Integer  SUPPER_ADMIN_ROLEiD = 0;
	
	public static final Integer SUCCESS = 0;
	
	public  static final Integer ERROR = 1;
	
	public static final Integer NOEDITDELETE =1;

	public static int CURRENT_LOGIN_COUNT = 0;//当前登录用户总数
	
	public static int TOTAL_DATE_COUNT = 0;//当天访客总数
	
//	public static Map<String,HttpSession> SESSION_MAP = new HashMap<String,HttpSession>();
	
	public static Map<String,Map<String,HttpSession>> DATE_SESSION_MAP = new HashMap<String,Map<String,HttpSession>>();
	
}
