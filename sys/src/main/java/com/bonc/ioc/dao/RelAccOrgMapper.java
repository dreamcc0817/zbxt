package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.RelAccOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**用户组织机构关系表
 * */
public interface RelAccOrgMapper {
   
	public void save(RelAccOrg relAccOrg) throws McpException;

	public void update(RelAccOrg relAccOrg) throws McpException;
	
	public void delete(@Param(value = "accId") String accId) throws McpException;
	
	public void delete(@Param(value = "orgId") String orgId, @Param(value = "accId") String accId) throws McpException;

	public List<RelAccOrg> getRelAccOrgList() throws McpException;
	
	public List<RelAccOrg> getAccOrgByorgId(@Param(value = "orgId") String orgId) throws McpException;
	
}