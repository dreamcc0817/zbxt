package com.bonc.ioc.service;

import com.bonc.ioc.core.base.service.BaseService;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.dao.CategoryMapper;
import com.bonc.ioc.util.PinyinUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service
@Transactional
public class CategoryService extends BaseService {
	@Resource
	private CategoryMapper categoryMapper;

	@Resource
	IndexToAttrsDicsService indexToAttrsDicsService;

	@Resource
	IndexService indexService;

	@Resource
	CategoryAttributesDicService categoryAttributesDicService;

	@Resource
	CategoryAttributesService categoryAttributesService;

	//类目拖拽功能实现主方法
	public AppReply moveCategoryService(HashMap<String, Object> map) throws McpException {
		Integer moveBeforeFatherCategoryId;
		Integer moveBeforeFatherCategoryLevel;
		String moveBeforeFatherCategoryFullcode;
		HashMap<String, Object> moveBeforeFatherCategoryMap = new HashMap<>();

		Integer categoryId = Integer.parseInt(map.get("categoryId").toString());
		String categoryFullcode;
		HashMap<String, Object> categoryMap = categoryMapper.getCategoryDao(map);

		Integer moveAfterFatherCategoryId = Integer.parseInt(map.get("moveAfterFatherCategoryId").toString());
		Integer moveAfterFatherCategoryLevel;
		String moveAfterFatherCategoryFullcode;
		HashMap<String, Object> moveAfterFatherCategoryMap = new HashMap<>();

		if (categoryMap == null) {
			return AppReply.error("传入拖拽的类目id错误");
		}
		categoryFullcode = categoryMap.get("categoryFullcode").toString();

		//获取拖拽前父类目信息
		moveBeforeFatherCategoryId = Integer.parseInt(categoryMap.get("categoryPid").toString());
		if (moveBeforeFatherCategoryId == -1) {
			moveBeforeFatherCategoryLevel = 0;
			moveBeforeFatherCategoryFullcode = "";
		} else {
			HashMap<String, Object> moveBeforeFatherCategoryParamsMap = new HashMap<>();
			moveBeforeFatherCategoryParamsMap.put("categoryId", moveBeforeFatherCategoryId);
			moveBeforeFatherCategoryMap = categoryMapper.getCategoryDao(moveBeforeFatherCategoryParamsMap);
			moveBeforeFatherCategoryLevel = Integer.parseInt(moveBeforeFatherCategoryMap.get("categoryLevel").toString());
			moveBeforeFatherCategoryFullcode = moveBeforeFatherCategoryMap.get("categoryFullcode").toString();
		}

		//获取移动后父类目信息
		if (moveAfterFatherCategoryId == -1) {
			moveAfterFatherCategoryLevel = 0;
			moveAfterFatherCategoryFullcode = "";
		} else {
			HashMap<String, Object> moveAfterFatherCategoryParamsMap = new HashMap<>();
			moveAfterFatherCategoryParamsMap.put("categoryId", moveAfterFatherCategoryId);
			moveAfterFatherCategoryMap = categoryMapper.getCategoryDao(moveAfterFatherCategoryParamsMap);
			if (moveAfterFatherCategoryMap == null) {
				return AppReply.error("传入拖拽后父类目id错误");
			}
			moveAfterFatherCategoryLevel = Integer.parseInt(moveAfterFatherCategoryMap.get("categoryLevel").toString());
			moveAfterFatherCategoryFullcode = moveAfterFatherCategoryMap.get("categoryFullcode").toString();
		}

		//判断被移动的指标类目是否包含移动后的父类目，如果包含，传入参数错误！
		if ((moveAfterFatherCategoryFullcode + "_").indexOf(categoryFullcode) != -1) {
			return AppReply.error("被拖拽的类目  为  拖拽后父类目  的  父类目，类目参数关系错误，拒绝拖拽！");
		}

		if (moveBeforeFatherCategoryId == moveAfterFatherCategoryId) {
			return AppReply.error("禁止在同一类目下进行拖拽！");
		}
		//信息获取结束，进行类目标、类目属性表、指标表更新 和 指标属性关联表删除工作

		//①删除指标属性关联表信息：---------------------------------------------------------------------
		//拖拽前后，指标父类目的共有属性不做操作；删除 指标拖拽前父类目特有属性 与 在拖拽涉及到指标  关联关系
		//分别拆分拼接 moveBeforeFatherCategoryFullcode 和 moveAfterFatherCategoryFullcode
		//添加到TreeSet中进行所有父类目categoryFullCode去重，筛选出拖拽前父类目独有的类目categoryFullcode
		//再按照筛选出的categoryFullcode对应到其所有的私有属性，在移动类目下指标范围内删除属性和指标关联关系
		TreeSet<String> allFatherFullcodeBeforeSet = new TreeSet();
		TreeSet<String> allFatherFullcodeBeforeNeedDeleteSet = new TreeSet();
		TreeSet<String> allFatherFullcodeAfterSet = new TreeSet();
		if (moveBeforeFatherCategoryId != -1) {
			String[] fullCodeArrayBefore = moveBeforeFatherCategoryFullcode.split("_");
			String everyFatherFullcodeBefore = fullCodeArrayBefore[0];
			allFatherFullcodeBeforeSet.add(everyFatherFullcodeBefore.toString());
			for (int i = 1; i < fullCodeArrayBefore.length; i++) {
				everyFatherFullcodeBefore = everyFatherFullcodeBefore + "_" + fullCodeArrayBefore[i];
				allFatherFullcodeBeforeSet.add(everyFatherFullcodeBefore.toString());
			}
		}
		if (moveAfterFatherCategoryId != -1) {
			String[] fullCodeArrayAfter = moveAfterFatherCategoryFullcode.split("_");
			String everyFatherFullcodeAfter = fullCodeArrayAfter[0];
			allFatherFullcodeAfterSet.add(everyFatherFullcodeAfter.toString());
			for (int i = 1; i < fullCodeArrayAfter.length; i++) {
				everyFatherFullcodeAfter = everyFatherFullcodeAfter + "_" + fullCodeArrayAfter[i];
				allFatherFullcodeAfterSet.add(everyFatherFullcodeAfter.toString());
			}
		}

		//allFatherFullcodeAfterSet和allFatherFullcodeBeforeSet去重
		//去重后allFatherFullcodeBeforeNeedDeleteSet为需要删除指标属性关联关系的 属性所在的类目categoryFullcode
		//allFatherFullcodeAfterSet为拖住后指标特有的 可选属性  所在的类目categoryFullcode集合
		//在去重的过程中删除指标属性关联关系即可，其实也可以不进行Set去重，以是否包含为判断条件进行删除即可。
		// 去重后两个Set作为保留，可能后续增添新功能用得上
		HashMap<String, Object> moveWhenCategoryMoveParaMap = new HashMap<>();
		moveWhenCategoryMoveParaMap.put("moveCategoryFullcode", categoryFullcode);
		Iterator<String> allFatherFullcodeBeforeSetIte = allFatherFullcodeBeforeSet.iterator();
		while (allFatherFullcodeBeforeSetIte.hasNext()) {
			String str = allFatherFullcodeBeforeSetIte.next();
			if (allFatherFullcodeAfterSet.contains(str)) {
				allFatherFullcodeAfterSet.remove(str);
				//allFatherFullcodeBeforeSet.remove(str);
				allFatherFullcodeBeforeNeedDeleteSet.add(str);
			} else {
				moveWhenCategoryMoveParaMap.put("fatherCategoryFullcode", str);
				indexToAttrsDicsService.deleteWhenCategoryMoveService(moveWhenCategoryMoveParaMap);
			}
		}

		//下方234更新fullcode的方式采取字段替换的方式
		// 当拖拽前为顶级类目或者拖拽后为顶级类目，替换会出现bug
		//拖拽前如果为顶级，需要在编码之前直接加上替换后父类目的categoryFullcode
		//处理方式：拖拽后categoryFullcode加上一个 "_" 符号 并且在Sql中判断 moveBeforeFatherCategoryId 值来执行拼接
		//拖拽后如果为顶级，需要在删除编码后一起删除 "_" 符号
		//处理方式：拖拽前categoryFullcode加上一个 "_" 符号就可以了
		HashMap<String, Object> updateFullcodeWhenCategoryMoveParaMap = new HashMap<>();
		updateFullcodeWhenCategoryMoveParaMap.put("moveCategoryFullcode", categoryFullcode);
		updateFullcodeWhenCategoryMoveParaMap.put("moveBeforeFatherCategoryId", moveBeforeFatherCategoryId);
		if (moveAfterFatherCategoryId == -1) {
			updateFullcodeWhenCategoryMoveParaMap.put("moveBeforeFatherCategoryFullcode", moveBeforeFatherCategoryFullcode + "_");
			updateFullcodeWhenCategoryMoveParaMap.put("moveAfterFatherCategoryFullcode", moveAfterFatherCategoryFullcode);
		} else if (moveBeforeFatherCategoryId == -1) {
			updateFullcodeWhenCategoryMoveParaMap.put("moveBeforeFatherCategoryFullcode", moveBeforeFatherCategoryFullcode);
			updateFullcodeWhenCategoryMoveParaMap.put("moveAfterFatherCategoryFullcode", moveAfterFatherCategoryFullcode + "_");
		} else {
			updateFullcodeWhenCategoryMoveParaMap.put("moveBeforeFatherCategoryFullcode", moveBeforeFatherCategoryFullcode);
			updateFullcodeWhenCategoryMoveParaMap.put("moveAfterFatherCategoryFullcode", moveAfterFatherCategoryFullcode);
		}

		//②更新指标表字段信息：----------------------------------------------------------------------
		//更新categoryFullcode和indexFullcode字段，将拖拽前的父类目的categoryFullcode字符串替换成拖拽后的父类目的categoryFullcode字符串
		indexService.updateIndexWhenCategoryMoveService(updateFullcodeWhenCategoryMoveParaMap);

		//③更新类目属性表信息：-----------------------------------------------------------------------
		//更新categoryFullcode字段，将拖拽前的父类目的categoryFullcode字符串替换成拖拽后的父类目的categoryFullcode字符串
		categoryAttributesService.updateAttributesWhenCategoryMoveService(updateFullcodeWhenCategoryMoveParaMap);

		//④更新类目标信息：----------------------------------------------------------------------------
		//1.修改被拖动的类目的Pid，其下属子类目不做修改
		//2.修改被拖动类目本身及其子类目的categoryFullcode字段，将拖拽前的父类目的categoryFullcode字符串替换成拖拽后的父类目的categoryFullcode字符串
		//3.修改被拖动类目本身及其子类目的categoryLevel
		HashMap<String, Object> updateLevelWhenCategoryMoveParaMap = new HashMap<>();
		updateLevelWhenCategoryMoveParaMap.put("moveCategoryFullcode", categoryFullcode);
		updateLevelWhenCategoryMoveParaMap.put("changeLevel", moveAfterFatherCategoryLevel - moveBeforeFatherCategoryLevel);
		this.updateLevelWhenCategoryMoveService(updateLevelWhenCategoryMoveParaMap);//3
		this.updatePidWhenCategoryMoveService(map); // 1
		this.updateFullcodeWhenCategoryMoveService(updateFullcodeWhenCategoryMoveParaMap);  //2


		return AppReply.success();
	}

