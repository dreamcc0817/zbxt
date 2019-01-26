package com.bonc.ioc.dao;

import com.bonc.ioc.core.base.mapper.BaseDao;
import com.bonc.ioc.core.exception.McpException;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CategoryAttributesDicMapper extends BaseDao<HashMap<String, Object>> {

    public List<HashMap<String, Object>> getCategoryDictInfoDao(HashMap<String, Object> params) throws McpException;
    public void deleteCategoryAttributesDicByCategoryIdDao(HashMap<String, Object> params) throws McpException;
    public void deleteCategoryAttributesDicByAttrIdDao(HashMap<String, Object> params) throws McpException;
    int addCategoryAttributesDicDao(HashMap<String, Object> params) throws McpException;
    int deleteCategoryAttributesDicByAttrIdAndDicNameDao(HashMap<String, Object> params) throws McpException;
}


