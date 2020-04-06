package com.micro.pmo.moudle.brand.service;

import java.util.List;
import java.util.Map;

import com.micro.pmo.moudle.brand.entity.Brand;

/**
 * 品牌Service
 */
public interface BrandService{
	
    /**
     * 返回品牌列表
     * @return
     */
    public List<Brand> brandList();

    /**
     * 同步数据
     */
    public void batchBrand();
    
    /***
     * 单独获取品牌
     * @return
     */
    public Map<String,List<Brand>> aloneBrandList();
    
    /***
     * 获取通过品牌id获取品牌车型
     * @param brandId
     * @return
     */
    public List<Brand> brandSeriesList(String brandId);
    
  /***
   * 获取通过品牌车型id获取品牌车型详情
   * @param brandId
   * @return
   */
    public List<Brand> brandSeriesInfos(String brandId);
}
