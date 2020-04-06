package com.micro.pmo.moudle.extension.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.mapper.ExtensionMapper;
import com.micro.pmo.moudle.extension.admin.service.AdminExtensionService;
import com.micro.pmo.moudle.extension.entity.Extension;

/**
 * ExtensionDao
 * 
 * @author wenhaofan
 * @createtime
 */
@Service
public class AdminExtensionServiceImpl implements AdminExtensionService {

	@Autowired
	private ExtensionMapper extensionMapper;

	@Override
	public void saveExtension(Extension extension) {
		extensionMapper.insertExtension(extension);
	}

	@Override
	public void updateExtensionById(Extension extension) {
		extensionMapper.updateExtensionById(extension);
	}

	@Override
	public List<Extension> extensionList(BaseQuery query) {
		return extensionMapper.extensionList();
	}

}