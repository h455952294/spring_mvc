package com.core.common.web;

/**
 * 分页实体
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年9月12日 下午4:51:26
 * @version 1.0
 */
public class PageModel {
	
	/** 定义默认一页显示的数量 */
	private static final int PAGE_SIZE = 10;
	/** 当前页码 */
	private int pageIndex;
	/** 每页显示的数量 */
	private int pageSize;
	/** 总记录条数 */
	private int recordCount;
	
	/** setter and getter method */
	public int getPageIndex() {
		// 不能小于1
		this.pageIndex =  pageIndex <= 1 ? 1 : pageIndex;
		// 不能大于总页数
		int totalPage = (this.getRecordCount() - 1) / this.getPageSize() + 1;
		this.pageIndex = this.pageIndex >= totalPage ? totalPage : this.pageIndex;
		return this.pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize <= 1 ? PAGE_SIZE : pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	// limit的第一个问号 ? 
	public int getStartRow(){
		return (this.getPageIndex() - 1) * this.getPageSize();
	}
}