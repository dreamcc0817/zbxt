package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.DeptContacts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 移动端组织通讯录dao层
 */
@Mapper
public interface AppDeptContactsMapper {
	/**
	 * 通讯录添加部门的部门列表
	 * @param keyword
	 * @return
	 */
	public List<DeptContacts> getDeptList(@Param(value = "keyword") String keyword) throws McpException;
	
	/**
	 * 根据用户主键查询通讯录中的部门主键
	 * @param userId
	 * @return
	 */
	public List<String> getDeptIdsByAppUserId(String userId) throws McpException;
	
	/**
	 * 通讯录添加部门
	 * @param id
	 * @param userId
	 * @param deptId
	 * @throws McpException
	 */
	public void addDeptForUser(@Param(value = "id") String id, @Param(value = "userId") String userId, @Param(value = "deptId") String deptId)  throws McpException;
	
	/**
	 * 政府通讯录树结构
	 * @param userId
	 * @return
	 * @throws McpException
	 */
	public List<DeptContacts> getDeptContactsList(String userId) throws McpException;
}
