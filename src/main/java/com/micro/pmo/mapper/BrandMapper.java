package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.brand.entity.Brand;
import com.micro.pmo.moudle.brand.vo.BranchVo;
import com.micro.pmo.moudle.brand.vo.SeriesVo;

/**
 * 品牌Mapper接口
 */
public interface BrandMapper{
	
    /**
     * 查询所有Brand数据
     * @return
     */
    public List<Brand> findBrand();
    
    /**
     * 查询所有车型
     * @return
     */
    public List<Brand> brandSeriesList();
    
    /**
     * 获取通过品牌id获取品牌车型
     * @param brandId
     * @return
     */
    public List<Brand> findBrandSeriesById(@Param("brandId") String brandId);
    
    /***
     * 获取通过品牌车型id获取品牌车型详情
     * @param brandId
     * @return
     */
    public List<Brand> findBrandSeriesInfosById(@Param("brandId") String brandId);
    
    /***
     * 批量写入
     * @param branchVos
     */
	@Insert({"<script>"
			+"INSERT INTO sys_brand (brand_id,brand_name,brand_img,brand_acronym,create_time) VALUES "
			+ "<foreach collection='branchVos' item='item' separator=','>"
			+    "(#{item.id}, #{item.name}, #{item.img},#{item.firstletter},now())"
			+"</foreach>"
		+ "</script>"})
    public void batchBrand(@Param("branchVos")List<BranchVo> branchVos); 
	
	@Insert({"<script>"
			+"INSERT INTO sys_brand_series (brand_series_id,brand_name,brand_id,create_time) VALUES "
			+ "<foreach collection='seriesAll' item='item' separator=','>"
			+    "(#{item.id}, #{item.name},#{item.brand_id}, now())"
			+"</foreach>"
		+ "</script>"})
	public void batchBrandSeries(@Param("seriesAll")List<SeriesVo> seriesAll); 
	
	@Insert({"<script>"
			+"INSERT INTO sys_brand_series_info (series_info_id,brand_name,brand_series_id,create_time) VALUES "
			+ "<foreach collection='seriesInfoAll' item='item' separator=','>"
			+    "(#{item.id}, #{item.full_name},#{item.series_id}, now())"
			+"</foreach>"
		+ "</script>"})
	public void batchBrandSeriesInfo(@Param("seriesInfoAll")List<SeriesVo> seriesInfoAll);
}
