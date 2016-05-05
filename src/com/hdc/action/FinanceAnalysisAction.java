package com.hdc.action;

import java.util.List;

import com.hdc.action.base.BaseAction;
import com.hdc.action.base.PaginationAction;
import com.hdc.model.Analysis_finance;
import com.hdc.model.Community;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;

public class FinanceAnalysisAction extends PaginationAction {

	private static final long serialVersionUID = 1L;
	
	private BaseService baseService;
	private CustomService customService;
	public String list(){
		
		return "list";
	}
	
	/**
	 * 饼状图，简单统计收支情况，根据登录的ID判断属于哪个社团，将过去所有时间的收支状态显示出来
	 * 饼状图分为两块，一块为收入，一块为支出
	 * @return
	 */
	public String pie(){
		List<Analysis_finance> afList = this.customService.queryAnalysis_finances(new Analysis_finance(), String.valueOf(super.getUser().getCommunityId()));
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\""+"支出"+"\":\"");
		sb.append(afList.get(0).getExpend()+"\",");
		sb.append("\""+"收入"+"\":\"");
		sb.append(afList.get(0).getIncome()+"\"");
//		for(int i=0;i<afList.size();i++){
//			sb.append("\""+afList.get(i).getExpend()+"\":\"");
//			sb.append(afList.get(i).getIncome()+"\",");
//		}
		sb.append("}");
		System.out.println(sb.toString());
		super.outPrintJson(sb.toString());
		return null;
	}
	
	
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public CustomService getCustomService() {
		return customService;
	}
	public void setCustomService(CustomService customService) {
		this.customService = customService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
