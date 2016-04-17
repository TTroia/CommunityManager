package com.hdc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.Activity;
import com.hdc.service.BaseService;
import com.hdc.service.CustomService;
import com.hdc.util.Constant;
import com.hdc.util.DateUtil;
import com.hdc.util.StringUtil;

public class ActivityAction extends PaginationAction{

	private static final long serialVersionUID = 1L;
	
	private BaseService baseService;
	private CustomService customService;
	
	private Activity activity;
	private List<Activity> resultList;
	
	private File file;
	private String fileFileName; //文件名
	private Integer id;
	private String url;

	 
	
	public String list(){
		return "list";
	}
	
	public String _list(){
		if(activity==null){
			activity = new Activity();
		}
		if (super.getUser().getRoleId() == 1) {
			activity.setApplyState(1);   //如果是校社联的话，只能查看到系社联已经确认的
		} else if (super.getUser().getRoleId() == 2) {
			activity.setPartof(super.getUser().getUsername());  //如果是系社联的话，只能查看本系的，系社联的名字就是活动及社团的partof属性
		} else if (super.getUser().getRoleId() == 3) {
			activity.setCom_id(super.getUser().getCommunityId());   //如果是社团的话，只能查看到自己社团的
		}
		this.resultList = this.customService.queryActivityList(activity,
				super.getPage());
		return "_list";
	}

	public String delete(){
		try {
			int k =this.baseService.delete(activity);
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
	
	public String add(){
		try {
			activity.setApplyUser(super.getUser().getUsername());
			activity.setApplyDate(new Date());
			activity.setApplyState(0);
			activity.setApplyState2(0);
			activity.setCompleteState(0);
			activity.setCom_id(super.getUser().getCommunityId());
			activity.setCom_name(super.getUser().getCommunityName());
			activity.setPartof(super.getUser().getPartof());
			int k = this.baseService.insert(activity);
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
			Activity act = (Activity) this.baseService.getObjectById(this.activity);
			act.setName(this.activity.getName());
			act.setLocation(this.activity.getLocation());
			act.setTeacher(this.activity.getTeacher());
			act.setHoldDate(this.activity.getHoldDate());
			act.setTableAdd(this.activity.getTableAdd());
			int k= this.baseService.update(act);
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
	
	//系社联确认
	public String xi_ok(){
		try {
			if(this.activity.getApplyState()==0){
				this.activity.setApplyState(1);
				this.activity.setApproveDate(new Date());
				this.activity.setApproveUser(super.getUser().getUsername());
			}else if(this.activity.getApplyState()==1){
				this.activity.setApplyState(0);
				this.activity.setApproveDate(new Date());
				this.activity.setApproveUser(super.getUser().getUsername());
			}
			
			int k=this.baseService.update(activity);
			if(k>0){
				super.outPrintJson(getJsonInfo("确认成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("确认失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	//校社联确认
	public String xiao_ok(){
		try {
			if(this.activity.getApplyState2()==0){
				this.activity.setApplyState2(1);
				this.activity.setApproveDate2(new Date());
				this.activity.setApproveUser2(super.getUser().getUsername());
			}else if(this.activity.getApplyState2()==1){
				this.activity.setApplyState2(0);
				this.activity.setApproveDate2(new Date());
				this.activity.setApproveUser2(super.getUser().getUsername());
			}
			
			int k=this.baseService.update(activity);
			if(k>0){
				super.outPrintJson(getJsonInfo("确认成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("确认失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	//活动完成状态修改
	public String complete(){
		try {
			if(this.activity.getCompleteState()!=1){
				this.activity.setCompleteState(1);
			}else if(this.activity.getCompleteState()==1){
				this.activity.setCompleteState(0);
			}
			int k=this.baseService.update(activity);
			if(k>0){
				super.outPrintJson(getJsonInfo("确认成功", Constant.SUCCESS));
			}else{
				super.outPrintJson(getJsonInfo("确认失败", Constant.ERROR));
			}
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}
		return null;
	}
	
	//上传word版申请书
	public String upload(){
		System.out.println("id"+id);
		String path=request.getSession().getServletContext().getRealPath("/upload");
		String fileName= super.getUser().getCommunityName()+"_"+DateUtil.dateConvertToString(new Date(), "yyyy-MM-dd")+"_"+fileFileName;
		try {
			File dest =  new File(path,fileName);
			FileInputStream fis = new FileInputStream(this.file);
			FileOutputStream fos= new FileOutputStream(dest);
			
			byte[] b = new byte[1024];
			int len=0;
			while((len=fis.read(b))!=-1){
				fos.write(b, 0, len);
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			log.error(StringUtil.outputException(e));
		}finally{
			File exFile = new File(path,fileName);
			if(exFile.exists()){
				activity.setTableAdd(exFile.getPath());
				this.baseService.update(activity);
				super.outPrintJson(getJsonInfo("上传成功", Constant.SUCCESS));
			}else {
				super.outPrintJson(getJsonInfo("上传失败", Constant.ERROR));
			}
		}
		return null;
	}
	
	
	//下载方法  社联下载申请书
	public String download(){
		try {
			url=new String(url.getBytes("iso8859-1"),"UTF-8");
			System.out.println("url="+url);
			File dFile = new File(url.trim());
			System.out.println(dFile.exists());
			System.out.println(dFile.length());
			
			this.fileFileName=url.substring(url.lastIndexOf("/")+1);
			
			System.out.println("fileName==="+fileFileName);
			FileInputStream fis = new FileInputStream(dFile);
			response.setContentType("multipart/form-data");
			response.setHeader("content-disposition", "attachment;filename=" + new String( fileFileName.getBytes("gbk"), "ISO8859-1" ));
			OutputStream out=response.getOutputStream();
			int len = 0;
			byte buffer[] = new byte[1024];
			while((len=fis.read(buffer))>0){
	            //输出缓冲区的内容到浏览器，实现文件下载
				System.out.println("len-"+len);
	            out.write(buffer, 0, len);
	            out.flush();
	        }
			
			fis.close();
			out.close();
			
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
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public List<Activity> getResultList() {
		return resultList;
	}
	public void setResultList(List<Activity> resultList) {
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

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	
	

}
