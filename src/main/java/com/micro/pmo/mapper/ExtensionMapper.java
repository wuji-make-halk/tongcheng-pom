package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.extension.entity.Extension;

/**
 * 推广费用配置表Mapper接口
 */
public interface ExtensionMapper{
	/**
	 * 新增Extension
	 * @param sysExtension
	 * @return
	 */
 	public int insertExtension(Extension sysExtension);
 
 	/**
 	 * 根据主键修改Extension
 	 * @param sysExtension
 	 * @return
 	 */
 	public int updateExtensionById(Extension sysExtension);
 	
 	/**
 	 * 查询 Extension
 	 * @return
 	 */
 	public List<Extension> extensionList();
 	
 	
	
	/***
	 * 根据ID查询SysExtension
	 **/
	public Extension getExtensionById(int extensionId);
}
