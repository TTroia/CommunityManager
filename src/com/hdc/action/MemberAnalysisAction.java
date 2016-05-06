package com.hdc.action;

import java.util.List;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Analysis_member;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;

public class MemberAnalysisAction extends PaginationAction {

	private static final long serialVersionUID = 1L;
	
	private BaseService baseService;
	private CustomService customService;
	
	public String list(){
		return "list";
	}
	
	public String getData(){
		List<Analysis_member> list= this.customService.queryAnalysis_members(new Analysis_member());
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		for(int i=0;i<list.size();i++){
			sb.append("\""+list.get(i).getCom_name()+"\":\"");
			sb.append(list.get(i).getNum()+"\",");
		}
		sb.append("}");
		sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",")+1, "");
		System.out.println(sb.toString());
		System.out.println("json:"+sb.toString());
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
