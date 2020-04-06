package com.micro.pmo.moudle.banner.admin;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.banner.entity.Banner;
import com.micro.pmo.moudle.banner.service.BannerService;

@Validated
@RestController
@RequestMapping("api-pc/banner")
public class BannerAdminApi {

	@Autowired
	private BannerService bannerService;
	
	/***
	 * banner上传
	 * @param multipartFile
	 * @return
	 */
	@PostMapping()
	public Result uploadBanner(@Valid @RequestBody Banner banner){
		 bannerService.uploadBanner(banner.getImgUrl());
		return Result.success();
	}
	
	/***
	 * banner 列表
	 * @return
	 */
	@GetMapping("list")
	public Result bannerList(){
		List<Banner> banners = bannerService.bannerList();
		return Result.success(banners);
	}
	/***
	 * 删除banner
	 * @param bannerId
	 * @return
	 */
	@DeleteMapping("del")
	public Result deleteBanner(@NotNull(message="bannerId不能为空")Integer bannerId){
		bannerService.deleteBanner(bannerId);
		return Result.success();
	}
}
