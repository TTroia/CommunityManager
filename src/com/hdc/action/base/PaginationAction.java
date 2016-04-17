package com.hdc.action.base;


import java.util.List;

import com.hdc.page.Page;



/**
 * @author Administrator
 *
 */
public class PaginationAction extends BaseAction {
	
	private final static long serialVersionUID = -7515581273533743583L;

	// 分页支持
	/**
	 * 当前页元素名称
	 */
	protected final static String PARAMS_KEY_PAGE_NO = Page.PAGE_NO;

	/**
	 * 每页显示记录个数元素名称
	 */
	protected final static String PARAMS_KEY_PAGE_LIMIT = Page.PAGE_LIMIT;

	/**
	 * 分页关键字
	 */
	protected final static String PARAMS_KEY_PAGE_OBJECT = "page";

	/**
	 * 分页列表关键字
	 */
	protected final static String PARAMS_KEY_RESULT_LIST = "resultList";

	// 当前页码
	public String pn = "1";

	// 分页显示数
	public String limit = String.valueOf(Page.LIMIT_SIZE);

	private Page page = new Page();

	// list页面显示的对象列表
	protected List<?> entities;

	private int iPageNo = 1;
	private int iLimit = Page.LIMIT_SIZE;

	// DI
	public void setPn(String pn) {

		try {
			iPageNo = Integer.parseInt(pn);

		} catch (NumberFormatException e) {
		}
		page.setPageNo(iPageNo);
	}

	public void setLimit(String limit) {

		try {
			iLimit = Integer.parseInt(limit);

		} catch (NumberFormatException e) {
		}

		page.setLimit(iLimit);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
