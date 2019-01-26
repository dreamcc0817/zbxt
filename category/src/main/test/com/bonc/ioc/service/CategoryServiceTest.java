package com.bonc.ioc.service;

import com.bonc.ioc.core.base.tips.AppReply;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

/**
 * @Title: bonc_ioc_zwzbzd
 * @Package: com.bonc.ioc.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/10 9:47
 * @Version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void moveCategoryService() {
	}

	@Test
	public void getCategoryService() {
		HashMap<String,Object> param = new HashMap<>();
		//测试参数正常
		param.put("categoryId",-1);
		//测试category数据库为空
		//param.put("categoryId",111);
		AppReply<HashMap<String, Object>> getCategory = categoryService.getCategoryService(param);
//		Set<Map.Entry<String,Object>> set = getCategory.entrySet();
//		Iterator iterator = set.iterator();
//		while (iterator.hasNext()){
//			Map.Entry entry = (Map.Entry) iterator.next();
//			System.out.println("Key ： " + entry.getKey() + " Value : " + entry.getValue() );
//		}
		System.out.println("getCategory = " + getCategory);
	}

	@Test
	public void getChildrenParallelCategoryService() {
		AppReply<List<HashMap<String, Object>>> appReply= categoryService.getChildrenParallelCategoryService();
		System.out.println("appReply = " + appReply);
	}

	@Test
	public void getChildCategory() {
	}

	@Test
	public void deleteAllByCategoryIdService() {
	}

	@Test
	public void deleteCategoryService() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("categoryId", 33);
		AppReply appReply = categoryService.deleteCategoryService(params);
		System.out.println("appReply = " + appReply);
	}

	@Test
	public void updateCategoryService() {
	}

	@Test
	public void addCategoryService() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		//params.put("categoryName", "测试名字1/8");
		params.put("categoryName", "机器测试");
		params.put("categoryCode", "测试Code");
		params.put("categoryDesc", "测试描述1/8");
		//已有指标的父类目下禁止新增类目
		//params.put("categoryPid", 1);
		//params.put("categoryPid", -1);
		AppReply appReply = categoryService.addCategoryService(params);
		System.out.println("appReply = " + appReply);
	}

	@Test
	public void whetherFullCodeRepeatService() {
		HashMap<String,Object> map = new HashMap<>();
		map.put("codeAdd", "JX");
		int integer = categoryService.whetherFullCodeRepeatService(map);
		System.out.println("integer = " + integer);
		Assert.assertNotEquals(1,integer);
	}

	@Test
	public void updatePidWhenCategoryMoveService() {
	}

	@Test
	public void updateFullcodeWhenCategoryMoveService() {
	}

	@Test
	public void updateLevelWhenCategoryMoveService() {
	}
}