package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageData;
import com.bonc.ioc.model.RelAccRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface RelAccRoleMapper {

public List<RelAccRole> selectByPage(HashMap<String, Object> paramMap) throws McpException;
    
    //public List findForList(PageData pd) throws McpException;

	public void save(RelAccRole relAccRole) throws McpException;

	public void delete(String[] accRoleIds) throws McpException;

	public List<RelAccRole> getRelAccRoleList(@Param("accId") String accId, @Param("roleId") String roleId) throws McpException;
}
