package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.RelSysRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RelSysRoleMapper {

	List<RelSysRole> getRelSysRoleList(@Param("sysId") String sysId, @Param("roleId") String roleId) throws McpException;

	void saveRelSysRole(RelSysRole relSysRole) throws McpException;

	void delete(String[] sysRoleIds) throws McpException;

}
