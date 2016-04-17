package com.hdc.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hdc.action.base.PaginationAction;
import com.hdc.model.LoginLog;
import com.hdc.service.BaseService;
import com.hdc.util.BeanReflectionUtil;
import com.hdc.util.Constant;
import com.hdc.util.DateUtil;
import com.hdc.util.StringUtil;
/**
 * 操作用户的产品
 * @author Administrator
 *
 */
public class ServiceAction extends PaginationAction {
	
	protected Logger log = Logger.getLogger(ServiceAction.class);
	
	private BaseService baseService;
//	private DingdanService dingdanService;
//	private RecodeService recodeService;
	
	
	private String productProducerid;
	
	private File image;
	private String imageFileName;
	private String imageContentType;
	
	
	private File filedata;
	private String filedataFileName;
	
	private String startDate;
	private String  endDate;
	
	private LoginLog loginLog;
	private List<LoginLog> loginLogList;
	
	private List<LoginLog> resultList;
	
private Integer noticeId;
	
	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	
	public String list(){
		return "list";
	}
	
	
	
	public String addHtmlImg(){
		
		try{
			FileOutputStream fos = null;
			FileInputStream fis = null;
			String path=ServletActionContext.getServletContext().getRealPath("/upload");
			int index = filedataFileName.lastIndexOf(".");
			String type = filedataFileName.substring(index);
			String fileName = DateUtil.dateConvertToString(new Date(), "yyyyMMddHHmmss")+"_"+type;
			System.out.println(fileName);
            fos = new FileOutputStream(path + "\\" + fileName);
            fis = new FileInputStream(getFiledata());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            fis.close();
           // String err ="文件格式不对";
             fileName = request.getContextPath() + "/upload/" + fileName;  
            super.outPrintJson("{\"err\":\"" + "" + "\",\"msg\":\""  + fileName+ "\"}");
			}catch (IOException e) {
			
			}
		return null;
	}
	
	
	
	
	public String logList(){
		Date now = new Date();
		this.startDate = DateUtil.getMonthOneDate(now);
		this.endDate = DateUtil.dateConvertToString(now, "yyyy-MM-dd");
		return "logList";
	}
	
	public String _logList(){
		if(loginLog==null){
			loginLog = new LoginLog();
		}
		return "_logList";
	}
	/**
	 * 退出按钮
	 * @return
	 */
	public String loginOut(){
		
		try {
			HttpSession session = request.getSession();
			session.setAttribute("user",null);
			session.removeAttribute("user");
			//Constant.CURRENT_LOGIN_COUNT--;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "loginOut";
	}

	public String regist(){
		
		return null;
	}
	public String getProductProducerid() {
		return productProducerid;
	}

	public void setProductProducerid(String productProducerid) {
		this.productProducerid = productProducerid;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

//	public RecodeService getRecodeService() {
//		return recodeService;
//	}
//
//	public void setRecodeService(RecodeService recodeService) {
//		this.recodeService = recodeService;
//	}

	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

//	public Notice getNotice() {
//		return notice;
//	}
//
//	public void setNotice(Notice notice) {
//		this.notice = notice;
//	}
//
//	public Notice getNotice2() {
//		return notice2;
//	}
//
//	public void setNotice2(Notice notice2) {
//		this.notice2 = notice2;
//	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public LoginLog getLoginLog() {
		return loginLog;
	}

	public void setLoginLog(LoginLog loginLog) {
		this.loginLog = loginLog;
	}


	public List<LoginLog> getLoginLogList() {
		return loginLogList;
	}

	public void setLoginLogList(List<LoginLog> loginLogList) {
		this.loginLogList = loginLogList;
	}


	public List<LoginLog> getResultList() {
		return resultList;
	}

	public void setResultList(List<LoginLog> resultList) {
		this.resultList = resultList;
	}
	
}
