package com.bonc.ioc.service;

import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.core.util.ZTreeUtil;
import com.bonc.ioc.dao.*;
import com.bonc.ioc.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 系统角色service层
 */
@Service
public class SysRoleService extends BaseService {
	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private DicElementMapper dicElementMapper;
	@Resource
	private RelSysRoleMapper relSysRoleMapper;
	@Resource
	private RelRoleMenuMapper relRoleMenuMapper;
	@Resource
	private RelSysMenuMapper relSysMenuMapper;
	@Resource
	private SysRegisterMapper sysRegisterMapper;
	@Resource
	private RelAccOrgMapper relAccOrgMapper;
	@Resource
	private RelAccRoleMapper relAccRoleMapper;
	@Resource
	private SysOrgMapper sysOrgMapper;
	@Resource
	private AccountMapper accountMapper;
	
	/**
	 * 新增角色
	 * @param sysRole
	 * @throws McpException
	 */
	public void save(SysRole sysRole) throws McpException {
		sysRole.setIsValid(1);
		//sysRole.setCreateAccId(getCurrentUser().getId());
		Date createTime = new Date(); // 创建时间
		sysRole.setCreateTime(createTime);
		
		/**
		 * 如果有相同的排序，更新其他的排序号码。
		 */
		sysRoleMapper.updateOtherRoleSort(sysRole.getSort());
		sysRoleMapper.save(sysRole);
	}
	
	/**
	 * 修改角色
	 */
	public void update(SysRole sysRole) throws McpException {
		sysRoleMapper.updateOtherRoleSort(sysRole.getSort());
		sysRoleMapper.update(sysRole);
	}
	
	/**
	 * 批量删除系统注册信息
	 * @param ids
	 * @throws McpException
	 */
	public void delete(String[] ids) throws McpException {
		sysRoleMapper.delete(ids);
	}
	
