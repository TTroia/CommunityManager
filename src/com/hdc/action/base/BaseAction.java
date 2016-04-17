package com.hdc.action.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.hdc.model.User;

/**
 * @author Administrator
 *
 */
public class BaseAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware {
	
	public HttpServletRequest request;
	public HttpServletResponse response;
	
	public Integer onEditDelte= 0; 
	
	/**
	 * 保存用户的登录Id
	 */
	protected final static String LOGIN_SELLERID = "SessionSellerId";
	
	/**
	 * 保存用户的登录名
	 */
	protected final static String LOGIN_USERNAME = "loginUsername";
	
	
	//增加打印日志D://web_log.log
	protected Log log = LogFactory.getLog(this.getClass().getName());
	/**
	 * 
	 */
	private String path;
	
	public String getPath() {
		path = request.getContextPath();
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	/**
	 * 根据信息与响应码，返回JSON格式的字符串
	 * 
	 * @param message
	 *            响应信息
	 * @param retcode
	 *            响应码
	 * @return String JSON串
	 * @author ZY
	 */
	protected String getJsonInfo(String message, int retcode) {
		StringBuffer strB = new StringBuffer();

		strB.append("{\"retmess\":\"");
		strB.append(message);
		strB.append("\",\"retcode\":\"");
		strB.append(retcode);
		strB.append("\"}");

		return strB.toString();
	}
	
	/**
	 * 自定义JSON返回字符串
	 */
	protected String getJsonInfo_byUser(int retcode,Object...args){
		StringBuffer sb=new StringBuffer();
		sb.append("{\"retcode\":\"");
		sb.append(retcode);
		for(int i=0;i<args.length;i+=2){
			sb.append("\",\""+args[i]+"\":\"");
			sb.append(args[i+1]);
		}
		sb.append("\"}");
		return sb.toString();
	}
	/**
	 * 输出json字符串
	 * @param value
	 */
	protected void outPrintJson(String value){
		try {
			response.setContentType("text/json;charset=utf-8");
			response.setHeader("Charset", "UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(value);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 取到用户的信息
	 * @return
	 */
	protected User getUser(){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null){
			try {
				this.response.sendRedirect("index.jsp");
				return new User();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	

	/**
	 * 取到用户的信息
	 * @return
	 */
	protected String getUserId(){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null){
			try {
				this.response.sendRedirect("/index.jsp");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user.getUserId();
	}
	
	public Integer getOnEditDelte() {
		return onEditDelte;
	}
	public void setOnEditDelte(Integer onEditDelte) {
		this.onEditDelte = onEditDelte;
	}
	
}
