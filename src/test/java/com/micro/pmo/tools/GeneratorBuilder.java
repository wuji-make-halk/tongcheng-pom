package com.micro.pmo.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.micro.pmo.tools.common.Constants;
import com.micro.pmo.tools.model.Column;
import com.micro.pmo.tools.model.Dao;
import com.micro.pmo.tools.model.Mapper;
import com.micro.pmo.tools.model.Model;
import com.micro.pmo.tools.model.Service;
import com.micro.pmo.tools.model.Table;
import com.micro.pmo.tools.utils.GeneratorUtils;



/**
 * 
 * 
 * @author jacktom
 */
public class GeneratorBuilder {

	private Model model = null;
	private Dao dao = null;
	private String tableComment;
	private Mapper mapper;
	private Service service;
	
	public GeneratorBuilder(String pkgName, String clzName, Table table, String tableComment) {
		this.tableComment = tableComment;
		
		model = new Model();
		model.setPackageName(Constants.PKG_PREFIX + pkgName + ".entity");
		model.setModelName(clzName);
		
		dao = new Dao();
		dao.setPackageName(Constants.MAPPER_URL);
		dao.setModelName(clzName);
		
		mapper = new Mapper();
		mapper.setPackageName(Constants.MAPPER_URL);
		mapper.setModelName(clzName);
		
		service = new Service();
		service.setPackageName(Constants.PKG_PREFIX + pkgName + ".service");
		service.setModelName(clzName);
		
		List<Column> list = table.getColumnList();
		
		mapper.setColumnList(list);
		mapper.setTableName(table.getTableName());
		mapper.setModelPackageName(model.getPackageName());
		
		// 判断表格是否有树节点
		/*	Column treeParent = new Column();
		treeParent.setName("parent_id");
		if (list.contains(treeParent)) {
			model.setTree(true);
			mapper.setTree(true);
		} else {
			model.setTree(false);
			mapper.setTree(false);
		}*/
		model.setTree(false);
		mapper.setTree(false);
		
		model.setColumnList(list);
		
		List<Column> insertColumnList = new ArrayList<Column>();
		for(Column c : list) {
			insertColumnList.add(c);
		}
		mapper.setInsertColumnList(insertColumnList);
		
	
		mapper.setUpdateColumnList(list);
	}
	
	/**
	 * 
	 * 
	 * @param isCreateDaoAndService 是否创建Dao和Service
	 */
	public void build(boolean isCreateDaoAndService){
		Map<String, Object> root = new HashMap<String, Object>();
		String modelFileName = GeneratorUtils.getTargetFilePath(model.getPackageName(), model.getModelName(), Constants.JAVA_SUFFIX);
		String daoFileName = GeneratorUtils.getTargetFilePath(dao.getPackageName(), dao.getModelName() + "Mapper", Constants.JAVA_SUFFIX);

		String serviceFileName = GeneratorUtils.getTargetFilePath(service.getPackageName(), service.getModelName() + "Service", Constants.JAVA_SUFFIX);
		String mapFileName = GeneratorUtils.getTargetFilePath(dao.getPackageName(), dao.getModelName() + "Mapper", Constants.MAPPINGS_SUFFIX);
		String modelFilePath = GeneratorUtils.getOutputPath(modelFileName);
		String daoFilePath = GeneratorUtils.getOutputPath(daoFileName);
		String serviceFilePath = GeneratorUtils.getOutputPath(serviceFileName);
		String mapFilePath = GeneratorUtils.getOutputPath(mapFileName);
		
		root.put("model", model);
		root.put("dao", dao);
		root.put("mapper", mapper);
		root.put("tableComment", tableComment);
		
		root.put("modelNameLowerCase", model.getModelName().substring(0, 1).toLowerCase() + model.getModelName().substring(1));
		try {
			GeneratorUtils.output("Model.ftl", root, modelFilePath);
			if(isCreateDaoAndService) {
				GeneratorUtils.output("Dao.ftl", root, daoFilePath);
				root.put("service", service);
				GeneratorUtils.output("Service.ftl", root, serviceFilePath);
			}
			if(model.getTree()) {
				GeneratorUtils.output("MapperTree.ftl", root, mapFilePath);
			} else {
				GeneratorUtils.output("MapperCrud.ftl", root, mapFilePath);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发生异常了");
		}
		
	}
}
