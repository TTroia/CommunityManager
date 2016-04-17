package com.hdc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanReflectionUtil {
	
    /**
     * 获得某类的静态属性
     * @param className
     * @param fieldName
     * @return
     * @throws Exception
     */
    public static Object getStaticProperty(String className,String fieldName)throws Exception{
        Class cls=Class.forName(className);
        Field field=cls.getField(fieldName);
        Object provalue=field.get(cls);
        return provalue;
    }
    /**
     * 获取参数对象的属性值
     * @param obj
     * @param propertyName
     * @return
     * @throws Exception
     */
    public static Object getPrivatePropertyValue(Object obj,String propertyName)throws Exception{
        Class cls=obj.getClass();
        Field field=cls.getDeclaredField(propertyName);
        field.setAccessible(true);
        Object retvalue=field.get(obj);
        return retvalue;
    }
    
    /**
     * 执行某对象的方法
     * @param owner
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    public Object invokeMethod(Object owner,String methodName,Object[] args)throws Exception{
        Class cls=owner.getClass();
        Class[] argclass=new Class[args.length];
        for(int i=0,j=argclass.length;i<j;i++){
            argclass[i]=args[i].getClass();
        }
        Method method=cls.getMethod(methodName,argclass);
        return method.invoke(owner, args);
    }
    
    /**
     * 执行静态类的方法
     * @param className
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    public Object invokeStaticMethod(String className,String methodName,Object[] args)throws Exception{
        Class cls=Class.forName(className);
        Class[] argclass=new Class[args.length];
        for(int i=0,j=argclass.length;i<j;i++){
            argclass[i]=args[i].getClass();
        }
        Method method=cls.getMethod(methodName,argclass);
        return method.invoke(null, args);
    }
    
    
    public static Object newInstance(String className)throws Exception{
        Class clss=Class.forName(className);
        java.lang.reflect.Constructor cons=clss.getConstructor();
        return cons.newInstance();
    }
    
    /**
     * 等到对象的所有属性
     * @param className
     * @return
     * @throws ClassNotFoundException 
     */
    public  static Field[] getBeanDeclaredFields(String className) throws ClassNotFoundException{
    	
    	 Class clss=Class.forName(className);
    	 Field[] fs = clss.getDeclaredFields();
    	 return fs;
    }
   
   
    /*
     * 得到类中的方法
     */
    public  static Method[] getBeanDeclaredMethods(String className) throws ClassNotFoundException{
    	
    	Class clss=Class.forName(className);
  
    	Method[] methods = clss.getMethods();
    	
    	return methods;
    /*for(int i = 0; i < methods.length; i++){
        Method method = methods[i];
        if(method.getName().startsWith("get")){
           System.out.print("methodName:"+method.getName()+"\t");
           System.out.println("value:"+method.invoke(bean));//得到get 方法的值
        }
    }*/
 }
    
    /**
     * bean的属性拷贝
     * @param source 被拷贝的对象
     * @param target 拷贝的对象
     */
    public static void copyProperties(Object source,Object target){
    	
    	try {
    		List<Field> list = new ArrayList<Field>();
    		Field[] sourceFild = getBeanDeclaredFields(source.getClass().getName());
    		Field[] targetFild = getBeanDeclaredFields(target.getClass().getName());
    		Map<String,Field> map = new HashMap<String,Field>();
    		for(Field field : targetFild){
    			map.put(field.getName(), field);
    		}
    		for(Field field : sourceFild){
    			if(map.get(field.getName())!=null){
    				list.add(field);
    			}
    		}
    		//source 属性值
    		for(Field field : list){
    			if(field.getName().equals("tableName")){
    				continue;
    			}
    			Field tg = map.get(field.getName());
    			tg.setAccessible(true);
    			tg.set(target, getPrivatePropertyValue(source,field.getName()));
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
