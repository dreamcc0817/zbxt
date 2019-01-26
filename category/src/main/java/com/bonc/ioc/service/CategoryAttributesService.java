package com.bonc.ioc.service;

import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.dao.CategoryAttributesMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class CategoryAttributesService extends BaseService {
	@Resource
	private CategoryAttributesMapper categoryAttributesMapper;

	@Resource
	private CategoryAttributesDicService categoryAttributesDicService;

	@Resource
	IndexToAttrsDicsService indexToAttrsDicsService;

	//按attrId查categoryPropertyInfo数据
	public HashMap<String, Object> getCategoryPropertyInfoService(HashMap<String, Object> map) throws McpException {
		return categoryAttributesMapper.getCategoryPropertyInfoDao(map);
	}

	//按attrId查categoryPropertyInfo数据和categoryDictInfo数据并做统一返回处理
	public HashMap<String, Object> getCategoryPropertyInfoReturnService(HashMap<String, Object> map) throws McpException {
		HashMap<String, Object> returnMap = new HashMap<>();
		returnMap.put("categoryPropertyInfo", this.getCategoryPropertyInfoService(map));
		returnMap.put("categoryDictInfo", categoryAttributesDicService.getCategoryDictInfoService(map));
		return returnMap;
	}

	//新增一条指标类目属性数据信息
	public int addCategoryAttributesService(HashMap<String, Object> map) throws McpException {
		int result = categoryAttributesMapper.addCategoryAttributesDao(map);
		return result;
	}

	//统一新增一条指标类目属性和其字典字段
	public AppReply addCategoryPropertyInfoService(HashMap<String, Object> map) throws McpException, JSONException {
		JSONArray categoryDictArray = new JSONArray(map.get("categoryDictArray").toString());
		int addResult = this.addCategoryAttributesService(map);
		if (addResult > 0) {
			Integer attrId = Integer.parseInt(this.getAttrIdByFullcodeService(map).get(0).toString());
			HashMap<String, Object> paramsMap = new HashMap<String, Object>();
			String dicName;
			paramsMap.put("attrId", attrId);
			for (int i = 0; i < categoryDictArray.length(); i++) {
				dicName = categoryDictArray.getJSONObject(i).getString("dicName");
				paramsMap.put("dicName", dicName);
				int result = categoryAttributesDicService.addCategoryAttributesDicService(paramsMap);
				if (result <= 0) {
					AppReply.error("新增失败");
				}
			}
			return AppReply.success("新增成功");
		}
		return AppReply.error("新增失败");
	}

	//更新类目属性。
	//注：同时需要对比参数中的dicName和数据库中的dicName，重合的不做操作，参数中独有的insert，数据库独有的delete
	public AppReply updateCategoryPropertyInfoService(HashMap<String, Object> map) throws McpException, JSONException {
		JSONArray categoryDictArray = new JSONArray(map.get("categoryDictArray").toString());
		HashSet<String> insertDicNameSet = new HashSet<String>();
		HashSet<String> deleteDicNameSet = new HashSet<String>();
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		String oleDicName = "";
		List<HashMap<String, Object>> oldDicNameMapList = categoryAttributesDicService.getCategoryDictInfoService(map);
		int result = this.updateCategoryAttributesService(map);
		if (result <= 0) {
			return AppReply.error("更新类目属性失败");
		}
		for (int i = 0; i < categoryDictArray.length(); i++) {
			insertDicNameSet.add(categoryDictArray.getJSONObject(i).getString("dicName"));
		}
		for (int i = 0; i < oldDicNameMapList.size(); i++) {
			oleDicName = oldDicNameMapList.get(i).get("dicName").toString();
			if (insertDicNameSet.contains(oleDicName)) {
				insertDicNameSet.remove(oleDicName);
			} else {
				deleteDicNameSet.add(oleDicName);
			}
		}
		paramsMap.put("attrId", Integer.parseInt(map.get("attrId").toString()));
		Iterator iterator = deleteDicNameSet.iterator();
		while (iterator.hasNext()) {
			paramsMap.put("dicName", iterator.next());
			int deleteResult = categoryAttributesDicService.deleteCategoryAttributesDicByAttrIdAndDicNameService(paramsMap);
			if (deleteResult <= 0) {
				return AppReply.error("更新类目属性失败");
			}
		}
		Iterator iterator2 = insertDicNameSet.iterator();
		while (iterator2.hasNext()) {
			paramsMap.put("dicName", iterator2.next());
			int addResult = categoryAttributesDicService.addCategoryAttributesDicService(paramsMap);
			if (addResult <= 0) {
				return AppReply.error("更新类目属性失败");
			}
		}
		return AppReply.success("更新类目属性成功");
	}

	//按照categoryId删除category_attributes表中信息
	public void deleteCategoryAttributesByCategoryIdService(HashMap<String, Object> map) throws McpException {
		categoryAttributesMapper.deleteCategoryAttributesByCategoryIdDao(map);
	}

	//分页查询，按照categoryFullcode查找本类目与父类目的属性和值域
	public PageResult selectByPagegetCategoryPropertyInfosService(HashMap<String, Object> map) throws McpException {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> pageMap = getParamMap();
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		map.putAll(pageMap);
		String[] fullCodeArray = ((String) map.get("categoryFullcode")).split("_");
		String everyFatherFullcode = fullCodeArray[0];
		String allFatherFullCode = everyFatherFullcode;

		//根据fullCode得到本身和父类fullCodes sql拼接
		for (int i = 1; i < fullCodeArray.length; i++) {
			everyFatherFullcode = everyFatherFullcode + "_" + fullCodeArray[i];
			allFatherFullCode = allFatherFullCode + "," + everyFatherFullcode;
		}
		map.put("allFatherFullCode", allFatherFullCode);
		list = categoryAttributesMapper.selectByPagegetCategoryPropertyInfosDao(map);
		for (int j = 0; j < list.size(); j++) {
			paramsMap = list.get(j);
			list.get(j).put("attrs", categoryAttributesDicService.getCategoryDictInfoService(paramsMap));
			//list.get(j).remove("attrId");
			if (list.get(j).get("categoryFullcode").toString().equals(map.get("categoryFullcode").toString())) {
				list.get(j).put("attributeType", 1);
			} else {
				list.get(j).put("attributeType", 0);
			}
		}
		return new PageResult(map, list);
	}

	//按照attrId删除category_attributes表中属性信息、属性与字典关联表信息、属性与指标关联表关联关系
	public AppReply deleteCategoryPropertyInfoService(HashMap<String, Object> map) throws McpException {
		//首先按照attrId删除index_to_attrs_dics表中指标、类目和属性的关联关系
		indexToAttrsDicsService.deleteIndexToAttrsDicsByAttrIdService(map);

		//然后按照attrId删除category_attributes_dic表中字典项
		categoryAttributesDicService.deleteCategoryAttributesDicByAttrIdService(map);

		//最后删除category_attributes表中属性信息
		int result = this.deleteCategoryAttributesByAttrIdService(map);

		if (result > 0) {
			return AppReply.success("删除类目属性成功");
		}

		return AppReply.error("删除类目属性失败");
	}

	public int deleteCategoryAttributesByAttrIdService(HashMap<String, Object> map) throws McpException {
		int result = categoryAttributesMapper.deleteCategoryAttributesByAttrIdDao(map);
		return result;
	}

	public List getAttrIdByFullcodeService(HashMap<String, Object> map) throws McpException {
		return categoryAttributesMapper.getAttrIdByFullcodeDao(map);
	}

	public int updateCategoryAttributesService(HashMap<String, Object> map) throws McpException {
		int result = categoryAttributesMapper.updateCategoryAttributesDao(map);
		return result;
	}

	public int updateAttributesWhenCategoryMoveService(HashMap<String, Object> map) throws McpException {
		int result = categoryAttributesMapper.updateAttributesWhenCategoryMoveDao(map);
		return result;
	}

}
