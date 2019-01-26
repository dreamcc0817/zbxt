/*
 * Copyright(C)2016-2020 BONC Software Co. Ltd. All rights reserved.
 * 系统名称：bonc_ioc_ompV1.1
 * 作者：左明强  && 手机：13522126905
 * 版本号                                         日   期                          作     者                    变更说明
 * AccountMapper-V1.0   2017/02/23     左明强                      新建
*/
package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.Account;
import com.bonc.ioc.model.RelAccOrg;
import com.bonc.ioc.model.SysOrg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * <p> 名       称：账号信息访问接口类
 * <p> 功       能：访问账号信息接口
 * <p> 作       者： 左明强
 * <p> 联系方式：13522126905
 * <p> 创建时间：2017/02/23 10:00:00
 * <p> 特殊情形： 无
 */
@Mapper
public interface AccountMapper {

	/**
	 * 按登录名获取账号信息
	 * @param loginName 登录账号
	 * @return 账号实体对象
	 * @throws McpException 数据库访问异常
	 */
	public Account findAccountByLoginName(HashMap<String,Object> param) throws McpException;

	public int getMaxSort();

	public void addSort(@Param(value = "assSort") Integer addSort);
	/**
	 * 查询账户列表
	 * @param paramMap
	 * @return 账号列表
	 * @throws McpException 数据库访问异常
	 */
	public List<Account> selectByPage(HashMap<String, Object> paramMap) throws McpException;

	public Account findById(@Param(value = "id") String id) throws McpException;

	Account findByNum(@Param(value = "accNum") String accNum) throws McpException;

	Account findByEmail(@Param(value = "contactMail") String contactMail) throws McpException;

	Account findByIsAdmin(@Param(value = "id") String id) throws McpException;
	/**
	 *根据用户ID查机构名称
	 * */
	public String findOrgnameByAccID(String accId)throws McpException;
	public List<SysOrg> getorgByAccID(String accId)throws McpException;
	/**
	 *根据用户ID查角色名称
	 * */
	public List<String> findRolenameByAccID(String accId)throws McpException;

	/**
	 *修改用户资料
	 * */
	public void updateAccount(Account acc)throws McpException;


	/**
	  * 删除
	  * @param ids
	  */
	 public void delete(String[] ids)throws McpException;

	 /**
	  * 新增
	  * @param account
	  */
	 public void save(Account account)throws McpException;

	 /**
	  * 修改
	 * @param account
	  */
	 public void update(Account account)throws McpException;

	 //public List findForList(PageData pd)throws McpException;

	 public void saveAccOrg(RelAccOrg relAccOrg)throws McpException;

	 public RelAccOrg findAccOrgById(String accId)throws McpException;

	 public void updateAccOrg(RelAccOrg relAccOrg)throws McpException;

	 /**
	  * 根据账户查询密保问题
	 * @param accNum
	  */
	 Account getByCode(@Param(value = "accNum") String accNum)throws McpException;

	 /**
	  * 排序
	  * @return
	  * @throws McpException
	  */
	 Integer findMaxOrderNum()throws McpException;

	 void updateOtherEleSort(Integer sort)throws McpException;

//	void addImportPwd(HashMap<String, Object> searchMap) throws McpException;

	void updateAdmin(@Param("accId") String accId) throws McpException;//新增管理员

	void updateRemoveAdmin(@Param("accId") String accId) throws McpException;//移除管理员

	public void removeAdmin(String[] delaccIds) throws McpException;//移除管理员

	public List<Account> selectByPageOrg(HashMap<String, Object> searchMap)throws McpException;

	public List<Account> selectByPageOrgIn(HashMap<String, Object> searchMap)throws McpException;

	public void updateAccountPwd(@Param("pwd") String pwd, @Param("id") String id) throws McpException;

	public Account getUserById(String userId) throws McpException;

	public void updatePwd(@Param("password") String password, @Param("salt") String salt, @Param("userId") String userId) throws McpException;

	public void locked(String userId) throws McpException;

	public void unlocked(String userId) throws McpException;
}
