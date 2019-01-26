package com.bonc.ioc.controller;

import com.bonc.ioc.constraint.IsRealPositiveInteger;
import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.service.CategoryAttributesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.json.JSONException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.HashMap;


@RestController
//@RequestMapping("/category")
@Validated
public class CategoryAttributesController {

    @Resource
    private CategoryAttributesService categoryAttributesService;

    @GetMapping(value = "/getCategoryPropertyInfo/{attrId}")
    @ApiOperation(value = "按照attrId查找类目属性及其值域")
    @AppAuthAnnotation
    public AppReply getCategoryPropertyInfoController(
            @ApiParam(value = "类目属性id", required = true) @NotBlank(message = "类目属性id参数缺省！") @Length(max=50,message="类目属性id字段长度不能超过{max}个字数")@PathVariable("attrId") String attrId
    ) throws McpException{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("attrId", attrId);
        return AppReply.success(categoryAttributesService.getCategoryPropertyInfoReturnService(params));
    }

    @GetMapping(value = "/getCategoryPropertyInfos")
    @ApiOperation(value = "分页查询，按照categoryFullcode查找本类目与父类目的属性和值域")
    @AppAuthAnnotation
    public AppReply getCategoryPropertyInfosSelectByPageController(
            @ApiParam(value = "类目全码", required = true) @NotBlank(message = "类目全码参数缺省！") @Length(max=50,message="类目全码字段长度不能超过{max}个字数")  String categoryFullcode,
            @ApiParam(value = "分页，每页数量", required = true)  @NotBlank(message = "每页数量参数缺省！")@Length(max=10,message="页码长度不能超过{max}个字数") @Min(1)  @IsRealPositiveInteger String pageSize,
            @ApiParam(value = "分页，当前页数", required = true) @NotBlank(message = "当前页数参数缺省！")@Length(max=10,message="页码长度不能超过{max}个字数") @Min(1) @IsRealPositiveInteger String pageNumber
    ) throws McpException{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("categoryFullcode", categoryFullcode);
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
       return AppReply.success(categoryAttributesService.selectByPagegetCategoryPropertyInfosService(params));
    }

    //需要同时删除属性表中数据、属性与值域关联表关联关系、属性与指标关联表关联关系
    @DeleteMapping(value = "/deleteCategoryPropertyInfo/{attrId}")
    @ApiOperation(value = "按照attrId删除类目的属性信息")
    @AppAuthAnnotation
    public AppReply deleteCategoryPropertyInfoController(
            @ApiParam(value = "类目属性id", required = true) @NotBlank(message = "类目属性id参数缺省！") @PathVariable String attrId
    ) throws McpException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("attrId", attrId);
        AppReply appReply = categoryAttributesService.deleteCategoryPropertyInfoService(params);
        return appReply;
    }

    //新增类目属性信息
    @PostMapping(value = "/addCategoryPropertyInfo")
    @ApiOperation(value = "新增指标类目属性")
    @AppAuthAnnotation
    public AppReply addCategoryPropertyInfoController(
            @ApiParam(value = "类目编码", required = true) @NotBlank(message = "类目编码参数缺省！") @Length(max=50,message="类目编码字段长度不能超过{max}个字数") String categoryFullcode,
            @ApiParam(value = "中文名称", required = true) @NotBlank(message = "中文名称参数缺省！") @Length(max=50,message="中文名称字段长度不能超过{max}个字数") String attrNameCn,
            @ApiParam(value = "英文名称", required = true) @NotBlank(message = "英文名称参数缺省！") @Length(max=50,message="英文名称字段长度不能超过{max}个字数") String attrNameEn,
            @ApiParam(value = "是否单选", required = true) @NotBlank(message = "是否单选参数缺省！") @Length(max=50,message="是否单选字段长度不能超过{max}个字数")  String attrIssingle,
            @ApiParam(value = "是否必填", required = true) @NotBlank(message = "是否必填参数缺省！") @Length(max=50,message="是否必填字段长度不能超过{max}个字数") String attrIsrequired,
            @ApiParam(value = "字段属性json", required = true) @NotBlank(message = "字段属性json参数缺省！") String categoryDictArray
    ) throws McpException, JSONException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("categoryDictArray", categoryDictArray);
        params.put("categoryFullcode", categoryFullcode);
        params.put("attrNameCn", attrNameCn);
        params.put("attrNameEn", attrNameEn);
        params.put("attrIssingle", attrIssingle);
        params.put("attrIsrequired", attrIsrequired);
        AppReply appReply = categoryAttributesService.addCategoryPropertyInfoService(params);
        return appReply;
    }


    @PutMapping("/updateCategoryPropertyInfo")
    @ApiOperation(value = "更新类目属性信息")
    @AppAuthAnnotation
    public AppReply updateCategoryPropertyInfoController(
            @ApiParam(value = "类目属性id", required = true) @NotBlank(message = "类目属性id参数缺省！")  String attrId,
            @ApiParam(value = "中文名称", required = false)  String attrNameCn,
            @ApiParam(value = "英文名称", required = false)  String attrNameEn,
            @ApiParam(value = "是否单选", required = false)  String attrIssingle,
            @ApiParam(value = "是否必填", required = false)  String attrIsrequired,
            @ApiParam(value = "字段属性json", required = true)  @NotBlank(message = "字段属性json参数缺省！")   String categoryDictArray
    ) throws McpException, JSONException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("categoryDictArray", categoryDictArray);
        params.put("attrId", attrId);
        params.put("attrNameCn", attrNameCn);
        params.put("attrNameEn", attrNameEn);
        params.put("attrIssingle", attrIssingle);
        params.put("attrIsrequired", attrIsrequired);
        AppReply appReply = categoryAttributesService.updateCategoryPropertyInfoService(params);
        return appReply;
    }
}
