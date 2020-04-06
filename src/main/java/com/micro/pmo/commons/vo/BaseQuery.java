package com.micro.pmo.commons.vo;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月15日
 */
public class BaseQuery {

	private Integer pageNumKey;

	private Integer pageSizeKey;
 
	public Integer getPageNumKey() {
		if (pageNumKey == null) {
			setPageNumKey(1);
		}
		return pageNumKey;
	}

	public void setPageNumKey(Integer pageNumKey) {
		this.pageNumKey = pageNumKey;
	}

	public Integer getPageSizeKey() {
		if (pageSizeKey == null) {
			setPageSizeKey(10);
		}
		return pageSizeKey;
	}

	public void setPageSizeKey(Integer pageSize) {
		this.pageSizeKey = pageSize;
	}

	/**
	 * 获取每页记录数
	 * @param minPageSizeKey 最小每页记录数
	 * @return
	 */
	public Integer getPageSizeKey(Integer minPageSizeKey) {
		if(getPageSizeKey() < minPageSizeKey ) {
			setPageSizeKey(minPageSizeKey);
		}
		return pageSizeKey;
	}
}
