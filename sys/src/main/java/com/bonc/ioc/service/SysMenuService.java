package com.bonc.ioc.service;

import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.core.util.ZTreeUtil;
import com.bonc.ioc.dao.DicElementMapper;
import com.bonc.ioc.dao.RelSysMenuMapper;
import com.bonc.ioc.dao.SysMenuMapper;
import com.bonc.ioc.dao.SysRegisterMapper;
import com.bonc.ioc.model.DicElement;
import com.bonc.ioc.model.RelSysMenu;
import com.bonc.ioc.model.SysMenu;
import com.bonc.ioc.model.SysRegister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 系统菜单service层
 */
@Service
public class SysMenuService extends BaseService {
	@Resource
	private SysMenuMapper sysMenuMapper;
	@Resource
	private RelSysMenuMapper relSysMenuMapper;
	@Resource
	private DicElementMapper dicElementMapper;
	@Resource
	private SysRegisterMapper sysRegisterMapper;
	
	/**
	 * 保存
	 * @param sysId
	 */
	public void save(SysMenu sysMenu, String sysId) throws McpException {
		/**
		 * 判断当前一级，是否有相同的排序号
		 */
		SysMenu sysmenu = sysMenuMapper.findSysMenuByMenuCodeAndSort(sysMenu.getMenuPcode(),sysMenu.getSort());
		if(sysmenu!= null){
			sysMenuMapper.updateOtherMenuSort(sysmenu.getMenuPcode(),sysmenu.getSort());
		}
		
		sysMenu.setIsValid(1);
		//sysMenu.setCreateAccId(getCurrentUser().getId());// shiro获取当前操作用户信息
		Date createTime = new Date(); // 创建时间
		sysMenu.setCreateTime(createTime);
		sysMenuMapper.save(sysMenu);
		
		//保存系统和菜单关系
		RelSysMenu relSysMenu = new RelSysMenu();
		relSysMenu.setMenuId(sysMenu.getId());
		relSysMenu.setSysId(sysId);
		relSysMenu.setIsValid("1");
		//relSysMenu.setCreateAccId(getCurrentUser().getId());
		relSysMenu.setCreateTime(createTime);
		relSysMenuMapper.saveRelSysMenu(relSysMenu);
	}
	
	/**
	 * 修改
	 */
	public void update(SysMenu sysMenu) throws McpException {
		sysMenuMapper.findSysMenuByMenuCodeAndSort(sysMenu.getMenuPcode(),sysMenu.getSort());
		if(sysMenu!= null){
			sysMenuMapper.updateOtherMenuSort(sysMenu.getMenuPcode(),sysMenu.getSort());
		}
		sysMenuMapper.update(sysMenu);
	}
	
	/**
	 * 检查是否能进行删除操作
	 * @param idArr
	 * @return
	 */
	public String checkCanDel(String[] idArr) throws McpException{
		List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
		sysMenuList = sysMenuMapper.checkCanDel(idArr);
		if(sysMenuList.size() > 0){
			return "false";
		}else{
			return "able";
		}
	}
	
	/**
	 * 批量删除系统注册信息
	 * @param ids
	 * @throws McpException
	 */
	public void delete(String[] ids) throws McpException {
		sysMenuMapper.delete(ids);
	}
	
	/**
	 * 查询菜单分类树
	 * @param sysId
	 * @return
	 */
	public List<ZTreeNode> getSysMenuTree(String sysId) throws McpException{
		List<ZTreeNode> ztree = new ArrayList<ZTreeNode>();
		ZTreeNode root = new ZTreeNode();//树的根节点
		root.setId("0");
		root.setPid("");
		root.setName("菜单列表");
		root.setType("vroot");//虚节点
		ztree.add(root);
		
//			String sysCode = ConfigUtil.getPropertie("sysType");
		List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
		sysMenuList = sysMenuMapper.getSysMenuList("");
		
		List<DicElement> dictEleList = new ArrayList<DicElement>();
		dictEleList = dicElementMapper.getDicEleListByTypeCode("XTFL");//从字典分类表中取出系统分类
		
		//从系统菜单关系表中，取出所有系统和菜单的数据
		List<RelSysMenu> relSysMenuList = relSysMenuMapper.getRelSysMenuList("");
		
		ZTreeNode stnode = null;
		for(DicElement dicEle : dictEleList){
			stnode = new ZTreeNode();
			stnode.setId(dicEle.getId());
			stnode.setPid("0");
			stnode.setName(dicEle.getElemName());
			stnode.setType("sysType"); //系统类型
			ztree.add(stnode);
			
			List<SysRegister> sysList = sysRegisterMapper.getSysListBySysType(dicEle.getId());
			ZTreeNode snode = null;
			for (SysRegister sys : sysList) {
				snode = new ZTreeNode();
				snode.setId(sys.getId());
				snode.setPid(sys.getSysType());
				snode.setName(sys.getSysName());
				/*if("综合运维管控平台".equals(sys.getSysName())){
					System.out.println(sys.getSysName());
				}*/
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
						//node.setSource(snode.getId());
						node.setSource(sysMenu.getMenuId());
						node.setType("menu");

						if(sysId != null && !"".equals(sysId )){
							List<RelSysMenu> oneSysMenuList = relSysMenuMapper.getRelSysMenuList(sysId);//该sysId下的系统菜单关系列表
							for(RelSysMenu onrRelSysMenu : oneSysMenuList){
								if(onrRelSysMenu.getMenuId().equals(sysMenu.getMenuId())){
									node.setChecked(true);
								}
							}
						}
						ztree.add(node);
					}
				}
			}
			
		}
		return ZTreeUtil.encapsulate(ztree);
	}
	
	/**
	 * 查询列表信息
	 * @param menuCode
	 * @param menuName
	 */
	public PageResult findSysMenuList(String menuCode, String menuName, String pageNumber, String pageSize) throws McpException{
		HashMap<String, Object> map = getParamMap();
		map.put("menuName", menuName);
		map.put("menuCode", menuCode);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		List<SysMenu> sysMenuList = sysMenuMapper.selectByPage(map);
		return new PageResult(map,sysMenuList);
	}
}
