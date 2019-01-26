package com.bonc.ioc.dao;

import com.bonc.ioc.core.base.mapper.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface OmpDicElementMapper extends BaseDao<HashMap<String,Object>> {
    public List<Map<String,Object>> getDicByType(String typeCode) throws Exception;
}
