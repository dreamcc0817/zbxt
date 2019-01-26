package com.bonc.ioc.service;

import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.dao.IndexToAttrsDicsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
@Transactional
public class IndexToAttrsDicsService extends BaseService {

    @Resource
    private IndexToAttrsDicsMapper indexToAttrsDicsMapper;

    public void deleteIndexToAttrsDicsByCategoryIdService(HashMap<String, Object> map) throws McpException {
        indexToAttrsDicsMapper.deleteIndexToAttrsDicsByCategoryIdDao(map);
    }

    public void deleteIndexToAttrsDicsByAttrIdService(HashMap<String, Object> map) throws McpException {
        indexToAttrsDicsMapper.deleteIndexToAttrsDicsByAttrIdDao(map);
    }

    public void deleteWhenCategoryMoveService(HashMap<String, Object> map) throws McpException {
        indexToAttrsDicsMapper.deleteWhenCategoryMoveDao(map);
    }

}
