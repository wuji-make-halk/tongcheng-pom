package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.vip.entity.Vip;

/**
 * SysVip 
 * @author wenhaofan
 * @createtime 
 */
public interface VipMapper {
	
	/**
	 *  保存SysVip
	 * @param sysVip
	 * @return
	 */
 	public int insertVip(Vip sysVip);
 	
 	/**
 	 * 修改sysVip
 	 * @param sysVip
 	 * @return
 	 */
	public int updateVipById(Vip sysVip);
	
	/**
	 * 获取SysVip列表
	 * @return
	 */
	public List<Vip> vipList();
	
	/**
	 * 根据Id获取SysVip
	 * @param id
	 * @return
	 */
	public Vip getVipById(Integer id);

}