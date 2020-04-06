package com.micro.pmo.moudle.banner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.banner.service.BannerService;

@RestController
@RequestMapping("api-app/banner")
public class BannerApi {

	@Autowired
	private BannerService bannerService;
	
	@GetMapping("list")
	public Result bannerList(){
		List<String> imgUrls = bannerService.bannerImgPath();
		return Result.success(imgUrls);
	}
	
}
