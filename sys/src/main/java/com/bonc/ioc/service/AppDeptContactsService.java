package com.bonc.ioc.service;

import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.util.ZTreeUtil;
import com.bonc.ioc.dao.AppDeptContactsMapper;
import com.bonc.ioc.model.DeptContacts;
import com.bonc.ioc.model.DicType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 移动端组织通讯录service层
 */
@Service
public class AppDeptContactsService {
	@Resource
	private AppDeptContactsMapper appDeptContactsMapper;
	
	/**
	 * 通讯录添加部门的部门列表
	 * @param keyword
	 * @return
	 */
	public List<DeptContacts> getDeptList(String keyword) throws McpException {
		return appDeptContactsMapper.getDeptList(keyword);
	}
	
	/**
	 * 根据用户主键查询通讯录中的部门主键
	 * @param userId
	 * @return
	 */
	public List<String> getDeptIdsByAppUserId(String userId) throws McpException {
		return appDeptContactsMapper.getDeptIdsByAppUserId(userId);
	}
	
	/**
	 * 通讯录添加部门
	 * @param id
	 * @param userId
	 * @param deptId
	 * @throws McpException
	 */
	public void addDeptForUser(String id, String userId, String deptId) throws McpException {
		appDeptContactsMapper.addDeptForUser(id, userId, deptId);
	}
	
	//public List<DeptContacts> getDeptContactsList(String userId) throws McpException {
	//	return appDeptContactsMapper.getDeptContactsList(userId);
	//}
	
	/**
	 * 政府通讯录树结构
	 * @param userId
	 * @return
	 */
	public List<ZTreeNode> getDeptContactsTree(String userId){
		List<ZTreeNode> ztree = new ArrayList<ZTreeNode>();
		ZTreeNode root = new ZTreeNode();//树的根节点
		root.setId("0");
		root.setPid("");
		root.setName("部门通讯录");
		root.setType("vroot"); //虚节点
		ztree.add(root);
		List<DeptContacts> deptContactsList;
		deptContactsList = appDeptContactsMapper.getDeptContactsList(userId);
		ZTreeNode node = null;
		if(deptContactsList != null && deptContactsList.size() > 0){
			for (DeptContacts deptContacts : deptContactsList) {
				node = new ZTreeNode();
				node.setId(deptContacts.getId());//将部门通讯录的code作为树的id显示到页面上
				if(deptContacts.getParentId() == null || "".equals(deptContacts.getParentId()))
					node.setPid("0");
				else
					node.setPid(deptContacts.getParentId());
				node.setName(deptContacts.getDeptName());
				//node.setLevel(dicType.getTypeLevel());
				node.setSource(deptContacts.getDeptPhone());
				node.setType("deptContacts");
				ztree.add(node);
			}
			return ZTreeUtil.encapsulate(ztree);
		}
		return null;
	}
}
