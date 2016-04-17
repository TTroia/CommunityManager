package com.hdc.action;

import java.util.Date;
import java.util.List;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Nature;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;

public class NatureAction extends PaginationAction{

	private static final long serialVersionUID = 1L;
	
	private BaseService baseService;
	private CustomService customService;
	
	private List<Nature> resultList;
	private Nature nature;
	
	public String list(){
		return "list";
	}
	
	public String _list(){
		if(nature==null){
			nature= new Nature();
		}
		this.resultList=this.customService.queryNatureList(nature, super.getPage());
		return "_list";
	}
	
	public String delete(){
		try {
			int k =this.baseService.delete(nature);
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
	
	public String update(){
		try {
			int k= this.baseService.update(nature);
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
	
	public String add(){
		try {
			int k = this.baseService.insert(nature);
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
	public List<Nature> getResultList() {
		return resultList;
	}
	public void setResultList(List<Nature> resultList) {
		this.resultList = resultList;
	}
	public Nature getNature() {
		return nature;
	}
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
