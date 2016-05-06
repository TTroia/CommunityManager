package com.hdc.action;

import java.util.Date;
import java.util.List;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Finance;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;

public class FinanceAction extends PaginationAction {

	private static final long serialVersionUID = 1L;
	
	private BaseService baseService;
	private CustomService customService;
	
	private Finance finance;
	private List<Finance> resultList;
	private Integer way;
	
	public String list(){
		return "list";
	}
	
	public String _list(){
		try {
			if(null==this.finance){
				finance=new Finance();
			}
			finance.setCom_id(super.getUser().getCommunityId());
			this.resultList=this.customService.queryFinanceList(finance, null);
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return "_list";
	}
	
	public String add(){
		try {
			Finance fa= new Finance();
			fa.setCom_id(super.getUser().getCommunityId());
			List<Finance> tempList = this.customService.queryFinanceList(fa, null);
			if(tempList.size()>0){
				if(way==0){
					this.finance.setBalance(tempList.get(tempList.size()-1).getBalance()+this.finance.getSpend());
				}else if(way==1){
					this.finance.setBalance(tempList.get(tempList.size()-1).getBalance()-this.finance.getSpend());
					this.finance.setSpend(0-finance.getSpend());
				}
			}else {
				this.finance.setBalance(this.finance.getSpend());
			}
			
			this.finance.setCom_id(super.getUser().getCommunityId());
			this.finance.setCom_name(super.getUser().getCommunityName());
			this.finance.setEditDate(new Date());
			this.finance.setEditUser(this.getUser().getUsername());
			int k = this.baseService.insert(this.finance);
			if(k>0){
				super.outPrintJson(getJsonInfo("添加成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	public String update(){
		try {
			Finance fa= new Finance();
			fa.setCom_id(super.getUser().getCommunityId());
			List<Finance> tempList = this.customService.queryFinanceList(fa, null);
			Finance tmpFinance=(Finance) this.baseService.getObjectById(this.finance);
			if(tempList.size()>0){
				if(way==0){
					tmpFinance.setSpend(this.finance.getSpend());
					tmpFinance.setBalance(tempList.get(tempList.size()-2).getBalance()+this.finance.getSpend());
				}else if(way==1){
					tmpFinance.setSpend(0-this.finance.getSpend());
					tmpFinance.setBalance(tempList.get(tempList.size()-2).getBalance()-this.finance.getSpend());
				}
			}else if(tempList.size()<=0){
				
			}
			
			tmpFinance.setEditDate(new Date());
			tmpFinance.setEditUser(this.getUser().getUsername());
			int k = this.baseService.update(tmpFinance);
			if(k>0){
				super.outPrintJson(getJsonInfo("修改成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("修改失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	public String delete(){
		try {
			int k = this.baseService.delete(this.finance);
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
	public Finance getFinance() {
		return finance;
	}
	public void setFinance(Finance finance) {
		this.finance = finance;
	}
	public List<Finance> getResultList() {
		return resultList;
	}
	public void setResultList(List<Finance> resultList) {
		this.resultList = resultList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setWay(Integer way) {
		this.way = way;
	}
	
	
}
