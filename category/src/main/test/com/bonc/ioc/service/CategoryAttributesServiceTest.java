package com.bonc.ioc.service;

import com.bonc.ioc.core.base.tips.AppReply;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @Title: bonc_ioc_zwzbzd
 * @Package: com.bonc.ioc.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/11 16:11
 * @Version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryAttributesServiceTest {

	@Autowired
	private CategoryAttributesService categoryService;

	@Test
	public void getCategoryPropertyInfoService() {
	}

	@Test
	public void getCategoryPropertyInfoReturnService() {
	}

	@Test
	public void addCategoryAttributesService() {
	}

	/**
	 *    @ApiParam(value = "类目编码", required = true) @NotBlank(message = "类目编码参数缺省！") @Length(max=50,message="类目编码字段长度不能超过{max}个字数") String categoryFullcode,
	 *    @ApiParam(value = "中文名称", required = true) @NotBlank(message = "中文名称参数缺省！") @Length(max=50,message="中文名称字段长度不能超过{max}个字数") String attrNameCn,
	 *    @ApiParam(value = "英文名称", required = true) @NotBlank(message = "英文名称参数缺省！") @Length(max=50,message="英文名称字段长度不能超过{max}个字数") String attrNameEn,
	 *    @ApiParam(value = "是否单选", required = true) @NotBlank(message = "是否单选参数缺省！") @Length(max=50,message="是否单选字段长度不能超过{max}个字数")  String attrIssingle,
	 *    @ApiParam(value = "是否必填", required = true) @NotBlank(message = "是否必填参数缺省！") @Length(max=50,message="是否必填字段长度不能超过{max}个字数") String attrIsrequired,
	 *    @ApiParam(value = "字段属性json", required = true) @NotBlank(message = "字段属性json参数缺省！") String categoryDictArray
	 *     ) throws McpException, JSONException {
	 */
	@Test
	public void addCategoryPropertyInfoService() {
		HashMap<String,Object> params = new HashMap<>();
		params.put("categoryDictArray", "[{\"dicName\":\"测试1/8 01\"},{\"dicName\":\"测试1/8 02\"}]");
		params.put("categoryFullcode", "JX");
		params.put("attrNameCn", "机械属性测试1/8");
		params.put("attrNameEn", "机械英文名测试1/8");
		params.put("attrIssingle", 1);
		params.put("attrIsrequired", 1);
		AppReply appReply = categoryService.addCategoryPropertyInfoService(params);
		System.out.println("appReply = " + appReply);
	}

	@Test
	public void updateCategoryPropertyInfoService() {
	}

	@Test
	public void deleteCategoryAttributesByCategoryIdService() {
	}

	@Test
	public void selectByPagegetCategoryPropertyInfosService() {
	}

	@Test
	public void deleteCategoryPropertyInfoService() {
	}

	@Test
	public void deleteCategoryAttributesByAttrIdService() {
	}

	@Test
	public void getAttrIdByFullcodeService() {
	}

	@Test
	public void updateCategoryAttributesService() {
	}

	@Test
	public void updateAttributesWhenCategoryMoveService() {
	}
}