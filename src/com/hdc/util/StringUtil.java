package com.hdc.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {
	
	/**
	 * 输入错误信息到日志上
	 * @param e
	 * @return
	 */
	public static String outputException(Exception e) {

		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
			return sw.toString();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	/**
	 *  
	 * @param obj
	 * @return array数组的所有值
	 */
	public static String getArrayValue(Object[] obj){
		if(obj==null || obj.length==0){
			return "";
		}
		String ret = "";
		for(Object o :obj){
			ret = ret+","+o;
		}
		return ret.substring(1);
	}
	/**
	 * 
	 * @param value 
	 * @return 如果为空 返回true
	 */
	public static boolean isNullOrBlank(String value){
		if(value==null || value.length()==0){
			return true;
		}
		return false;
	}
	
	public static boolean isNumber(String number){
		try {
			if(number==null || number.length()==0){
				return false;
			}
			Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(number);
            if( !isNum.matches() )
            {
                  return false;
            }
            return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(isNumber("22"));
	}
	/**
	 * 
	 * @param value 
	 * @return 如果为空 返回""
	 */
	public static String StringTrim(String value){
		if(value==null || value.length()==0){
			return "";
		}
		return value.trim();
	}

	public static String getNumberData(String value){
		try {
			Pattern p = Pattern.compile("[\\d]{1,}");
			Matcher match = p.matcher(value);
			if(match.find()){
				return match.group();
			}else if(value.contains("一")){;
				return "1";
			}
			else if(value.contains("二")){;
				return "2";
			}
			else if(value.contains("三")){;
				return "3";
			}
			else if(value.contains("四")){;
				return "4";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param v1 除数
	 * @param v2 被除数
	 * @return
	 */
	public static  String formatZhuanLv(int v1,int v2){
		 if(v2==0){
			 return "0.00%";
		 }
	      DecimalFormat fmt = new DecimalFormat("0.00%");
//	      return v1+"/"+v2+"="+fmt.format((double)v1/v2);
	      return fmt.format((double)v1/v2);
	  }
	 
}
