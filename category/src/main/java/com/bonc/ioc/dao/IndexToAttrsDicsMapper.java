package com.bonc.ioc.dao;

import com.bonc.ioc.core.base.mapper.BaseDao;
import com.bonc.ioc.core.exception.McpException;
import org.mapstruct.Mapper;

import java.util.HashMap;

@Mapper
public interface IndexToAttrsDicsMapper extends BaseDao<HashMap<String, Object>> {

    public void deleteIndexToAttrsDicsByCategoryIdDao(HashMap<String, Object> params) throws McpException;
    public void deleteIndexToAttrsDicsByAttrIdDao(HashMap<String, Object> params) throws McpException;
    public void deleteWhenCategoryMoveDao(HashMap<String, Object> params) throws McpException;
}


