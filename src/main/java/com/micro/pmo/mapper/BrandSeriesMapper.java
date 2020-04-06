package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.brand.entity.BrandSeries;

/**
 * 品牌系列Mapper接口
 */
public interface BrandSeriesMapper{
	
    /**
     * 根据品牌Id查询型号
     * @param brandId
     * @return
     */
    public List<BrandSeries> findByBrandList(@Param("brandId")Integer brandId);
}
