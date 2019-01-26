package com.bonc.ioc.service;

import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.core.util.ZTreeUtil;
import com.bonc.ioc.dao.*;
import com.bonc.ioc.model.*;
import com.bonc.ioc.util.PasswordHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 系统用户service层
 */
@Service
public class AccountService extends BaseService {
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private RelAccOrgMapper relAccOrgMapper;
	@Resource
	private DicElementMapper dicElementMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private RelAccRoleMapper relAccRoleMapper;
	
	/**
	 * 保存
	 */
	public void save(Account account) throws McpException {
		account.setCreateTime(new Date());//保存时间
		//account.setCreateAccId(getCurrentUser().getId());//创建者
		account.setIsOnline(0);
		account.setIsValid(1);
		//排序号
		accountMapper.updateOtherEleSort(account.getSort());
		accountMapper.save(account);
	}
	
	public void saveAccOrg(RelAccOrg relAccOrg) throws McpException {
		Date createTime = new Date(); // 创建时间
		relAccOrg.setCreateTime(createTime);
		//relAccOrg.setCreateAccId(getCurrentUser().getId());
		relAccOrg.setIsValid(1);
		accountMapper.saveAccOrg(relAccOrg);
	}
	
	/**
	 * 修改
	 */
	public void update(Account  account) throws McpException {
		account.setUpdateTime(new Date());
	/*	account.setUpdateAccId(getCurrentUser().getId());*/
		accountMapper.updateOtherEleSort(account.getSort());
		accountMapper.update(account);
	}
	
	/**
	 * 组织机构与账号关联
	 *  @author wnn
	 */
	public void addOrgAccRel(String orgCodes,String accId) throws McpException{
		//original Org
		List<String> oldOrg = getorgCodesByAccID(accId);
		//now Org
		List<String> newOrg = Arrays.asList(orgCodes.split(","));
		ArrayList<String> resultlist =new ArrayList<String>();
		
		if(oldOrg==null||oldOrg.isEmpty()){
			if(newOrg!=null&&!newOrg.isEmpty()){
				for (int i = 0; i < newOrg.size(); i++) {
					SysOrg sysOrg = sysOrgService.findByCode(newOrg.get(i));
					RelAccOrg relAccOrg = new RelAccOrg();
					relAccOrg.setAccId(accId);
					relAccOrg.setOrgId(sysOrg.getId());
					saveAccOrg(relAccOrg);
				}
			}
		}else{
			//取差集,删除
			resultlist.clear();
			resultlist.addAll(oldOrg);
			resultlist.removeAll(newOrg);
			if(resultlist!=null&&!resultlist.isEmpty()){
				for (int i = 0; i < resultlist.size(); i++) {
					SysOrg sysOrg = sysOrgService.findByCode(resultlist.get(i));
					//删除账号和组织机构的关系
					relAccOrgMapper.delete(sysOrg.getId(),accId);
				}
			}
			//取差集,保存
			resultlist.clear();
			resultlist.addAll(newOrg);
			resultlist.removeAll(oldOrg);
			
			if(resultlist!=null&&!resultlist.isEmpty()){
				for (int i = 0; i < resultlist.size(); i++) {
					SysOrg sysOrg = sysOrgService.findByCode(resultlist.get(i));
					RelAccOrg relAccOrg = new RelAccOrg();
					relAccOrg.setAccId(accId);
					relAccOrg.setOrgId(sysOrg.getId());
					saveAccOrg(relAccOrg);
				}
			}
		}
	}
	
	/**
	 * 查询用户所属机构code
	 * @author wnn
	 */
	public List<String> getorgCodesByAccID(String accId) throws McpException {
		List<SysOrg> orgList = new ArrayList<SysOrg>();
		List<String> orgCodes = new ArrayList<String>();
		orgList =  accountMapper.getorgByAccID(accId);
		if(orgList!=null&&!orgList.isEmpty()){
			for(SysOrg org:orgList){
				orgCodes.add(org.getOrgCode());
			}
		}else{
			return null;
		}
		return orgCodes;
	}
	
	public Account getFindByIsAdmin(String id)throws McpException{
		Account account = accountMapper.findByIsAdmin(id);
		return account;
	}
	
	/**
	 * 删除
	 */
	public void delete(String[] ids) throws McpException {
		accountMapper.delete(ids);
	}
	
	/**
	 * 条件查询账户列表
	 * @param isLocked
	 * @param accName
	 * @param orgName
	 * @throws Exception ServiceException
	 */
	public PageResult findAccountList(String isLocked, String accName, String orgName, String pageNumber, String pageSize) throws McpException {
		HashMap<String, Object> map = getParamMap();
		map.put("isLocked", isLocked);
		map.put("accName", accName);
		map.put("orgName", orgName);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		List<Account> accountList = accountMapper.selectByPage(map);
		return new PageResult(map, accountList);
	}
	
