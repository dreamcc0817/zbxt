package com.bonc.ioc.dao;

import com.bonc.ioc.core.base.mapper.BaseDao;
import com.bonc.ioc.core.exception.McpException;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper

public interface CategoryMapper extends BaseDao<HashMap<String, Object>> {
    public List<HashMap<String, Object>> getChildrenParallelCategoryDao() throws McpException;

    /**
     * 更新类目信息
     * @param params
     * @return
     * @throws McpException
     */
    int updateCategoryDao(HashMap<String, Object> params) throws McpException;

    /**
     * 新增指标类目
     * @param params
     * @return
     * @throws McpException
     */
    int addCategoryDao(HashMap<String, Object> params) throws McpException;
    Integer whetherFullCodeRepeatDao(HashMap<String, Object> params) throws McpException;
    int deleteAllByCategoryIdDao(HashMap<String, Object> params) throws McpException;
    public HashMap<String, Object> getCategoryDao(HashMap<String, Object> params) throws McpException;
    public void updatePidWhenCategoryMoveDao(HashMap<String, Object> params) throws McpException;
    public void updateFullcodeWhenCategoryMoveDao(HashMap<String, Object> params) throws McpException;
    public void updateLevelWhenCategoryMoveDao(HashMap<String, Object> params) throws McpException;
}


