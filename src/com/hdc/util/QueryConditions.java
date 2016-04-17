package com.hdc.util;

import java.util.HashMap;
import java.util.Map;

public class QueryConditions {
	
	//大与等于
	private  Map<String,String> lgEqMap = new HashMap<String,String>();
	
	//小于与等于
	private  Map<String,String> ltEqMap = new HashMap<String,String>();
   
   //like
	private  Map<String,String> likeMap = new HashMap<String,String>();
   

	public Map<String, String> getLgEqMap() {
		return lgEqMap;
	}
	
/*	public static void addLgEqMap(String,String) {
		lgEqMap.ad
	}
	
	public Map<String, String> getGtEqMap() {
		return gtEqMap;
	}
	
	public void addGtEqMap(Map<String, String> gtEqMap) {
		this.gtEqMap = gtEqMap;
	}
	
	public Map<String, String> getLikeMap() {
		return likeMap;
	}*/
	
	public void setLikeMap(Map<String, String> likeMap) {
		this.likeMap = likeMap;
	}
   
}
