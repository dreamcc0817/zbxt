package com.bonc.ioc.dao;

import com.bonc.ioc.core.base.mapper.BaseDao;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.Category;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baixue on 2018/11/8.
 */
@Mapper
public interface SearchDao extends BaseDao {

    public List<Map<String, Object>> getCategoryProps(String categoryId) throws McpException;

    public List<Map<String, Object>>  selectByPageFindByKeyWords (HashMap<String, Object> map) throws McpException;

    public List<Map<String, Object>> selectIndexName(String categoryFullcode) throws McpException;

    public List<String> selectAllCategoryNames(HashMap<String, Object> map)throws McpException;

    public List<Map<String, Object>> getCategoryList()throws McpException;

    List<Category> getAllTopLevelCategory()throws McpException;

    int getSearchTotalNumber(HashMap<String, Object> map) throws McpException;
}
