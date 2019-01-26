package com.bonc.ioc.service;

import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.dao.SearchDao;
import com.bonc.ioc.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhouyu on 2018/11/6.
 */
@Service
@Transactional
public class SearchService extends BaseService {
	@Resource
	private SearchDao searchDao;

	public List<Map<String, Object>> getCategoryProps(String categoryId) throws McpException {
		List<Map<String, Object>> cg = searchDao.getCategoryProps(categoryId);
		return cg;
	}

	public PageResult selectByPageFindByKeyWords(HashMap<String, Object> map) throws McpException {
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		HashMap<String, Object> pageMap = getParamMap();
		map.putAll(pageMap);
		String[] fullCodeArray;
		String everyFatherFullcode;
		String allFatherFullCode;
		String totalRows = searchDao.getSearchTotalNumber(map)+"";
		map.put("totalRows",totalRows);
		List<Map<String, Object>> list = searchDao.selectByPageFindByKeyWords(map);
		//集合分页，新增查询结果总数量
		map.put("totalRows",totalRows);
		//异常频现处
		for (int i = 0; i < list.size(); i++) {
			fullCodeArray = list.get(i).get("categoryFullcode").toString().split("_");
			everyFatherFullcode = fullCodeArray[0];
			allFatherFullCode = everyFatherFullcode;
			for (int j = 1; j < fullCodeArray.length; j++) {
				everyFatherFullcode = everyFatherFullcode + "_" + fullCodeArray[j];
				allFatherFullCode = allFatherFullCode + "," + everyFatherFullcode;
			}
			list.get(i).remove("categoryFullcode");
			paramsMap.put("allFatherFullCode", allFatherFullCode);
			list.get(i).put("categoryNames", searchDao.selectAllCategoryNames(paramsMap));
		}
		return new PageResult(map, list);
	}

	public List<Map<String, Object>> getIndexsForEcharts() throws McpException {
		TreeSet categoryPidSet = new TreeSet();
		List<Map<String, Object>> list = searchDao.getCategoryList();
		for (int i = 0; i < list.size(); i++) {
			categoryPidSet.add(Integer.parseInt(list.get(i).get("categoryPid").toString()));
		}
		return this.getChildCategory(list, categoryPidSet, -1);
	}

	public List<Map<String, Object>> getChildCategory(List<Map<String, Object>> list, TreeSet categoryPidSet, Integer categoryId) throws McpException {
		List<Map<String, Object>> returnList = new ArrayList<>();
		if (categoryPidSet.contains(categoryId)) {
			for (int i = 0; i < list.size(); i++) {
				if (Integer.parseInt(list.get(i).get("categoryPid").toString()) == categoryId) {
					Map<String, Object> map = new HashMap<>();
					map.put("name", list.get(i).get("categoryName"));
					map.put("children", this.getChildCategory(list, categoryPidSet, Integer.parseInt(list.get(i).get("categoryId").toString())));
					returnList.add(map);
				}
			}
			return returnList;
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (Integer.parseInt(list.get(i).get("categoryId").toString()) == categoryId) {
					returnList = searchDao.selectIndexName(list.get(i).get("categoryFullcode").toString());
					for (int j = 0; returnList != null && j < returnList.size(); j++) {
						returnList.get(j).put("value", 666);
					}
					return returnList;
				}
			}
		}
		return returnList;
	}
	public List<Category> getAllTopLevelCategory() throws McpException{
		return searchDao.getAllTopLevelCategory();
	}
}
