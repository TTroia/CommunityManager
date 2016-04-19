package com.hdc.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

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
	private File file;
	private String fileName;
	
	public String list(){
		return "list";
	}
	
	public String _list(){
		try {
			member.setCom_id(super.getUser().getCommunityId());
			this.resultList=this.customService.queryMemberList(member, super.getPage());
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return "_list";
	}
	
	public String add(){
		try {
			this.member.setCom_id(super.getUser().getCommunityId());
			this.member.setCom_name(super.getUser().getCommunityName());
			this.member.setCreateDate(new Date());
			this.member.setCreateUser(super.getUserId());
			int k = this.baseService.insert(this.member);
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
	
	public String addByFile(){
		try {
			System.out.println("file="+file);
			if(null!=file&&file.length()>0){
				Workbook book = Workbook.getWorkbook(file);
				//获得一个工作表对象
				Sheet sheet = book.getSheet(0);
				int ret=0;
				//得到单元格 姓名  性别  学号  年级  系别 专业
				Member mb = new Member();
				List<Member> allMembers = this.baseService.getObjectList(mb, null);
				Map<String, Member> allMap=new HashMap<String, Member>();
				if(allMembers!=null&&allMembers.size()>0){
					for (Member m : allMembers) {
						allMap.put(m.getSno(), m);
					}
				}
				for(int j=1;j<sheet.getRows();j++){
					if(null==sheet.getCell(0,j)){
						continue;
					}
					mb.setSno(StringUtil.StringTrim(sheet.getCell(2,j).getContents()));
					if(allMap.get(mb.getSno())!=null){
						log.info("已经添加过此人");
					}else {
						mb.setName(StringUtil.StringTrim(sheet.getCell(0,j).getContents()));
						if(StringUtil.StringTrim(sheet.getCell(1,j).getContents()).equals("男")){
							mb.setSex(1);
						}else {
							mb.setSex(0);
						}
						mb.setGrade(StringUtil.StringTrim(sheet.getCell(3,j).getContents()));
						mb.setDepartment(StringUtil.StringTrim(sheet.getCell(4,j).getContents()));
						mb.setMajor(StringUtil.StringTrim(sheet.getCell(5,j).getContents()));
						mb.setPhone(StringUtil.StringTrim(sheet.getCell(6,j).getContents()));
						mb.setQq(StringUtil.StringTrim(sheet.getCell(7,j).getContents()));
						mb.setCom_id(super.getUser().getCommunityId());
						mb.setCom_name(super.getUser().getCommunityName());
						mb.setCreateDate(new Date());
						mb.setCreateUser(super.getUserId());
						int k = this.baseService.insert(mb);
						 if(k>0){
		                    	log.info("插入成员成功");
		                    	ret++;
		                 }
					}
				}
				book.close();
				super.outPrintJson(super.getJsonInfo("导入成功", Constant.SUCCESS));
				
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	public String update(){
		try {
			int k = this.baseService.update(this.member);
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
