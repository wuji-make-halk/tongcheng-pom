package com.micro.pmo.moudle.share.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.customer.entity.CusStore;
import com.micro.pmo.moudle.share.entity.Share;
import com.micro.pmo.moudle.share.service.ShareService;
import com.micro.pmo.moudle.store.entity.Store;
import com.micro.pmo.moudle.store.vo.ShareQuery;

@Validated
@RestController
@RequestMapping("api-app/share")
public class ShareController {

	
	@Autowired
	private ShareService shareService;
	
	/***
	 * 微店员工列表
	 * @return
	 */
	@GetMapping("staff")
	public Result storeStaff(BaseQuery baseQuery){
		List<CusStore> cusStores = shareService.storeCusList(baseQuery);
		return Result.success(cusStores);
	}
	
	/***
	 * 分享列表
	 * @param cusId
	 * @return
	 */
	@GetMapping("list")
	public Result shareList(@NotNull(message="用户id不能为空")Integer cusId){
		List<Share> shares = shareService.shareList(cusId);
		return Result.success(shares);
	}
	/**
	 * 分享分页查询
	 * @param baseQuery
	 * @return
	 */
	@GetMapping("page")
	public Result sharePage(ShareQuery shareQuery){
		PageInfo<Share> info = shareService.sharePage(shareQuery);
		return Result.success(info);
	}
	
	/***
	 * 分享详情
	 * @param shareId
	 * @return
	 */
	@GetMapping("info")
	public Result shareInfo(@NotNull(message="分享id不能为空")Integer shareId){
		Map<String, Object> map = shareService.shareInfo(shareId);
		return Result.success(map);
	}
	
	/***以下都是分享查询****/
	/**
	 * 分享微店详情
	 * @param storeId
	 * @param cusId
	 * @param cusHead
	 * @param cusNick
	 * @return
	 */
	@GetMapping("store/info")
	public Result shareStoreInfo(@NotNull(message="微店id参数不能为空") Integer storeId,
			Integer cusId,String cusHead,String cusNick,HttpServletRequest request){
		String ip = CommonUtils.getIpAddr(request);
		Store store = shareService.shareStoreInfo(storeId, cusId, cusHead, cusNick,ip); 
		return Result.success(store);
	}
	/**
	 * 微店车源列表
	 * @param storeId
	 * @param carIds
	 * @param cusId
	 * @param cusHead
	 * @param cusNick
	 * @return
	 */
	@GetMapping("store/car-list")
	public Result shareStoreCarList(@NotNull(message="微店id参数不能为空")Integer storeId,@NotNull(message="微店车辆ids不能为空")String carIds){
		 List<FindCarVO> list = shareService.shareStoreCarList(storeId, carIds);
		return Result.success(list);
	}
	/***
	 * 微店车辆详情
	 * @param storeId
	 * @param carId
	 * @return
	 */
	@GetMapping("store/car-info")
	public Result shareStoreCarInfo(@NotNull(message="微店id参数不能为空")Integer storeId,@NotNull(message="微店车辆id不能为空")Integer carId,
			Integer cusId,String cusHead,String cusNick,HttpServletRequest request){
		String ip = CommonUtils.getIpAddr(request);
		Map<String, Object> map = shareService.shareStoreCarInfo(storeId, carId, cusId, cusHead, cusNick,ip);
		return Result.success(map);
	}
	/**
	 * 平台车源列表
	 * @param carIds
	 * @return
	 */
	@GetMapping("platform/car-list")
	public Result sharePlatformCarList(@NotNull(message="微店车辆ids不能为空")String carIds){
		List<FindCarVO>  list = shareService.sharePlatformCarList(carIds);
		return Result.success(list);
	}
	/**
	 * 平台车辆详情
	 * @param carId
	 * @param cusId
	 * @param cusHead
	 * @param cusNick
	 * @return
	 */
	@GetMapping("platform/car-info")
	public Result sharePlatformCarInfo(@NotNull(message="微店车辆id不能为空")Integer carId,
			Integer cusId,String cusHead,String cusNick,HttpServletRequest request){
		String ip = CommonUtils.getIpAddr(request);
		Map<String, Object> map = shareService.sharePlatformCarInfo(carId, cusId, cusHead, cusNick,ip);
		return Result.success(map);
	}
}
