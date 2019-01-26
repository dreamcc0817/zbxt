package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.RelRoleMenu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RelRoleMenuMapper {

	List<RelRoleMenu> getRelRoleMenuList(@Param("roleId") String roleId) throws McpException;
	
	List<RelRoleMenu> getRelRoleMenuListBymenu(@Param("menuId") String menuId) throws McpException;
	
	void saveRelRoleMenu(RelRoleMenu relRoleMenu) throws McpException;

	void delete(String[] roleMenuIds)throws McpException;

}
