package com.bonc.ioc.dao;

/**
 * 组织通讯录dao层
 */

import com.bonc.ioc.core.base.mapper.BaseDao;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.DeptContacts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeptContactsMapper extends BaseDao {
	/**
	 * 组织通讯录(管理者)——添加组织信息
	 * 组织通讯录(管理者)——添加部门通讯录
	 * @param deptContacts
	 * @throws McpException
	 */
	public void add(DeptContacts deptContacts) throws McpException;
	
	/**
	 * 组织通讯录(管理者)——修改组织信息
	 * 组织通讯录(管理者)——编辑部门通讯录
	 * @param deptContacts
	 * @throws McpException
	 */
	public void edit(DeptContacts deptContacts) throws McpException;
	
	/**
	 * 组织通讯录(管理者)——删除组织信息
	 * 组组织通讯录(管理者)——批量删除部门通讯录
	 * @param id
	 * @throws McpException
	 */
	public void del(String id) throws McpException;
	
	/**
	 * 组织列表的生成
	 * @param keyword
	 * @return
	 * @throws McpException
	 */
	public List<DeptContacts> getORG(@Param(value="keyword") String keyword) throws McpException;
	
	/**
	 * 组织通讯录(管理者)——分页查询部门通讯录
	 * @param map
	 * @return
	 * @throws McpException
	 */
	public List<DeptContacts> selectByPageDEPT(Map map) throws McpException;
}