	public List<ZTreeNode> getAccountRoleTree(String accId) throws McpException {
		List<RelAccRole> relAccRoleList = new ArrayList<RelAccRole>();
		relAccRoleList = relAccRoleMapper.getRelAccRoleList(accId,"");
		
		
		List<ZTreeNode> ztree = new ArrayList<ZTreeNode>();
		ZTreeNode root = new ZTreeNode();//树的根节点
		root.setId("0");
		root.setPid("");
		root.setName("角色列表");
		root.setType("vroot"); //虚节点
		ztree.add(root);
		
		List<DicElement> dictEleList = new ArrayList<DicElement>();
		dictEleList = dicElementMapper.getDicEleListByTypeCode("JSFL");//从字典分类表中取出系统分类
		
		ZTreeNode stnode = null;
		for(DicElement dicEle : dictEleList){
			stnode = new ZTreeNode();
			stnode.setId(dicEle.getId());
			stnode.setPid("0");
			stnode.setName(dicEle.getElemName());
			stnode.setType("roleType"); //角色类型
			ztree.add(stnode);
			
			List<SysRole> sysRoleList = sysRoleMapper.getSysRoleListByRoleType(dicEle.getId());
			ZTreeNode snode = null;
			
			for (SysRole sysRole : sysRoleList) {
				snode = new ZTreeNode();
				snode.setId(sysRole.getId());
				snode.setPid(sysRole.getRoleType());
				snode.setName(sysRole.getRoleName());
				snode.setType("role"); //角色
				for (RelAccRole relAccRole : relAccRoleList) {
					if(snode.getId().equals(relAccRole.getRoleId())){
						snode.setChecked(true);
						break;
					}
				}
				ztree.add(snode);
			}
		}
		return ZTreeUtil.encapsulate(ztree);
	}
	
	public void saveAccRole(String accId, String roleIds) throws McpException {
		String adminRoleId = "F02D136ACCFBC78893FD221EF97F3282";//管理员角色Id
		Account acc = accountMapper.findById(accId);
		
		List<RelAccRole> relAccRoleList = new ArrayList<RelAccRole>();
		relAccRoleList = relAccRoleMapper.getRelAccRoleList(accId, "");
		
		List<String> idsDeleteList = new ArrayList<String>();
		if (roleIds == null || "".equals(roleIds)) {
			if (relAccRoleList != null && relAccRoleList.size() > 0) {
				for (RelAccRole relAccRole : relAccRoleList) {
					idsDeleteList.add(relAccRole.getId());
					if(relAccRole.getRoleId().equals(adminRoleId)){//移除管理员
						accountMapper.updateRemoveAdmin(accId);
					}
				}
			}
		} else {
			List<String> roleIdList = new ArrayList<String>();
			for (String roleId : roleIds.split(",")) {
				roleIdList.add(roleId);
			}
			if (relAccRoleList != null && relAccRoleList.size() > 0) {
				for (RelAccRole relAccRole : relAccRoleList) {
					String roleId = relAccRole.getRoleId();
					if (roleIdList.contains(roleId)) {
						roleIdList.remove(roleId);
					} else {
						idsDeleteList.add(relAccRole.getId());
						if(roleId.equals(adminRoleId)){//移除管理员
							accountMapper.updateRemoveAdmin(accId);
						}
					}
				}
			}
			RelAccRole relAccRole = null;
			Date createTime = new Date(); // 创建时间
			for (String roleId : roleIdList) {
				relAccRole = new RelAccRole();
				relAccRole.setId(UUID.randomUUID().toString().replace("-", ""));
				relAccRole.setAccId(accId);
				relAccRole.setRoleId(roleId);
				relAccRole.setCreateTime(createTime);
				//relAccRole.setCreateAccId(getCurrentUser().getId());// 外部注册，创建账号为本账号
				relAccRole.setIsValid(1);
				relAccRoleMapper.save(relAccRole);
				
				if(roleId.equals(adminRoleId)){//新增管理员
					accountMapper.updateAdmin(accId);
				}
			}
			
		}
		if (idsDeleteList.size() > 0) {
			String[] accRoleIds = new String[idsDeleteList.size()];
			for (int i = 0; i < accRoleIds.length; i++) {
				accRoleIds[i] = idsDeleteList.get(i);
				
			}
			relAccRoleMapper.delete(accRoleIds);
		}
	}
	
	/**
	 * 添加导入用户的密码
	 * @param ids
	 */
	public void addImportPwd(String ids) throws McpException {
		String idArr[] = ids.split(",");
		for(String id : idArr){
			Account account = accountMapper.findById(id);
			account.setAccPwd("123456");
			String pwd = new PasswordHelper().getEncryptPassword(account);
			accountMapper.updateAccountPwd(pwd,id);
		}
	}
	
	/**
	 * 根据登录名称查找用户
	 * @param loginName 登录名称
	 * @return User对象
	 * @throws McpException
	 */
	public Account findAccountByLoginName(HashMap<String,Object> param) throws McpException {
		Account account = null;
		account = accountMapper.findAccountByLoginName(param);
		return account;
	}
	
	/**
	 * 修改密码
	 * @param password
	 * @param salt
	 * @param userId
	 * @throws McpException
	 */
	public void updatePwd(String password, String salt, String userId) throws McpException {
		accountMapper.updatePwd(password, salt, userId);
	}
	
	public Account getUserById(String userId) throws McpException {
		return accountMapper.getUserById(userId);
	}
	
	public void updateLockedState(String isLocked, String userId) throws McpException {
		if("0".equals(isLocked)){
			accountMapper.locked(userId);
		}else{
			accountMapper.unlocked(userId);
		}
	}
	
}
