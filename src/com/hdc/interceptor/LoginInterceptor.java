package com.hdc.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.hdc.action.LoginAction;
import com.hdc.action.ServiceAction;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6456504992698935875L;
	
	protected static Logger log = Logger.getLogger(LoginInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "";  
        //因为要把这个拦截器设置成默认拦截器，所以如果是登陆的action则跳过。  
        if(LoginAction.class==invocation.getAction().getClass() || ServiceAction.class== invocation.getAction().getClass()){  
            return invocation.invoke();  
        } 
        ActionProxy proxy = invocation.getProxy(); 
        String methodName = proxy.getMethod(); 
       /* if(methodName.equals("querySynOrder")){
        	 log.info("queryString:="+ServletActionContext.getRequest().getParameter("mType")+" --- actionName："+invocation.getAction()+" methodName:="+methodName);
        	 result = invocation.invoke(); 
        	 log.info("result:="+result);
        	 return result; 
        }*/
        //取session中的user，如果等于null则没有登陆，返回timeout跳转到登陆页面  
        if(ServletActionContext.getRequest().getSession().getAttribute("user")==null &&  ServletActionContext.getRequest().getSession().getAttribute("supplier")==null ){  
        	
//        	if(ServletActionContext.getRequest().getSession().getAttribute("user")==null){
//        		return "timeout";  
//        	}
//        	else if(ServletActionContext.getRequest().getSession().getAttribute("supplier")==null){
        		return "serviceTimeout";  
//        	}
        }  
        
        
        result = invocation.invoke();  
        log.info("actionName："+invocation.getAction()+" methodName:="+methodName);
        return result;  
	}

}
