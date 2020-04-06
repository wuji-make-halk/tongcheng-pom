package com.micro.pmo.moudle.brand.admin.service;

import java.util.List;

import com.micro.pmo.moudle.brand.entity.Brand;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月2日 
*/
public interface AdminBrandService {

	/**
	 *  查询品牌车型
	 * @param type
	 * @param type
	 * @return
	 */
	public List<Brand> branList(Integer type,Integer parentId);
	
}