	//按照类目id查询类目基本信息
	public AppReply<HashMap<String, Object>> getCategoryService(HashMap<String, Object> map) throws McpException {
		HashMap<String, Object> returnMap = categoryMapper.getCategoryDao(map);
		if (returnMap == null) {
			return AppReply.error("查询结果为空");
		}
		return AppReply.success(returnMap);
	}

	//递归一次性返回所有类目信息
	public AppReply<List<HashMap<String, Object>>> getChildrenParallelCategoryService() throws McpException {
		List<HashMap<String, Object>> list = categoryMapper.getChildrenParallelCategoryDao();
		if(list.isEmpty()){
			return AppReply.error("查询类目为空");
		}
		TreeSet categoryPidSet = new TreeSet();
		for (int i = 0; i < list.size(); i++) {
			categoryPidSet.add(Integer.parseInt(list.get(i).get("categoryPid").toString()));
		}
		return AppReply.success(this.getChildCategory(list, categoryPidSet, -1));
	}

	public List<HashMap<String, Object>> getChildCategory(List<HashMap<String, Object>> list, TreeSet categoryPidSet, Integer categoryId) throws McpException {
		List<HashMap<String, Object>> returnList = new ArrayList<>();
		if (categoryPidSet.contains(categoryId)) {
			for (int i = 0; i < list.size(); i++) {
				if (Integer.parseInt(list.get(i).get("categoryPid").toString()) == categoryId) {
					HashMap<String, Object> map = new HashMap<>();
					map = list.get(i);
					map.put("children", this.getChildCategory(list, categoryPidSet, Integer.parseInt(list.get(i).get("categoryId").toString())));
					returnList.add(map);
				}
			}
			return returnList;
		} else {
			return returnList;
		}
	}

