package com.bonc.ioc.dao;

import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.SysRegister;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 名 称：系统注册信息访问接口类
 * <p>
 * 功 能：访问系统注册信息接口
 * <p>
 * 作 者：许文旭
 * <p>
 * 联系方式：18705102012
 * <p>
 * 创建时间：2017/02/28 17:00:00
 * <p>
 * 特殊情形： 无
 */
public interface SysRegisterMapper {

	/**
	 * 查询系统注册列表
	 * 
	 * @param paramMap
	 * @return 系统列表
	 * @throws McpException
	 *             数据库访问异常
	 */
	List<SysRegister> selectByPage(HashMap<String, Object> paramMap) throws McpException;
	
	
	/** 
	 * @Title: selectSysRegisterList 
	 * @Description: 查询系统注册列表
	 * @param  paraMap  
	 * @author yangpingan 
	 * @date 2017年10月19日 上午11:35:32 
	 * @return List<SysRegister>    
	 * @throws 
	 */
	List<SysRegister> selectSysRegisterList(Map<String, Object> paraMap) throws McpException;
	/**
	 * 查询有数据源的系统
	 * */
	public List<SysRegister> getotherdataSysList() throws McpException;
	/**
	 * 根据id查询系统注册详情
	 */
	SysRegister findById(@Param(value = "id") String id) throws McpException;
	
	/**
	 * 根据roleID查询角色
	 * @param roleId
	 * @return
	 * @throws McpException
	 */
	public List<String> getSysByRoleId(String roleId)throws McpException;
	/**
	 * 查询系统注册列表
	 */
	//List<PageData> findSys();
	
	/**
	 * 批量系统注册列表删除
	 */
	void delete(String[] ids) throws McpException;
	
	
	/**
	 * 保存系统注册
	 */	
	void save(SysRegister sysRegister) throws McpException;
	
	/**
	 * 更新系统注册信息
	 */
	void update(SysRegister sysRegister) throws McpException;
	
	/**
	 * 更新系统注册排序号
	 */
	void addSort(SysRegister sysRegister) throws McpException;

	/**
	 * 根据sysType获取列表
	 */
	List<SysRegister> getSysListBySysType(String sysType) throws McpException;
	
	/**
	 * 根据orgId查询系统list
	 */
	public List<SysRegister> getSysByorgId(@Param("orgId") String orgId) throws McpException;
	/**
	 * 根据extend1获取相关系统列表
	 */
	List<SysRegister> getSysListByExtend1(String extend1) throws McpException;
	
	/**
	 * 查询最大的sort
	 */
	Integer findMaxOrderNum() throws McpException;
	
	/**
	 * 更新sort
	 */
	//void updateSort(SysRegister sysRegister, PageData pd) throws McpException;
	
	SysRegister findByCode(@Param(value = "sysCode") String sysCode) throws McpException;
	/**
	 * 查询单点登录的系统
	 * */
	List<SysRegister> selectByPageIssso (HashMap<String, Object> paramMap) throws McpException;
	/**
	 * 查询单点登录的系统
	 */
	List<SysRegister> queryIsssoList(@Param("casId") String casId)throws McpException;
	/**
	 * 解除单点集成
	 */
	void releseMix(String[] ids) throws McpException;
	/**
	 * 解除集成
	 */
	void deleteCasid(@Param("casId") String casId) throws McpException;
	/**
	 * 添加单点集成
	 */
	void addMix(HashMap<String, Object> paramMap) throws McpException;
	/**
	 * 如果有相同的排序，更新其他的排序号码。
	 */
	void updateOtherSysSort(Integer sort)throws McpException;
	/**
	 * 根据账号id查询可访问的系统列表
	 * @param accId
	 * @return
	 * @throws McpException
	 */
	List<SysRegister> getSysListByAccId(String accId)throws McpException;
}
