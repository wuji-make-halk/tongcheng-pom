package com.micro.pmo.moudle.banner.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.BannerMapper;
import com.micro.pmo.moudle.banner.entity.Banner;
import com.micro.pmo.moudle.banner.service.BannerService;
import com.micro.pmo.moudle.user.entity.User;
import com.micro.pmo.moudle.user.utils.UserUtils;


@Service
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerMapper bannerMapper;
	
	@Override
	@Transactional
	public void uploadBanner(String pathImg) {
		User user = UserUtils.getUser();
		//排序处理
		Integer imgSort = bannerMapper.getImgSort();
		 if(imgSort == null){
			 imgSort = 1;
		 }else{
			 imgSort += 1;
		 }
		Banner banner = new Banner(pathImg, user.getUserId(), new Date(), imgSort);
		bannerMapper.insertBanner(banner);
	}

	@Override
	@Transactional
	public void deleteBanner(Integer bannerId) {
		List<Banner> banners = bannerMapper.getBannerList();
		if(banners != null && banners.size() == 1){
			throw new PmoException(ResultState.BANER_DEL_FAIL);
		}
		bannerMapper.delectBanner(bannerId);
		
	}


	@Override
	public List<Banner> bannerList() {
		
		return bannerMapper.getBannerList();
	}

	@Override
	public List<String> bannerImgPath() {
		return bannerMapper.getBannerPathList();
	}

}