	public int deleteAllByCategoryIdService(HashMap<String, Object> map) throws McpException {
		int result = categoryMapper.deleteAllByCategoryIdDao(map);
		return result;
	}

	//按照类目Id删除类目及其下属类目、类目属性以及类目和属性与指标的关联关系。
	//删除的顺序不可改变！！
	public AppReply deleteCategoryService(HashMap<String, Object> map) throws McpException {
		//首先按照categoryId删除index_to_attrs_dics表中指标、类目和属性的关联关系
		indexToAttrsDicsService.deleteIndexToAttrsDicsByCategoryIdService(map);

		//然后按照categoryId删除index表中指标与类目的关联关系
		indexService.deleteRelationBetweenIndexAndGategoryService(map);

		//然后按照categoryId删除category_attributes_dic表中字典项
		categoryAttributesDicService.deleteCategoryAttributesDicByCategoryIdService(map);

		//然后按照categoryId删除category_attributes属性
		categoryAttributesService.deleteCategoryAttributesByCategoryIdService(map);

		//最后按照categoryId删除category中类目及其子类目信息
		int result = this.deleteAllByCategoryIdService(map);

		if(result > 0 ){
			return AppReply.success("删除成功");
		}
		return AppReply.error("删除失败");
	}

	//按照类目Id更新一条类目数据
	public AppReply updateCategoryService(HashMap<String, Object> map) throws McpException {
		String codeAdd = PinyinUtils.getAlpha((String) map.get("categoryName"));
		map.put("codeAdd", codeAdd);
		int result = categoryMapper.updateCategoryDao(map);
		if (result > 0) {
			return AppReply.success("更新类目成功");
		} else {
			return AppReply.success("更新类目失败");
		}
	}

