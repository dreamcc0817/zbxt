package com.bonc.ioc.service;

import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.dao.DeptContactsMapper;
import com.bonc.ioc.model.DeptContacts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 组织通讯录service层
 */
@Service
public class DeptContactsService extends BaseService {
	@Resource
	private DeptContactsMapper deptContactsMapper;
	
	/**
	 * 组织通讯录(管理者)——添加组织信息
	 * @param deptContacts
	 * @throws McpException
	 */
	public void addORG(DeptContacts deptContacts) throws McpException {
		deptContactsMapper.add(deptContacts);
	}
	
	/**
	 * 组织通讯录(管理者)——修改组织信息
	 * @param deptContacts
	 * @throws McpException
	 */
	public void editORG(DeptContacts deptContacts) throws McpException {
		deptContactsMapper.edit(deptContacts);
	}
	
	/**
	 * 组织通讯录(管理者)——删除组织信息
	 * @param id
	 * @throws McpException
	 */
	public void delORG(String id) throws McpException {
		deptContactsMapper.del(id);
	}
	
	/**
	 * 组织列表的生成
	 * @return
	 * @throws McpException
	 */
	public List<DeptContacts> getORG(String keyword) throws McpException {
		return deptContactsMapper.getORG(keyword);
	}
	
	/**
	 * 组织通讯录(管理者)——添加部门通讯录
	 * @param deptContacts
	 * @throws McpException
	 */
	public void addDEPT(DeptContacts deptContacts) throws McpException {
		deptContactsMapper.add(deptContacts);
	}
	
	/**
	 * 组织通讯录(管理者)——编辑部门通讯录
	 * @param deptContacts
	 * @throws McpException
	 */
	public void editDEPT(DeptContacts deptContacts) throws McpException {
		deptContactsMapper.edit(deptContacts);
	}
	
	/**
	 * 组组织通讯录(管理者)——批量删除部门通讯录
	 * @param ids
	 * @throws McpException
	 */
	public void delDEPT(String ids) throws McpException {
		if(ids.contains(",")){
			String[] idS = ids.split(",");
			for(String id : idS){
				deptContactsMapper.del(id);
			}
		}else{
			deptContactsMapper.del(ids);
		}
	}
	
	/**
	 * 组织通讯录(管理者)——分页查询部门通讯录
	 * @param keyword
	 * @param deptId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws McpException
	 */
	public PageResult selectByPageDEPT(String keyword,
	                                   String deptId,
	                                   String pageNumber,
	                                   String pageSize) throws McpException {
		HashMap<String, Object> map = getParamMap();
		map.put("keyword", keyword);
		map.put("deptId", deptId);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		List<DeptContacts> list = deptContactsMapper.selectByPageDEPT(map);
		return new PageResult(map, list);
	}
}
