package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.RelSysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelSysMenuMapper {

	List<RelSysMenu> getRelSysMenuList(@Param("sysId") String sysId)throws McpException;
	
	List<RelSysMenu> getRelSysMenuListByMenu(@Param("menuId") String menuId)throws McpException;

	void saveRelSysMenu(RelSysMenu relSysMenu)throws McpException;

	void delete(String[] sysMenuIds)throws McpException;

	RelSysMenu getRelSysMenuByMenuId(@Param("menuId") String menuId)throws McpException;

}