	/**
	 * 查询角色 树
	 */
	public List<ZTreeNode> getRoleTree() throws McpException{
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
				
				ztree.add(snode);
			}
		}
		return ZTreeUtil.encapsulate(ztree);
	}
	
	public PageResult findRoleList(HashMap<String, Object> searchMap) throws McpException{
		HashMap<String, Object> map = getParamMap();
		map.putAll(searchMap);
		//	map.put("roleCode", roleCode);
		//	map.put("roleName", roleName);
		List<SysRole> roleList = sysRoleMapper.selectByPage(map);
		return new PageResult(map, roleList);
	}
	
	/**
	 * 查询角色拥有的系统和菜单 树
	 */
	public List<ZTreeNode> getRoleSysMenuTree(String roleId) throws McpException{
		//角色拥有的系统
		List<RelSysRole> relSysRoleList = new ArrayList<RelSysRole>();
		relSysRoleList = relSysRoleMapper.getRelSysRoleList("",roleId);
		
		List<RelRoleMenu> relRoleMenuList = new ArrayList<RelRoleMenu>();
		relRoleMenuList = relRoleMenuMapper.getRelRoleMenuList(roleId);
		
		List<ZTreeNode> ztree = new ArrayList<ZTreeNode>();
		ZTreeNode root = new ZTreeNode();//树的根节点
		root.setId("0");
		root.setPid("");
		root.setName("菜单列表");
		root.setType("vroot");//虚节点
		ztree.add(root);
		
		List<DicElement> dictEleList = new ArrayList<DicElement>();
		dictEleList = dicElementMapper.getDicEleListByTypeCode("XTFL");//从字典分类表中取出系统分类
		
		//从系统菜单关系表中，取出所有系统和菜单的数据
		List<RelSysMenu> relSysMenuList = relSysMenuMapper.getRelSysMenuList("");
		
		ZTreeNode stnode = null;
		for(DicElement dicEle : dictEleList){
			int i = 0;//记录改分类下，该角色是否有系统
			stnode = new ZTreeNode();
			stnode.setId(dicEle.getId());
			stnode.setPid("0");
			stnode.setName(dicEle.getElemName());
			stnode.setType("sysType"); //系统类型
			
			List<SysRegister> sysList = sysRegisterMapper.getSysListBySysType(dicEle.getId());
			ZTreeNode snode = null;
			for (SysRegister sys : sysList) {
				for(RelSysRole relSysRole : relSysRoleList){
					if(relSysRole.getSysId().equals(sys.getId())){//如果该角色拥有改系统，则添加到树节点
						
						i ++;
						
						snode = new ZTreeNode();
						snode.setId(sys.getId());
						snode.setPid(sys.getSysType());
						snode.setName(sys.getSysName());
						snode.setType("sys"); //系统
						ztree.add(snode);
						
						for (RelSysMenu sysMenu : relSysMenuList) {
							ZTreeNode node = null;
							if(snode.getId().equals(sysMenu.getSysId())){//
								node = new ZTreeNode();
								node.setId(sysMenu.getMenuCode());
								if(sysMenu.getMenuPcode()!=null
										&&!"".equals(sysMenu.getMenuPcode())){
									node.setPid(sysMenu.getMenuPcode());
								}else{
									node.setPid(snode.getId());
								}
								node.setName(sysMenu.getMenuName());
								node.setSource(sysMenu.getMenuId());
								node.setType("menu");
								
								if(roleId != null && !"".equals(roleId)){
									for(RelRoleMenu relRoleMenu : relRoleMenuList){
										if(relRoleMenu.getMenuId().equals(sysMenu.getMenuId())){
											node.setChecked(true);
										}
									}
								}
								ztree.add(node);
							}
						}
					}
				}
			}
			
			if(i > 0){
				ztree.add(stnode);
			}
		}
		return ZTreeUtil.encapsulate(ztree);
	}
	
	/**
	 * 设置改role的菜单权限
	 * @param menuIds
	 * @param roleId
	 */
	public void setRoleMenu(String menuIds, String roleId) throws McpException{
		List<RelRoleMenu> relRoleMenuList = new ArrayList<RelRoleMenu>();
		relRoleMenuList = relRoleMenuMapper.getRelRoleMenuList(roleId);
		
		List<String> menuidsDeleteList = new ArrayList<String>();
		if(menuIds==null||"".equals(menuIds)){
			if(relRoleMenuList!=null&&relRoleMenuList.size()>0){
				for (RelRoleMenu relRoleMenu : relRoleMenuList) {
					menuidsDeleteList.add(relRoleMenu.getId());
				}
			}
		}else {
			List<String> menuIdList = new ArrayList<String>();
			for(String menuId : menuIds.split(",")){
				menuIdList.add(menuId);
			}
			if(relRoleMenuList!=null&&relRoleMenuList.size()>0){
				for (RelRoleMenu relRoleMenu : relRoleMenuList) {
					String menuId = relRoleMenu.getId();
					if(menuIdList.contains(menuId)){
						menuIdList.remove(menuId);
					}else{
						menuidsDeleteList.add(relRoleMenu.getId());
					}
				}
			}
			RelRoleMenu relRoleMenu = null;
			Date createTime = new Date(); // 创建时间
			for (String menuId : menuIdList) {
				relRoleMenu = new RelRoleMenu();
				relRoleMenu.setId(UUID.randomUUID().toString().replace("-", ""));
				relRoleMenu.setMenuId(menuId);
				relRoleMenu.setRoleId(roleId);
				relRoleMenu.setCreateTime(createTime);
				//relRoleMenu.setCreateAccId(getCurrentUser().getId());
				relRoleMenu.setIsValid("1");
				relRoleMenuMapper.saveRelRoleMenu(relRoleMenu);
			}
		}
		if(menuidsDeleteList.size()>0){
			String[] roleMenuIds = new String[menuidsDeleteList.size()];
			for (int i = 0; i < roleMenuIds.length; i++) {
				roleMenuIds[i] = menuidsDeleteList.get(i);
			}
			relRoleMenuMapper.delete(roleMenuIds);
		}
	}
	
	/**
	 * 分配账户时弹出框中账户栏列表
	 * @param roleId
	 * @return
	 * @throws McpException
	 */
	public List<ZTreeNode> getRoleAccountTree(String roleId) throws McpException{
		List<RelAccOrg> relAccOrgList = new ArrayList<RelAccOrg>();//账号组织机构关系
		relAccOrgList = relAccOrgMapper.getRelAccOrgList();
		
		List<RelAccRole> relAccRoleList = new ArrayList<RelAccRole>();//账号角色关系
		relAccRoleList = relAccRoleMapper.getRelAccRoleList("", roleId);
		
		List<ZTreeNode> ztree = new ArrayList<ZTreeNode>();
		ZTreeNode root = new ZTreeNode();//树的根节点
		root.setId("0");
		root.setPid("");
		root.setName("用户列表");
		root.setType("vroot");//虚节点
		ztree.add(root);
		List<SysOrg> sysOrgList = new ArrayList<SysOrg>();
		sysOrgList = sysOrgMapper.getSysOrgList("");
		ZTreeNode node =null;
		if(sysOrgList != null && sysOrgList.size() > 0){
			for (SysOrg sysOrg : sysOrgList){
				node = new ZTreeNode();
				node.setId(sysOrg.getOrgCode());//将组织机构的code作为树的id显示到页面上
				if(sysOrg.getOrgPcode() == null || "".equals(sysOrg.getOrgPcode()))
					node.setPid("0");
				else
					node.setPid(sysOrg.getOrgPcode());
				node.setName(sysOrg.getOrgNameF());
				node.setSource(sysOrg.getId());
				node.setType("org");
				ztree.add(node);
				
				ZTreeNode onode =null;
				for(RelAccOrg relAccOrg : relAccOrgList){
					if(relAccOrg.getOrgId().equals(sysOrg.getId())){
						onode = new ZTreeNode();
						onode.setId(relAccOrg.getAccId());
						onode.setPid(sysOrg.getOrgCode());
						onode.setName(relAccOrg.getAccName());
						onode.setType("acc");
						for(RelAccRole relAccRole : relAccRoleList){
							if(relAccRole.getAccId().equals(relAccOrg.getAccId())){
								onode.setChecked(true);
								break;
							}
						}
						ztree.add(onode);
					}
				}
			}
			return ZTreeUtil.encapsulate(ztree);
		}
		return null;
	}
	
	/**
	 * 保存角色分配的账户信息
	 * @param roleId
	 * @param accIds
	 * @throws Exception
	 */
	public void saveRoleAcc(String roleId, String accIds) throws McpException {
		String adminRoleId = "F02D136ACCFBC78893FD221EF97F3282";//管理员角色Id
		
		List<String> deleteAccids = new ArrayList<String>();//需要删除的账号的id
		
		List<RelAccRole> relAccRoleList = new ArrayList<RelAccRole>();//账号角色关系
		relAccRoleList = relAccRoleMapper.getRelAccRoleList("", roleId);
		
		List<String> idsDeleteList = new ArrayList<String>();
		if(accIds==null||"".equals(accIds)){
			if(relAccRoleList!=null&&relAccRoleList.size()>0){
				for (RelAccRole relAccRole : relAccRoleList) {
					idsDeleteList.add(relAccRole.getId());
					deleteAccids.add(relAccRole.getAccId());
				}
			}
		}else {
			List<String> accIdList = new ArrayList<String>();
			for(String accId : accIds.split(",")){
				accIdList.add(accId);
			}
			if(relAccRoleList!=null&&relAccRoleList.size()>0){
				for (RelAccRole relAccRole : relAccRoleList) {
					String accId = relAccRole.getAccId();
					if(accIdList.contains(accId)){
						accIdList.remove(accId);
					}else{
						idsDeleteList.add(relAccRole.getId());
						deleteAccids.add(relAccRole.getAccId());
					}
				}
			}
			RelAccRole relAccRole = null;
			Date createTime = new Date(); // 创建时间
			for (String accId : accIdList) {
				relAccRole = new RelAccRole();
				relAccRole.setId(UUID.randomUUID().toString().replace("-", ""));
				relAccRole.setAccId(accId);
				relAccRole.setRoleId(roleId);
				relAccRole.setCreateTime(createTime);
				//relAccRole.setCreateAccId(getCurrentUser().getId());//外部注册，创建账号为本账号
				relAccRole.setIsValid(1);
				relAccRoleMapper.save(relAccRole);
				
				if(adminRoleId.equals(roleId)){
					accountMapper.updateAdmin(accId);
				}
			}
			
		}
		if(idsDeleteList.size()>0){
			String[] accRoleIds = new String[idsDeleteList.size()];
			for (int i = 0; i < accRoleIds.length; i++) {
				accRoleIds[i] = idsDeleteList.get(i);
			}
			relAccRoleMapper.delete(accRoleIds);
		}
		if(deleteAccids.size()>0){
			String[] delaccIds = new String[deleteAccids.size()];
			for (int i = 0; i < delaccIds.length; i++) {
				delaccIds[i] = deleteAccids.get(i);
			}
			
			accountMapper.removeAdmin(delaccIds);
		}
	}
}
