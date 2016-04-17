package com.hdc.page;


import java.util.List;

/**
 * 分页对象
 */
public class Page {

	/**
	 * 每页记录数目
	 */
	public static final int LIMIT_SIZE = 40;
	public static final String PAGE_NO = "pn";
	public static final String PAGE_LIMIT = "limit";

	@SuppressWarnings("static-access")
	private int limit = this.LIMIT_SIZE;

	// 记录总数
	private int totalRows = 0;

	// 当前页码
	private int pageNo = 1;

	// 结果集存放List
	private List<?> resultList;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	// 计算总页数
	public int getTotalPages() {
		int totalPages;

		if (0 == totalRows % limit) {

			totalPages = totalRows / limit;

		} else {

			totalPages = (totalRows / limit) + 1;
		}

		return (0 == totalPages) ? 1 : totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getOffset() {
		return (pageNo - 1) * limit;
	}

	public int getEndIndex() {

		if (getOffset() + limit > totalRows) {

			return totalRows;

		} else {

			return getOffset() + limit;
		}
	}

	public int getPageNo() {

		if (pageNo > getTotalPages()) {

			pageNo = getTotalPages();
		}

		if (0 >= pageNo) {

			pageNo = 1;
		}

		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
