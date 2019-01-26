package com.bonc.ioc.service;

import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.core.util.ZTreeUtil;
import com.bonc.ioc.dao.DicElementMapper;
import com.bonc.ioc.dao.DicTypeMapper;
import com.bonc.ioc.model.DicElement;
import com.bonc.ioc.model.DicType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 字典管理service层
 */
@Service
public class DicService extends BaseService {
	@Resource
	private DicTypeMapper dicTypeMapper;
	@Resource
	private DicElementMapper dicElementMapper;
	
	/**
	 * 保存字典分类信息
	 * @param dicType
	 * @throws McpException
	 */
	public void saveDicType(DicType dicType) throws McpException {
		dicType.setIsValid(1);
		//dicType.setCreateAccId(getCurrentUser().getId());// shiro获取当前操作用户信息
		Date createTime = new Date(); // 创建时间
		dicType.setCreateTime(createTime);
		
		/**
		 * 判断当前一级，是否有相同的排序号
		 */
		DicType dictype = dicTypeMapper.findDicTypeByTypeCodeAndSort(dicType.getTypePcode(), dicType.getSort());
		if(dictype!= null){//如果同一级，有相同的排序号
			dicTypeMapper.updateOtherTypeSort(dictype.getTypePcode(),dictype.getSort());//更新其他的排序号
		}
		dicTypeMapper.saveDicType(dicType);
	}
	
	/**
	 * 更新字典分类信息
	 * @param dicType
	 * @throws Exception
	 */
	public void updateDicType(DicType dicType) throws McpException{
		//dicType.setUpdateAccId(getCurrentUser().getId());// shiro获取当前操作用户信息
		Date createTime = new Date(); // 创建时间
		dicType.setUpdateTime(createTime);
		
		/**
		 * 判断当前一级，是否有相同的排序号
		 */
		DicType dictype = dicTypeMapper.findDicTypeByTypeCodeAndSort(dicType.getTypePcode(), dicType.getSort());
		if(dictype != null && !dicType.getId().equals(dictype.getId())){//如果同一级且不是自身，有相同的排序号
			dicTypeMapper.updateOtherTypeSort(dictype.getTypePcode(),dictype.getSort());//更新其他的排序号
		}
		
		dicTypeMapper.updateDicType(dicType);
	}
	
	/**
	 * 检查是否能进行删除操作
	 * @param idArr
	 * @return
	 */
	public String checkCanDel(String[] idArr) throws McpException{
		List<DicType> dicTypeList = new ArrayList<DicType>();
		dicTypeList = dicTypeMapper.checkCanDel(idArr);
		if(dicTypeList.size() > 0){//如果有，则说明不能删除
			return "false";
		}else{
			return "able";
		}
	}
	
	/**
	 * 删除字典分类信息
	 * @param idArr
	 * @throws McpException
	 */
	public void deleteDicType(String[] idArr) throws McpException{
		dicTypeMapper.deleteDicType(idArr);
	}
	
	/**
	 * 查询字典分类树
	 * @return
	 */
	public List<ZTreeNode> getDicTypeTree() throws McpException{
		List<ZTreeNode> ztree = new ArrayList<ZTreeNode>();
		ZTreeNode root = new ZTreeNode();//树的根节点
		root.setId("0");
		root.setPid("");
		root.setName("字典分类");
		root.setType("vroot"); //虚节点
		ztree.add(root);
		List<DicType> dicTypeList;
		dicTypeList = dicTypeMapper.getDicTypeList("");
		ZTreeNode node = null;
		if(dicTypeList != null && dicTypeList.size() > 0){
			for (DicType dicType : dicTypeList) {
				node = new ZTreeNode();
				node.setId(dicType.getTypeCode());//将字典分类的code作为树的id显示到页面上
				if(dicType.getTypePcode() == null || "".equals(dicType.getTypePcode()))
					node.setPid("0");
				else
					node.setPid(dicType.getTypePcode());
				node.setName(dicType.getTypeName());
				/*node.setLevel(dicType.getTypeLevel());*/
				node.setSource(dicType.getTypeLevel());
				node.setType("dictype");
				ztree.add(node);
			}
			return ZTreeUtil.encapsulate(ztree);
		}
		return null;
	}
	
	/**
	 * 查询字典分类列表数据
	 * @param typename
	 * @param typecode
	 * @return
	 */
	public PageResult getDicTypeList(String typename, String typecode, String pageNumber, String pageSize) throws McpException{
		HashMap<String, Object> map = getParamMap();
		map.put("typeCode", typecode);
		map.put("typeName", typename);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		List<DicType> eleTypeList = dicTypeMapper.selectByPage(map);
		/**
		 * 如果父节点名称是空的，则给它命名为字典分类
		 */
		for(DicType dicType: eleTypeList){
			if(dicType.getpTypeName() == null || "".equals(dicType.getpTypeName()))
				dicType.setpTypeName("字典分类");
			dicType.setElemCount(dicTypeMapper.countChildrenByCode(dicType.getTypeCode()));
		}
		return new PageResult(map, eleTypeList);
	}
	
	/**
	 * 保存字典元素
	 */
	public void saveDicElement(DicElement dicElement) throws McpException{
		dicElement.setIsValid(1);
		//dicElement.setCreateAccId(getCurrentUser().getId());// shiro获取当前操作用户信息
		Date createTime = new Date(); // 创建时间
		dicElement.setCreateTime(createTime);
		
		//DicElement dicele = dicTypeMapper.findDicTypeByTypeCodeAndSort(dicType.getTypePcode(), dicType.getSort());
		//if(dictype != null && !dicType.getId().equals(dictype.getId())){//如果同一级且不是自身，有相同的排序号
		//	dicTypeMapper.updateOtherTypeSort(dictype.getTypePcode(),dictype.getSort());//更新其他的排序号
		//}
		
		/**
		 * 如果有相同的排序，更新其他的排序号码。
		 */
		dicElementMapper.updateOtherEleSort(dicElement.getSort());
		dicElementMapper.saveDicElement(dicElement);
	}
	
	/**
	 * 更新字典元素
	 */
	public void updateDicElement(DicElement dicElement) throws McpException{
		//dicElement.setUpdateAccId(getCurrentUser().getId());// shiro获取当前操作用户信息
		Date createTime = new Date(); // 创建时间
		dicElement.setUpdateTime(createTime);
		
		/**
		 * 如果有相同的排序，更新其他的排序号码。
		 */
		dicElementMapper.updateOtherEleSort(dicElement.getSort());
		dicElementMapper.updateDicElement(dicElement);
	}
	
	public void deleteDicElement(String[] idArr) throws McpException{
		dicElementMapper.deleteDicElement(idArr);
	}
	
	/**
	 * 查询字典元素列表数据
	 * @param elemname
	 * @param typecode
	 * @return
	 */
	public PageResult getElemList(String elemname, String typecode, String pageNumber, String pageSize) throws McpException{
		HashMap<String, Object> map = getParamMap();
		map.put("typeCode", typecode);
		map.put("elemName", elemname);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		List<DicElement> dicElemList = dicElementMapper.selectByPage(map);
		return new PageResult(map, dicElemList);
	}
	
	/**
	 * 根据字典分类查询字典
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getDicListByType(Map<String, Object> params) {
		return dicTypeMapper.getDicListByType(params);
	}
}
