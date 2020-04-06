package com.micro.pmo.moudle.brand.controller;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.brand.entity.Brand;
import com.micro.pmo.moudle.brand.service.BrandService;

@Validated
@RestController
@RequestMapping("/api-app/brand")
public class BrandController {

	@Autowired
    private BrandService brandService;
	
	/**
     * 品牌列表接口,用于找车
     * @return
     */
    @GetMapping("list")
    public Result brandList(){
        List<Brand> brands = brandService.brandList();
        return Result.success(brands);
    }

    /***
     * 只返回品牌
     * @return
     */
    @GetMapping("alone-list")
    public Result aloneBrandList(){
    	Map<String,List<Brand>> brands = brandService.aloneBrandList();
    	return Result.success(brands);
    }
    /**
     * 获取通过品牌id获取品牌车型
     * @param brandId
     * @return
     */
    @GetMapping("series-list")
    public Result brandSeriesList(@NotNull(message = "品牌id不能为空") String brandId){
    	List<Brand> brands = brandService.brandSeriesList(brandId);
    	return Result.success(brands);
    }
    /***
     * 获取通过品牌车型id获取品牌车型详情
     * @param brandId
     * @return
     */
    @GetMapping("series-infos")
    public Result brandSeriesInfos(@NotNull(message = "品牌车型id不能为空") String brandId){
    	List<Brand> brands = brandService.brandSeriesInfos(brandId);
    	return Result.success(brands);
    }
    
}