	//要求指标的父类目下不能再有子类目，所以新增指标类目的时候需要判断一下其父类目下是否已经有指标
	//新增一条指标类目信息
	public AppReply addCategoryService(HashMap<String, Object> map) throws McpException {
		String codeAdd = PinyinUtils.getAlpha((String) map.get("categoryName"));
		if (indexService.whetherCategoryHaveIndexService(map) > 0) {
			return AppReply.error("已有指标的父类目下禁止新增类目");
		}
		if (codeAdd.length() == 0)
			codeAdd = "DEFAULT";
		map.put("codeAdd", codeAdd);
		while (this.whetherFullCodeRepeatService(map) > 0) {
			codeAdd = codeAdd + (int) (Math.random() * 10);
			map.put("codeAdd", codeAdd);
		}
		int result = categoryMapper.addCategoryDao(map);
		if (result > 0) {
			return AppReply.success("新增类目成功");
		} else {
			return AppReply.error("新增类目失败");
		}
	}

	//<!--根据类目id和addCode查询新增fullCode是否重复-->
	//<!--返回数量大于0说明重复-->
	//<!--用于类目新增时判断所用-->
	public Integer whetherFullCodeRepeatService(HashMap<String, Object> map) throws McpException {
		return categoryMapper.whetherFullCodeRepeatDao(map);
	}

	public void updatePidWhenCategoryMoveService(HashMap<String, Object> map) throws McpException {
		categoryMapper.updatePidWhenCategoryMoveDao(map);
	}

	public void updateFullcodeWhenCategoryMoveService(HashMap<String, Object> map) throws McpException {
		categoryMapper.updateFullcodeWhenCategoryMoveDao(map);
	}

	public void updateLevelWhenCategoryMoveService(HashMap<String, Object> map) throws McpException {
		categoryMapper.updateLevelWhenCategoryMoveDao(map);
	}

}
