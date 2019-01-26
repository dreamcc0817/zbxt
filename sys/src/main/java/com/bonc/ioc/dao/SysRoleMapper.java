package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SysRoleMapper {
    public List<String> getAccByRoleId(String roleId) throws McpException;
    
    public List<String> getSysByRoleId(String roleId) throws McpException;
    
    public List<String> getSysByRoleType(@Param("dicElemId") String dicElemId) throws McpException;
	
    public List<SysRole> selectByPage(HashMap<String, Object> paramMap) throws McpException;
    
    //public List findForList(PageData pd) throws McpException;
	
	public SysRole findById(@Param("id") String id) throws McpException;
    
    public void delete(String[] ids) throws McpException;
	
	public SysRole findByCode(@Param(value = "roleCode") String roleCode) throws McpException;
	
	public void save(SysRole sysRole) throws McpException;

	public void update(SysRole sysRole) throws McpException;
	
	public List<SysRole> getSysRoleListByRoleType(@Param("roleType") String roleType) throws McpException;
	
	public Integer findMaxOrderNum()throws McpException;
	
	public void updateOtherRoleSort(Integer sort)throws McpException;
	
	public String findMaxRoleCode() throws McpException;

}