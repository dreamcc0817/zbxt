package com.bonc.ioc.service;

import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.core.util.ZTreeUtil;
import com.bonc.ioc.dao.DicElementMapper;
import com.bonc.ioc.dao.SysOrgMapper;
import com.bonc.ioc.model.DicElement;
import com.bonc.ioc.model.SysOrg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 系统组织机构service层
 */
@Service
public class SysOrgService extends BaseService {
	@Resource
	private SysOrgMapper sysOrgMapper;
	@Resource
	private DicElementMapper dicElementMapper;
	
	/**
	 * 保存新增信息
	 * @param sysOrg
	 * @throws Exception
	 */
	public void save(SysOrg sysOrg) throws McpException {
		sysOrg.setIsValid(1);
		//sysOrg.setCreateAccId(getCurrentUser().getId());
		Date createTime = new Date(); // 创建时间
		sysOrg.setCreateTime(createTime);
		
		/**
		 * 判断当前一级，是否有相同的排序号
		 */
		SysOrg sysorg = sysOrgMapper.findSysOrgByorgCodeAndSort(sysOrg.getOrgPcode(),sysOrg.getSort());
		if(sysorg!= null){
			sysOrgMapper.updateOtherOrgSort(sysorg.getOrgPcode(),sysorg.getSort());
		}
		sysOrgMapper.save(sysOrg);
	}
	
	public void update(SysOrg sysOrg) throws McpException {
		sysOrgMapper.update(sysOrg);
	}
	
	/**
	 * 检查是否能进行删除操作
	 * @param idArr
	 * @return
	 */
	public String checkCanDel(String[] idArr) throws McpException{
		List<SysOrg> sysOrgList = new ArrayList<SysOrg>();
		sysOrgList = sysOrgMapper.checkCanDel(idArr);
		if(sysOrgList.size() > 0){
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
		sysOrgMapper.delete(ids);
	}
	
	/**
	 * 组织机构树及个节点
	 * @throws McpException
	 */
	public List<ZTreeNode> getSysOrgTree() throws McpException{
		List<ZTreeNode> ztree = new ArrayList<ZTreeNode>();
		ZTreeNode root = new ZTreeNode();//树的根节点
		root.setId("0");
		root.setPid("");
		root.setName("智慧鹿泉");
		root.setType("vroot");//虚节点
		ztree.add(root);
//		List<SysOrg> sysOrgList;
//		sysOrgList = sysOrgMapper.getSysOrgList("");
		List<DicElement> dictEleList = new ArrayList<DicElement>();
		dictEleList = dicElementMapper.getDicEleListByTypeCode("JGFL");//从字典分类表中取出系统分类
		
		ZTreeNode stnode = null;
		for(DicElement dicEle : dictEleList){
			stnode = new ZTreeNode();
			stnode.setId(dicEle.getId());
			stnode.setPid("0");
			stnode.setName(dicEle.getElemName());
			stnode.setType("orgType"); //系统类型
			ztree.add(stnode);
			
			List<SysOrg> orgList = sysOrgMapper.getSysOrgListByOrgType(dicEle.getId());
			
			ZTreeNode node =null;
			if(orgList != null && orgList.size() > 0){
				for (SysOrg sysOrg : orgList){
					node = new ZTreeNode();
					node.setSource(sysOrg.getId());
					node.setId(sysOrg.getOrgCode());//将组织机构的code作为树的id显示到页面上
					if(sysOrg.getOrgPcode() == null || "".equals(sysOrg.getOrgPcode()))
						node.setPid(sysOrg.getOrgType());
					else
						node.setPid(sysOrg.getOrgPcode());
					node.setName(sysOrg.getOrgNameF());
					node.setType("org");
					ztree.add(node);
				}
			}
		}
		return ZTreeUtil.encapsulate(ztree);
	}
	
	/**
	 * 查询单位列表信息
	 * @param orgCode
	 * @param orgName
	 * @param orgType
	 */
	public PageResult findOrgList(String orgCode, String orgName, String orgType, String pageNumber, String pageSize) throws McpException{
		
		HashMap<String, Object> map = getParamMap();
		map.put("orgCode", orgCode);
		map.put("orgName", orgName);
		map.put("orgType", orgType);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		List<SysOrg> orgList = sysOrgMapper.selectByPage(map);
		/**
		 * 如果父节点名称是空的，则给它命名为东莞东城街道IOC大数据平台项目
		 */
		for(SysOrg sysOrg: orgList){
			if(sysOrg.getpOrgName() == null ||"".equals(sysOrg.getpOrgName()))
				sysOrg.setpOrgName("东莞东城街道IOC大数据平台项目");
		}
		return new PageResult(map, orgList);
	}
	
	/**
	 * 根据组织机构编码获取组织机构详情
	 * @param orgCode
	 * @throws McpException
	 */
	public SysOrg findByCode(String orgCode) throws McpException {
		SysOrg sysOrg = null;
		sysOrg = sysOrgMapper.findByCode(orgCode);
		if(sysOrg.getpOrgName() == null ||"".equals(sysOrg.getpOrgName()))
			sysOrg.setpOrgName("东莞东城街道IOC大数据平台");
		return sysOrg;
	}
}
