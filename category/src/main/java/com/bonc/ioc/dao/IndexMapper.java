package com.bonc.ioc.dao;

import com.bonc.ioc.core.base.mapper.BaseDao;
import com.bonc.ioc.core.exception.McpException;
import org.mapstruct.Mapper;

import java.util.HashMap;

@Mapper
public interface IndexMapper extends BaseDao<HashMap<String, Object>> {

    public void deleteRelationBetweenIndexAndGategoryDao(HashMap<String, Object> params) throws McpException;
    public Integer whetherCategoryHaveIndexDao(HashMap<String, Object> params) throws McpException;
    public void updateIndexWhenCategoryMoveDao(HashMap<String, Object> params) throws McpException;
}


