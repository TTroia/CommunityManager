package com.hdc.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Module;
import com.hdc.service.BaseService;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;

public class ModuleAction extends PaginationAction {

	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(ModuleAction.class);
	// set
	private Integer id ;
	private String name;
	private String url;
	private Integer level;
	private Integer partentId;
	private Integer orderBy;
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPartentId() {
		return partentId;
	}

	public void setPartentId(Integer partentId) {
		this.partentId = partentId;
	}

	// servcie
	private BaseService baseService;
	
	//get
	private Module module;
	private List<Module> resultList;
	
	/**
	 * 取的列表
	 * @return
	 */
	public String list(){
		
		try {
			Module module = new Module();
			//query fu Id
			module.setPartentId(0);
			List<Module> tempList =  this.baseService.getObjectList(module, null);
			resultList = new ArrayList<Module>();
			if(tempList!=null && tempList.size()>0){
				Collections.sort(tempList);//排好序
				for(Module vo :tempList){
					resultList.add(vo);
					module = new Module();
					module.setPartentId(vo.getId());
					List<Module> tempList2 =  this.baseService.getObjectList(module, null);
					Collections.sort(tempList2);//排好序
					resultList.addAll(tempList2);
				}
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
		}
		
		return "list";
		
	}
	
	/**
	 * 添加菜单
	 * @return
	 */
	public String inAdd(){
		
		try {
			Module module = new Module();
			module.setId(partentId);
			this.module  = (Module) this.baseService.getObjectById(module);
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		
		return  "inAdd";
	}
	
	/**
	 * 添加信息
	 */
	public String add(){
		
		try {
			if(name.equals("")||level.equals("")||url.equals("")){
				super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
				return null;
			}
			Module module = new Module();
			module.setCreateDate(new Date());
			module.setName(name);
			module.setLevel(level);
			module.setUrl(url);
			module.setUserId(super.getUserId());
			module.setPartentId(partentId);
			module.setOrderBy(orderBy);
			int  k  = this.baseService.insert(module);
			if(k>0){
				super.outPrintJson(getJsonInfo("添加成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
		}
		
		return  null;
	}
	
	public String delete(){
		
		try {
			
			Module vo = new Module();
			vo.setPartentId(id);
			List list   = this.baseService.getObjectList(vo, null);
			if(list!=null && list.size()>0){
				super.outPrintJson(getJsonInfo("删除失败,此菜单下有子菜单！", Constant.ERROR));
				return null;
			}
			
			Module module = new Module();
			module.setId(id);
			int  k  = this.baseService.delete(module);
			
			if(k>0){
				super.outPrintJson(getJsonInfo("删除成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("删除失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("添加失败", Constant.ERROR));
		}
		
		return  null;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String edit(){
		
		try {
			Module vo = new Module();
			vo.setId(id);
			this.module  = (Module) this.baseService.getObjectById(vo);
			// 取父级菜单
			if(module.getLevel()!=1){
				vo.setId(module.getPartentId());
				vo = (Module) this.baseService.getObjectById(vo);
				this.module.setPartentName(vo.getName());
			}else{
				this.module.setPartentName("一级菜单");
			}
			
			super.outPrintJson(getJsonInfo_byUser(Constant.SUCCESS, "id",module.getId(),"level",module.getLevel(),
					"partentId",module.getPartentId(),"partentName",module.getPartentName(),"name",module.getName(),"url",module.getUrl(),
					"orderBy",module.getOrderBy()));
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		
		return  null;
	}

	/**
	 * 添加信息
	 */
	public String update(){
		
		try {
			Module module = new Module();
			module.setCreateDate(new Date());
			module.setName(name);
			module.setLevel(level);
			module.setUrl(url);
			module.setUserId(super.getUserId());
			module.setPartentId(partentId);
			module.setOrderBy(orderBy);
			module.setId(id);
			int  k  = this.baseService.update(module);
			if(k>0){
				super.outPrintJson(getJsonInfo("更新成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("更新失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
			super.outPrintJson(getJsonInfo("更新失败", Constant.ERROR));
		}
		
		return  null;
	}
	
	
	public Module getModule() {
		return module;
	}

	public static void setLog(Logger log) {
		ModuleAction.log = log;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public List<Module> getResultList() {
		return resultList;
	}
	
}
