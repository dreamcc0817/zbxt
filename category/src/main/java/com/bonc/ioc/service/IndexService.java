package com.bonc.ioc.service;

import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.dao.IndexMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
@Transactional
public class IndexService extends BaseService {

    @Resource
    private IndexMapper indexMapper;

    public void deleteRelationBetweenIndexAndGategoryService(HashMap<String, Object> map) throws McpException {
        indexMapper.deleteRelationBetweenIndexAndGategoryDao(map);
    }

    public Integer whetherCategoryHaveIndexService(HashMap<String, Object> map) throws McpException {
        return indexMapper.whetherCategoryHaveIndexDao(map);
    }

    public void updateIndexWhenCategoryMoveService(HashMap<String, Object> map) throws McpException {
        indexMapper.updateIndexWhenCategoryMoveDao(map);
    }

}
