package com.micro.pmo.moudle.banner.service;

import java.util.List;

import com.micro.pmo.moudle.banner.entity.Banner;

/**
 * 轮播图Service
 */
public interface BannerService{
	
	/***
	 * 上传banner图
	 * @param pathImg
	 */
	public void uploadBanner(String pathImg);
	
	/***
	 * 删除banner图
	 * @param bannerId
	 */
	public void deleteBanner(Integer bannerId);
	
	/***
	 * 获取Bannerlist用于后台
	 * @return
	 */
	public List<Banner> bannerList();
	
	/****
	 * 前端用户使用
	 * @return
	 */
	public List<String> bannerImgPath();
}
