package com.bonc.ioc.dao;

import com.bonc.ioc.core.base.mapper.BaseDao;
import com.bonc.ioc.core.exception.McpException;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CategoryAttributesMapper extends BaseDao<HashMap<String, Object>> {
	public HashMap<String, Object> getCategoryPropertyInfoDao(HashMap<String, Object> params) throws McpException;

	int addCategoryAttributesDao(HashMap<String, Object> params) throws McpException;

	public void deleteCategoryAttributesByCategoryIdDao(HashMap<String, Object> params) throws McpException;

	public List selectByPagegetCategoryPropertyInfosDao(HashMap<String, Object> params) throws McpException;

	int deleteCategoryAttributesByAttrIdDao(HashMap<String, Object> params) throws McpException;

	public List getAttrIdByFullcodeDao(HashMap<String, Object> params) throws McpException;

	int updateCategoryAttributesDao(HashMap<String, Object> params) throws McpException;

	int updateAttributesWhenCategoryMoveDao(HashMap<String, Object> params) throws McpException;
}


