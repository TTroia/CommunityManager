package com.hdc.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hdc.util.Constant;
import com.hdc.util.DateUtil;

public class CountListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		//新创建的session
        HttpSession session = (HttpSession)sessionEvent.getSession();
        Date now = new Date();
        String yyyyMMdd = DateUtil.dateConvertToString(now, "yyyyMMdd");
        Map<String,HttpSession> session_map = Constant.DATE_SESSION_MAP.get(yyyyMMdd);
        if(session_map == null){
        	Constant.DATE_SESSION_MAP.clear();
        	 Constant.CURRENT_LOGIN_COUNT = 0;
        	session_map = new HashMap<String,HttpSession>();
        	Constant.DATE_SESSION_MAP.put(yyyyMMdd, session_map);
        }
        //保存session
        session_map.put(session.getId(),session);
        //在线人数++
        Constant.CURRENT_LOGIN_COUNT++;
        Constant.TOTAL_DATE_COUNT = session_map.size();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        Constant.CURRENT_LOGIN_COUNT--;
	}
}
