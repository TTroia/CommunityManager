package com.hdc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
	
	/**
	 * 取的日期时间
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getDate(String date) throws Exception {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = localSimpleDateFormat.parse(date);
		return date1;
	}
	
	/**
	 * 
	 * @param date
	 * @return 当期月的第一天
	 */
	public static String getMonthOneDate(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01");
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 一个月的最后一天
	 * @param date
	 * @return
	 */
	public static String getMonthLastDate(Date date) {
	// 获取Calendar  
		Calendar calendar = Calendar.getInstance();  
		// 设置日期为本月最大日期  
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());  
	}
	/**
	 * 
	 * @param date
	 * @param patten 日期转为String
	 * @return
	 */
	public static String dateConvertToString(Date date,String patten) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(patten);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param date
	 * @param patten 日期转为String
	 * @return
	 */
	public static Date stringConvertToDate(String dateString,String patten) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(patten);
			return sdf.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param date 
	 * @return 当前时间的前一天的数据
	 */
	public static Date getBeforeDay(Date date ,int dateNo){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dateNo);
		date = calendar.getTime();
		return date;
	}
	
	
	public static Date getMonthOfValueParam(Date date,int value) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, value);
		Date da = cal.getTime();
		return da;
	}
	
	public static Date getYearOfValueParam(Date date,int value) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, value);
		Date da = cal.getTime();
		return da;
	}
	
	
	public static Date getDayOfValueParam(Date date,int value) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, value);
		Date da = cal.getTime();
		return da;
	}
	
	/**
	 * 取得 两个时间段之间所有的天数
	 * @param da1 开始时间
	 * @param da2 结束时间
	 * @return
	 * @throws Exception
	 */
	public static List<Date> getBtwnDateDay(Date da1,Date da2) throws Exception{
			if(da1.getTime()>da2.getTime()){//第一个时间比第二个时间小
				return null;
			}
			List<Date> dayList = new ArrayList<Date>();
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(da1);
			c2.setTime(da2);
			while(c1.getTime().getTime()<=c2.getTime().getTime()){
				dayList.add(c1.getTime());
				c1.add(Calendar.DATE, 1);
			}
		return dayList;
	}
	
	public static void main(String[] args) {
		Date now = new Date();
		System.err.println(dateConvertToString(getDayOfValueParam(now,200),"yyyy-MM-dd"));
	}
	
	
}
