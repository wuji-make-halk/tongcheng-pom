package com.micro.pmo.moudle.brand.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.micro.pmo.commons.utils.HttpClientUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.mapper.BrandMapper;
import com.micro.pmo.moudle.brand.entity.Brand;
import com.micro.pmo.moudle.brand.service.BrandService;
import com.micro.pmo.moudle.brand.vo.SeriesHttpVo;
import com.micro.pmo.moudle.brand.vo.SeriesVo;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    @Override
    public List<Brand> brandList() {
    	//品牌
        List<Brand> brandList = brandMapper.findBrand();
        if(CollectionUtils.isEmpty(brandList)){
        	return brandList;
        }
        //品牌车型
        List<Brand> brandSeries = brandMapper.brandSeriesList();
        if(CollectionUtils.isEmpty(brandSeries)){
        	return brandList;
        }
        //品牌车型分组
        Map<String, List<Brand>> brandSeriesMap = brandSeries.stream().collect(Collectors.groupingBy(Brand :: getBrandId));
        //装入品牌车型
        List<Brand> children = null;
        for (Brand brand : brandList) {
        	children = brandSeriesMap.get(brand.getBrandId());
        	if(CollectionUtils.isEmpty(children)){
        		continue;
        	}
        	brand.setChildren(children);
		}
        
        return brandList;
    }

	@Override
	public Map<String,List<Brand>> aloneBrandList() {
		List<Brand> brand = brandMapper.findBrand();
		Map<String,List<Brand>> map = brand.stream().collect(Collectors.groupingBy(Brand :: getBrandAcronym));
		return map;
	}


	@Override
	public List<Brand> brandSeriesList(String brandId) {
		
		return brandMapper.findBrandSeriesById(brandId);
	}


	@Override
	public List<Brand> brandSeriesInfos(String brandId) {
		List<Brand> brands = brandMapper.findBrandSeriesInfosById(brandId);
		List<Brand> newBrands = Lists.newArrayList();
		if(CollectionUtils.isEmpty(brands)){
			return newBrands;
		}
		Map<String,List<Brand>> map = brands.stream().collect(Collectors.groupingBy(Brand :: getBrandName));
		for (String name : map.keySet()) {
			List<Brand> brandList = map.get(name);
			newBrands.add(brandList.get(0));
		}
		
		return brands;
	}

	/***
	 * 同步数据
	 */
	@Override
	public void batchBrand() {
		String contentType = "application/json";
		String url = "http://tool.bitefu.net/car?";
		Gson brandJson = new Gson();
		try {
			/*String brandUrl = url + "type=brand&pagesize=300";
			Result barndResult = HttpClientUtils.getString(brandUrl, contentType, null);
			if(barndResult.getCode() != 200){
				System.out.println("获取品牌信息异常");
			}
			String responseContent = (String) barndResult.getData();
			System.out.println(responseContent);
			BranchHttpVo branchHttpVo = brandJson.fromJson(responseContent, BranchHttpVo.class);
			if(!StringUtils.equals(branchHttpVo.getStatus(), "1")){
				return ;
			}
			List<BranchVo> branchVos = branchHttpVo.getInfo();
			//brandMapper.batchBrand(branchVos);*/
			//批量写入TODO
			//品牌车型

			/*List<SeriesVo> seriesInfoAll = Lists.newArrayList();
			for (SeriesVo seriesVo : seriesList) {
				String seriesInfoUrl = url +"type=info&pagesize=300&brand_id=" + branchVo.getId() +"&series_id=" + seriesVo.getId();
				Result seriesInfoResult = HttpClientUtils.getString(seriesInfoUrl, contentType, null);
				String seriesInfoContent = (String) seriesInfoResult.getData();
				SeriesHttpVo seriesInfoHttpVo = null;
				try {
					 seriesInfoHttpVo = brandJson.fromJson(seriesInfoContent, SeriesHttpVo.class);
				} catch (Exception e) {
					System.out.println(seriesInfoContent);
					continue;
				}
				seriesInfoAll.addAll(seriesInfoHttpVo.getInfo());
			}*/
			//批量写入数据库
			//List<Brand> branchVos = brandMapper.findBrand();
			//List<SeriesVo> seriesAll = this.series(contentType, brandJson, url);
			//brandMapper.batchBrandSeries(seriesAll);
			
			List<SeriesVo> seriesInfoAll = this.seriesInfo(contentType, brandJson, url);
			brandMapper.batchBrandSeriesInfo(seriesInfoAll);
			System.out.println("zhixingwancjke");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	/***
	 * 车型
	 * @param contentType
	 * @param brandJson
	 * @param url
	 * @return
	 */
	public List<SeriesVo> series(String contentType,Gson brandJson,String url){
		List<SeriesVo> seriesAll = Lists.newArrayList();
		String seriesUrl = url +"type=series&pagesize=9000";
		try {
			Result seriesResult = HttpClientUtils.getString(seriesUrl, contentType, null);
			 String seriesContent = (String) seriesResult.getData();
				SeriesHttpVo seriesHttpVo = brandJson.fromJson(seriesContent, SeriesHttpVo.class);
				if(!StringUtils.equals(seriesHttpVo.getStatus(), "1")){
					System.out.println("获取品牌车型失败信息异常");
				}
				return seriesHttpVo.getInfo();
		} catch (Exception e) {
			return seriesAll;
		}
				
	}
	/****
	 * 品牌车型
	 * @param contentType
	 * @param brandJson
	 * @param url
	 * @return
	 */
	private List<SeriesVo> seriesInfo(String contentType,Gson brandJson,String url){

		String seriesInfoUrl = url +"type=info&pagesize=35360";
		Result seriesInfoResult;
		int i = 0;
		try {
			seriesInfoResult = HttpClientUtils.getString(seriesInfoUrl, contentType, null);
			String seriesInfoContent = (String) seriesInfoResult.getData();
			SeriesHttpVo seriesInfoHttpVo =  brandJson.fromJson(seriesInfoContent, SeriesHttpVo.class);
			return seriesInfoHttpVo.getInfo();
		} catch (Exception e1) {
			System.out.println(i++);
			e1.printStackTrace();
			return Lists.newArrayList();
		}
		
	}


}
