package com.bonc.ioc.service;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.dao.AreaMapper;
import com.bonc.ioc.model.DicArea;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 区划service层
 */
@Service
public class AreaService {
	@Resource
	private AreaMapper areaMapper;
	
	public DicArea getByAeraCode(String areaCode) throws McpException {
		DicArea areaType = null;
		areaType = areaMapper.findDicAreaByAreaCode(areaCode, null);
		return areaType;
	}
}
