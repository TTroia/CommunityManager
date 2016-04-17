package com.hdc.util;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class WebApplicationContextServlet implements ServletContextListener  {

   private static WebApplicationContext springContext;
    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }
    public void contextDestroyed(ServletContextEvent event) {
    }
    
    public static WebApplicationContext getWebApplicationContext() {
        return springContext;
    }
}
