package com.micro.pmo.moudle.extension.admin.service;

import java.util.List;

import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.extension.entity.Extension;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月4日 
*/
public interface AdminExtensionService {

	/**
	 *  添加SysExtension
	 * @param sysExtension
	 * @return
	 */
	public void saveExtension(Extension extension);
  
	/**
	 * 修改SysExtension 根据Id
	 * @param sysExtension
	 * @return
	 */
	public void updateExtensionById(Extension extension);
	
	/**
	 * 分页查询推广配置
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	List<Extension> extensionList(BaseQuery query);
}