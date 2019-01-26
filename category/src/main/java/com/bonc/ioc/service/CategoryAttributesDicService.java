package com.bonc.ioc.service;

import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.dao.CategoryAttributesDicMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CategoryAttributesDicService extends BaseService {

    @Resource
    private CategoryAttributesDicMapper categoryAttributesDicMapper;

    public List<HashMap<String, Object>> getCategoryDictInfoService(HashMap<String, Object> params) throws McpException {
        return categoryAttributesDicMapper.getCategoryDictInfoDao(params);
    }

    public void deleteCategoryAttributesDicByCategoryIdService(HashMap<String, Object> params) throws McpException{
        categoryAttributesDicMapper.deleteCategoryAttributesDicByCategoryIdDao(params);
    }

    public void deleteCategoryAttributesDicByAttrIdService(HashMap<String, Object> params) throws McpException{
        categoryAttributesDicMapper.deleteCategoryAttributesDicByAttrIdDao(params);
    }

    public int addCategoryAttributesDicService(HashMap<String, Object> params) throws McpException{
        int result = categoryAttributesDicMapper.addCategoryAttributesDicDao(params);
        return result;
    }

    public int deleteCategoryAttributesDicByAttrIdAndDicNameService(HashMap<String, Object> params) throws McpException{
        int result = categoryAttributesDicMapper.deleteCategoryAttributesDicByAttrIdAndDicNameDao(params);
        return result;
    }

}
