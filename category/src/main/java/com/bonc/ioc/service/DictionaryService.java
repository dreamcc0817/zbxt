package com.bonc.ioc.service;

import com.bonc.ioc.dao.OmpDicElementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class DictionaryService {
    @Resource
    private OmpDicElementMapper ompDicElementMapper;
    public List<Map<String,Object>> getDicByType(String typeCode) throws Exception {
        List<Map<String,Object>> list;
        list=this.ompDicElementMapper.getDicByType(typeCode);
        return list;
    }
}
