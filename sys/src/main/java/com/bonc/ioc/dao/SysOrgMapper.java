package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.SysOrg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 系统组织机构dao层
 */
@Mapper
public interface SysOrgMapper {
	/**
	 * 查询列表
	 * @param
	 */
	public List<SysOrg> selectByPage(HashMap<String, Object> paramMap) throws McpException;
	/**
	 * 根据id获取信息
	 * @param
	 */
	public SysOrg findById(@Param(value="id")String id) throws McpException;
	/**
	 * 删除
	 * @param
	 */
	public void delete(String[] ids) throws McpException;
	
	public Object save(String str, Object obj) throws McpException;
	
	public SysOrg findByCode(@Param(value="orgCode")String orgCode) throws McpException;
	/**
	 * 新增
	 * @param
	 */
	public void save(SysOrg sysOrg) throws McpException;
	/**
	 * 修改
	 * @param
	 */
	public void update(SysOrg sysOrg) throws McpException;
	
	public List<SysOrg> getSysOrgList(@Param("orgPcode")String orgPcode) throws McpException;
	
	/**
	 * 检查是否能进行删除操作
	 */
	public List<SysOrg> checkCanDel(String[] idArr) throws McpException;
	
	
	public void updateOtherOrgSort(@Param("orgPcode")String orgPcode,@Param("sort")Integer sort)throws McpException;
	
	public Integer findMaxOrderNum()throws McpException;
	
	public SysOrg findSysOrgByorgCodeAndSort(@Param("orgPcode")String orgPcode,@Param("sort")Integer sort) throws McpException;
	
	public String findMaxOrgCode() throws McpException;
	/**
	 * 根据组织机构类查列表
	 */
	public List<SysOrg> getSysOrgListByOrgType(@Param("orgType")String orgType)throws McpException;
}
