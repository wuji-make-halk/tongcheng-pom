package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.banner.entity.Banner;

/**
 * 轮播图Mapper接口
 */
public interface BannerMapper{
	
	/**写入Banner信息***/
	public int insertBanner(Banner banner);
	
	/***删除banner**/
	@Delete("DELETE FROM banner WHERE banner_id = #{bannerId}")
	public int delectBanner(@Param("bannerId")Integer bannerId);
	
	/***后台使用***/
	public List<Banner> getBannerList();
	
	/**app使用***/
	public List<String> getBannerPathList();
	/*获取最后的排序参数***/
	public Integer getImgSort();
}
