package com.micro.pmo.moudle.brand.admin.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.micro.pmo.mapper.BrandMapper;
import com.micro.pmo.moudle.brand.admin.service.AdminBrandService;
import com.micro.pmo.moudle.brand.entity.Brand;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月2日 
*/
@Service
public class AdminBrandServiceImpl implements AdminBrandService{

	@Autowired
	private BrandMapper brandMapper;
	
	@Override
	public List<Brand> branList(Integer type, Integer parentId) {
		
		List<Brand> brandList = null;
		
		Integer level = null;
		
		if (parentId == null || type == null || type.equals(0)) {
			brandList = brandMapper.findBrand();
			level = 1;
		}else if (type.equals(1)) {
			brandList = brandMapper.findBrandSeriesById(parentId.toString());
			level = 2;
		}else if (type.equals(2)) {
			List<Brand> brands = brandMapper.findBrandSeriesInfosById(parentId.toString());
			List<Brand> newBrands = Lists.newArrayList();
			if(CollectionUtils.isEmpty(brands)){
				return newBrands;
			}
			Map<String,List<Brand>> map = brands.stream().collect(Collectors.groupingBy(Brand :: getBrandName));
			for (String name : map.keySet()) {
				List<Brand> brandA = map.get(name);
				newBrands.add(brandA.get(0));
			}
			brandList = newBrands;
			level = 3;
		}
		
		if(brandList == null || brandList.isEmpty()) {
			return brandList;
		}
		
		//设置层级
		final Integer finalLevel = level;
		
		brandList.forEach((b)->{
			b.setLevel(finalLevel);
		});
		
		return brandList;
	}

	
	
}
