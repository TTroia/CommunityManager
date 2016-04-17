package com.hdc.page;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * 局部刷新的分页查询页签
 */
public class DynamicPageTag extends BodyTagSupport {

	private static final long serialVersionUID = -2756529597196138810L;

	private static String FIRST = "首页";
	private static String PREV = "上一页";
	private static String NEXT = "下一页";
	private static String LAST = "尾页";
	
	private JspWriter out = null;

	/** 页脚是否包含跳转框 */
	private boolean hasGo = false;

	private boolean down = false;

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	/** 页面记录当前页的id */
	private String pageId = Page.PAGE_NO;

	/** 页面每页显示记录数的id */
	private String limitId = Page.PAGE_LIMIT;

	/** 设定每页显示数 */
	private String limit = "";

	/** request中的key */
	private String view = "page";

	/** AJAX操作的目标form名称 */
	private String form;

	/** 目标结果刷新的区域名称 */
	private String resultDiv;

	/** 合并列 */
	private String colspan;

	/** 分页前，调用js */
	private String before;

	/** 分页后，调用js */
	private String behind;

	public void setBefore(String before) {
		this.before = before;
	}

	public void setBehind(String behind) {
		this.behind = behind;
	}

	public void setLimitId(String limitId) {

		if (null == limitId || "".equals(limitId.trim())) {

			return;
		}
		this.limitId = limitId;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public void setView(String view) {

		if (null == view || "".equals(view.trim())) {

			return;
		}
		this.view = view;
	}

	public void setHasGo(String hasGo) {

		this.hasGo = Boolean.parseBoolean(hasGo);
	}

	public void setForm(String form) {
		this.form = form;
	}

	public void setResultDiv(String resultDiv) {
		this.resultDiv = resultDiv;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		// 获取request
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		Page page = (Page) request.getAttribute(view);

		// 从request中获取需要的值，前提要求Action方法中必须在request中设置相应的属性值
		int pageNo = 1;
		int totalPages = 0;
		int totalRows = 0;

		int limit = Page.LIMIT_SIZE; // pageSize

		if (page != null) {

			pageNo = page.getPageNo();
			totalPages = page.getTotalPages();
			totalRows = page.getTotalRows();

			if (null == this.limit || "".equals(this.limit.trim())) {
				limit = page.getLimit();
			}
		}

		out = pageContext.getOut();

		try {

			out.print("<tr height='30'><td colspan='" + colspan
					+ "' align='center'><table width='100%' border='0'><tr>");

			printOutFront(pageNo, request);
			printOutBack(pageNo, totalPages, request);

			out.print("<td align='left' style='border:0px;'>页次：");

			if (0 != totalRows) {
				// 没有查询出记录的情况下，永不显示跳转框
				
				if (hasGo) {
					printOutGo(pageNo, totalPages);
				} else {
					out.print(pageNo);
				}
			}

			out.print("/" + totalPages + "页　共有" + totalRows + "条  " + limit
					+ "条/页</td>");

			out.print("</tr></table></td></tr>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * 组装标签前部分
	 * 
	 * @param pageNo
	 * @param request
	 * @throws JspException
	 */
	private void printOutFront(int pageNo, HttpServletRequest request)
			throws JspException {
		try {

			StringBuilder outString = new StringBuilder();
			outString.append("<td  style='border:0px;text-align:right;'>");
			
			if (pageNo == 1) {

				// 如果是首页
				outString.append(FIRST);
				outString.append("&nbsp;&nbsp;");
				outString.append(PREV);

			} else {
				Integer prePage = new Integer(pageNo == 1 ? pageNo : pageNo - 1);
				outString.append("<a href=\"javascript:void(0);\" onclick=\"");
				outString.append("$('#");
				outString.append(form);
				outString.append("').setDynamicPage('1', '");
				outString.append(resultDiv);
				outString.append("','");
				outString.append(form + pageId);
				outString.append("','");
				outString.append(before);
				outString.append("','");
				outString.append(behind);
				outString.append("')\">");
				outString.append(FIRST);
				outString.append("</a>");
				outString.append("&nbsp;&nbsp;");

				outString.append("<a href=\"javascript:void(0);\" onclick=\"");
				outString.append("$('#");
				outString.append(form);
				outString.append("').setDynamicPage('");
				outString.append(prePage);
				outString.append("','");
				outString.append(resultDiv);
				outString.append("','");
				outString.append(form + pageId);
				outString.append("','");
				outString.append(before);
				outString.append("','");
				outString.append(behind);
				outString.append("')\">");
				outString.append(PREV);
				outString.append("</a>");
			}
			out.print(outString.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	/**
	 * 组装标签后部分
	 * 
	 * @param pageNo
	 * @param totalPage
	 * @param request
	 * @throws JspException
	 */
	private void printOutBack(int pageNo, int totalPages,
			HttpServletRequest request) throws JspException {

		try {

			StringBuilder outString = new StringBuilder();

			if (pageNo == totalPages || totalPages == 0 || totalPages == 1) {

				outString.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				outString.append(NEXT);
				outString.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				outString.append(LAST);

			} else {

				Integer nextPage = new Integer(
						pageNo == totalPages ? totalPages : pageNo + 1);

				outString.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				outString.append("<a href=\"javascript:void(0);\" onclick=\"");
				outString.append("$('#");
				outString.append(form);
				outString.append("').setDynamicPage('");
				outString.append(nextPage);
				outString.append("','");
				outString.append(resultDiv);
				outString.append("','");
				outString.append(form + pageId);
				outString.append("','");
				outString.append(before);
				outString.append("','");
				outString.append(behind);
				outString.append("')\">");
				outString.append(NEXT);
				outString.append("</a>");
				outString.append("&nbsp;&nbsp;&nbsp;&nbsp;");

				outString.append("<a href=\"javascript:void(0);\" onclick=\"");
				outString.append("$('#");
				outString.append(form);
				outString.append("').setDynamicPage('");
				outString.append(totalPages);
				outString.append("','");
				outString.append(resultDiv);
				outString.append("','");
				outString.append(form + pageId);
				outString.append("','");
				outString.append(before);
				outString.append("','");
				outString.append(behind);
				outString.append("')\">");
				outString.append(LAST);
				outString.append("</a>");
			}

			outString.append("</td>");
			out.print(outString.toString());

		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	/**
	 * 组装标签其他部分
	 * 
	 * @param pageNo
	 * @param totalPage
	 * @throws JspException
	 */
	private void printOutGo(int pageNo, int totalPages) throws JspException {
		try {

			StringBuilder outString = new StringBuilder();
			outString
					.append("<input id='dkglakdir' type='text' style='height:16px;width:36px;text-align:center' maxlength='5' title='回车可跳转' onKeyUp=\"value=value.replace(/[^0-9]/g,'');if(event.keyCode==13)if(value<1){$('#");
			outString.append(form);
			outString.append("').setDynamicPage(1,'");
			outString.append(resultDiv);
			outString.append("','");
			outString.append(form + pageId);
			outString.append("','");
			outString.append(before);
			outString.append("','");
			outString.append(behind);
			outString.append("')}else if(value>Number(alt)){$('#");
			outString.append(form);
			outString.append("').setDynamicPage(alt,'");
			outString.append(resultDiv);
			outString.append("','");
			outString.append(form + pageId);
			outString.append("','");
			outString.append(before);
			outString.append("','");
			outString.append(behind);
			outString.append("')}else{$('#");
			outString.append(form);
			outString.append("').setDynamicPage(value,'");
			outString.append(resultDiv);
			outString.append("','");
			outString.append(form + pageId);
			outString.append("','");
			outString.append(before);
			outString.append("','");
			outString.append(behind);
			outString.append("')}\" value='");
			outString.append("");
			outString.append(pageNo);
			outString.append("' alt='");
			outString.append(totalPages);
			outString.append("' />");

			out.print(outString.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
	}
	
}