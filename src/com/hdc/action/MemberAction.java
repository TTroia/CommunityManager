package com.hdc.action;

import java.util.List;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Member;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;

public class MemberAction extends PaginationAction {

	private static final long serialVersionUID = 1L;
	
	private BaseService baseService;
	private CustomService customService;
	
	private Member member;
	private List<Member> resultList;
	
	public String list(){
		try {
			member.setCom_id(super.getUser().getCommunityId());
			this.resultList=this.customService.queryMemberList(member, super.getPage());
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return "list";
	}
	
	public String add(){
		
		return null;
	}
	
	public String addByFile(){
		
		return null;
	}
	
	public String update(){
		
		return null;
	}
	
	public String delete(){
		try {
			int k= this.baseService.delete(member);
			if(k>0){
				super.outPrintJson(getJsonInfo("删除成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("删除失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<Member> getResultList() {
		return resultList;
	}
	public void setResultList(List<Member> resultList) {
		this.resultList = resultList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
