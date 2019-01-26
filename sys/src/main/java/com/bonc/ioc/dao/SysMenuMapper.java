package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.SysMenu;
import org.apache.ibatis.annotations.Param;
import java.util.HashMap;
import java.util.List;

public interface SysMenuMapper {

	/**
	 * 查询菜单列表
	 * @param paramMap
	 * @return 菜单列表
	 * @throws McpException 数据库访问异常
	 */
	public List<SysMenu> selectByPage(HashMap<String, Object> paramMap) throws McpException;
	
	public List<SysMenu> findSysMenuListByRoleId(HashMap<String, Object> paramMap) throws McpException;
	
	SysMenu findById(String id) throws McpException;
	
	// 删除	 
	public void delete(String[] ids) throws McpException;
	
	// 新增
	public void save(SysMenu sysMenu) throws McpException;
	
	int addSort(Integer addSort) throws McpException;
	
	// 修改
	public void update(SysMenu sysMenu) throws McpException;

	//通过菜单编码查询

    List<SysMenu> getSysMenuList(@Param("pcode") String pcode) throws McpException;
  //通过菜单编码查询
    SysMenu findSysMenuByMenuCode(@Param("menuCode") String menuCode) throws McpException;
	
    /**
	 * 检查是否能进行删除操作
	 */
	public List<SysMenu> checkCanDel(String[] idArr) throws McpException;

	public List<SysMenu> findMenuList(HashMap<String, Object> paramMap) throws McpException;

	Integer findMaxOrderNum() throws McpException;
	
	void updateOtherMenuSort(@Param("menuPCode") String menuPCode, @Param("sort") Integer sort) throws McpException;
	
	SysMenu findSysMenuByMenuCodeAndSort(@Param("menuPCode") String menuPCode, @Param("sort") Integer sort) throws McpException;

	public String findMaxMenuCode() throws McpException;


}