package com.bonc.ioc.controller;

import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Validated
@RestController
//@RequestMapping("/category")
public class CategoryController {

	@Resource
	private CategoryService categoryService;

	@PutMapping("/moveCategory")
	@ApiOperation(value = "类目拖拽功能，更新相关类目和指标字段信息")
	@AppAuthAnnotation
	public AppReply moveCategoryController(
			@ApiParam(value = "拖拽移动的类目id", required = true) @NotBlank(message = "拖拽移动的类目id参数缺省！") @Length(max = 10, message = "拖拽移动的类目id字段长度不能超过{max}个字数") String categoryId,
			@ApiParam(value = "拖拽后父类目id", required = true) @NotBlank(message = "拖拽后父类目id参数缺省！") @Length(max = 10, message = "拖拽后父类目id字段长度不能超过{max}个字数") String moveAfterFatherCategoryId
	) throws McpException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("categoryId", categoryId);
		params.put("moveAfterFatherCategoryId", moveAfterFatherCategoryId);
		return categoryService.moveCategoryService(params);
	}

	@GetMapping(value = "/getCategory/{categoryId}")
	@ApiOperation(value = "查询类目基本信息")
	@AppAuthAnnotation
	public AppReply getCategoryController(
			@ApiParam(value = "类目id", required = true) @NotBlank(message = "类目id参数缺省！") @Length(max = 10, message = "类目id字段长度不能超过{max}个字数") @PathVariable("categoryId") String categoryId
	) throws McpException {
		HashMap<String, Object> params = new HashMap();
		params.put("categoryId", categoryId);
		return AppReply.success(categoryService.getCategoryService(params));
	}

	@GetMapping(value = "/getChildrenParallelCategory")
	@ApiOperation(value = "一次递归查询所有类目信息进行返回")
	@AppAuthAnnotation
	public AppReply getChildrenParallelCategoryController() throws McpException {
		return AppReply.success(categoryService.getChildrenParallelCategoryService());
	}

	//需要同时删除其字类目，子类目下的属性，及其子类目下的关联表关系。
	@DeleteMapping("/deleteCategory/{categoryId}")
	@ApiOperation(value = "按照类目Id删除类目信息")
	@AppAuthAnnotation
	public AppReply deleteCategoryController(
			@ApiParam(value = "指标类目id", required = true) @NotBlank(message = "指标类目id参数缺省！") @Length(max = 50, message = "指标类目id字段长度不能超过{max}个字数")@PathVariable String categoryId
	) throws McpException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("categoryId", categoryId);
		AppReply appReply = categoryService.deleteCategoryService(params);
		return appReply;
	}

	@PutMapping("/updateCategory")
	@ApiOperation(value = "按照类目Id更新类目信息")
	@AppAuthAnnotation
	public AppReply updateCategoryController(
			@ApiParam(value = "指标类目id", required = true) @NotBlank(message = "指标类目id参数缺省！") @Length(max = 50, message = "指标类目id字段长度不能超过{max}个字数") String categoryId,
			@ApiParam(value = "指标类目名称", required = true) @NotBlank(message = "指标类目名称参数缺省！") @Length(max = 50, message = "指标类目名称字段长度不能超过{max}个字数") String categoryName,
			@ApiParam(value = "指标类目编码", required = true) @NotBlank(message = "指标类目编码参数缺省！") @Length(max = 50, message = "指标类目编码字段长度不能超过{max}个字数") String categoryCode,
			@ApiParam(value = "指标类目描述", required = true) @NotBlank(message = "指标类目描述参数缺省！") String categoryDesc
	) throws McpException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("categoryId", categoryId);
		params.put("categoryName", categoryName);
		params.put("categoryCode", categoryCode);
		params.put("categoryDesc", categoryDesc);
		AppReply appReply = categoryService.updateCategoryService(params);
		return appReply;
	}

	@PostMapping("/addCategory")
	@ApiOperation(value = "新增指标类目")
	@AppAuthAnnotation
	public AppReply addCategoryController(
			@ApiParam(value = "父类目id", required = false) @Length(max = 50, message = "父类目id名称字段长度不能超过{max}个字数") String categoryPid,
			@ApiParam(value = "指标类目描述", required = false) String categoryDesc,
			@ApiParam(value = "指标类目名称", required = true) @NotBlank(message = "指标类目名称参数缺省！") @Length(max = 50, message = "指标类目名称字段长度不能超过{max}个字数") String categoryName,
			@ApiParam(value = "指标类目编码", required = true) @NotBlank(message = "指标类目编码参数缺省！") @Length(max = 50, message = "指标类目编码字段长度不能超过{max}个字数") String categoryCode
	) throws McpException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("categoryName", categoryName);
		params.put("categoryCode", categoryCode);
		params.put("categoryDesc", categoryDesc);
		params.put("categoryPid", categoryPid);
		AppReply appReply = categoryService.addCategoryService(params);
		return appReply;
	}
}
