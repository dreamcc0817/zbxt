package com.bonc.ioc.controller;

import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.model.DicElement;
import com.bonc.ioc.model.DicType;
import com.bonc.ioc.service.DicService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 字典管理控制器
 */
@RestController
@Validated
public class DicController {
	@Resource
	private DicService dicService;
	
	@PostMapping("/save")
	@ApiOperation("【系统管理】字典管理——添加字典分类信息")
	@AppAuthAnnotation
	public AppReply save(@ApiParam(value = "类型编号", required = true) @NotBlank(message = "类型编号参数缺省！") @Length(max=50,message="类型编号字段长度不能超过{max}个字数") String typeCode,
	                     @ApiParam(value = "类型名称", required = true) @NotBlank(message = "类型名称参数缺省！") @Length(max=50,message="类型名称字段长度不能超过{max}个字数") String typeName,
	                     @ApiParam(value = "上级节点", required = false) String typePcode,
	                     @ApiParam(value = "排序", required = false) String sort,
	                     @ApiParam(value = "备注", required = false) String remark) throws McpException {
		DicType dicType = new DicType();
		dicType.setId(UUID.randomUUID().toString().replace("-", ""));
		dicType.setTypeCode(typeCode);
		dicType.setTypeName(typeName);
		if(typePcode != null && !"".equals(typePcode)){
			//dicType.setTypeLevel(dicType.getTypeLevel()+1);
			dicType.setTypePcode(typePcode);
		}
		if(sort != null && !"".equals(sort)){
			dicType.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			dicType.setRemark(remark);
		}
		dicService.saveDicType(dicType);
		return AppReply.success("新增成功！");
	}
	
	@PutMapping("/update")
	@ApiOperation("【系统管理】字典管理——编辑字典分类信息")
	@AppAuthAnnotation
	public AppReply update(@ApiParam(value = "类型编号", required = true) @NotBlank(message = "类型编号参数缺省！") @Length(max=50,message="类型编号字段长度不能超过{max}个字数") String typeCode,
	                       @ApiParam(value = "类型名称", required = true) @NotBlank(message = "类型名称参数缺省！") @Length(max=50,message="类型名称字段长度不能超过{max}个字数") String typeName,
	                       @ApiParam(value = "上级节点", required = false) String typePcode,
	                       @ApiParam(value = "排序", required = false) String sort,
	                       @ApiParam(value = "备注", required = false) String remark,
	                       @ApiParam(value = "主键", required = true) @NotBlank(message = "主键参数缺省！") String id) throws McpException {
		DicType dicType = new DicType();
		dicType.setTypeCode(typeCode);
		dicType.setTypeName(typeName);
		if(typePcode != null && !"".equals(typePcode)){
			//dicType.setTypeLevel(dicType.getTypeLevel()+1);
			dicType.setTypePcode(typePcode);
		}
		if(sort != null && !"".equals(sort)){
			dicType.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			dicType.setRemark(remark);
		}
		dicType.setId(id);
		dicService.updateDicType(dicType);
		return AppReply.success("修改成功！");
	}
	
	@DeleteMapping("/deleteDicType")
	@ApiOperation("【系统管理】字典管理——删除字典分类信息")
	@AppAuthAnnotation
	public AppReply deleteDicType(@ApiParam(value = "主键s", required = true) @NotBlank(message = "ids参数缺省！") String ids) throws McpException {
		String idArr[] = ids.split(",");
		
		String result = dicService.checkCanDel(idArr);//检出是否能见进行删除操作
		if(result.equals("able")){
			dicService.deleteDicType(idArr);
		}else{
			return AppReply.error("false");
		}
		return AppReply.success("删除成功！");
	}
	
	@GetMapping("/getDicTypeTree")
	@ApiOperation("【系统管理】字典管理——生成左侧父级字典树")
	@AppAuthAnnotation
	public AppReply getDicTypeTree() throws McpException {
		List<ZTreeNode> tree = dicService.getDicTypeTree();
		return AppReply.success(tree);
	}
	
	@GetMapping("/getDicTypeList")
	@ApiOperation("【系统管理】字典管理——查询字典分类列表")
	@AppAuthAnnotation
	public AppReply getDicTypeList(@ApiParam(value = "类型名称", required = false) String typename,
	                               @ApiParam(value = "类型编号", required = false) String typecode,
	                               @ApiParam(value = "页码", required = true) @NotBlank(message = "页码参数缺省！") @Min(1) String pageNumber,
	                               @ApiParam(value = "每页条数", required = true) @NotBlank(message = "每页条数参数缺省！") @Min(1) String pageSize) throws McpException {
		PageResult pageResult = dicService.getDicTypeList(typename, typecode, pageNumber, pageSize);
		return AppReply.success(pageResult);
		
	}
	
	@PostMapping("/elesave")
	@ApiOperation("【系统管理】字典管理——添加字典元素信息")
	@AppAuthAnnotation
	public AppReply elesave(@ApiParam(value = "元素编号", required = true) @NotBlank(message = "元素编号参数缺省！") @Length(max=50,message="元素编号字段长度不能超过{max}个字数") String elemCode,
	                        @ApiParam(value = "元素名称", required = true) @NotBlank(message = "元素名称参数缺省！") @Length(max=50,message="元素名称字段长度不能超过{max}个字数") String elemName,
	                        @ApiParam(value = "类型名称", required = true) @NotBlank(message = "类型名称参数缺省！") String typeCode,
	                        @ApiParam(value = "排序", required = false) String sort,
	                        @ApiParam(value = "备注", required = false) String remark) throws McpException {
		DicElement dicElement = new DicElement();
		dicElement.setId(UUID.randomUUID().toString().replace("-", ""));
		dicElement.setElemCode(elemCode);
		dicElement.setElemName(elemName);
		dicElement.setTypeCode(typeCode);
		if(sort != null && !"".equals(sort)){
			dicElement.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			dicElement.setRemark(remark);
		}
		dicService.saveDicElement(dicElement);
		return AppReply.success("新增成功！");
	}
	
	@PutMapping("/eleUpdate")
	@ApiOperation("【系统管理】字典管理——编辑字典元素信息")
	@AppAuthAnnotation
	public AppReply eleUpdate(@ApiParam(value = "元素编号", required = true) @NotBlank(message = "元素编号参数缺省！") @Length(max=50,message="元素编号字段长度不能超过{max}个字数") String elemCode,
	                          @ApiParam(value = "元素名称", required = true) @NotBlank(message = "元素名称参数缺省！") @Length(max=50,message="元素名称字段长度不能超过{max}个字数") String elemName,
	                          @ApiParam(value = "排序", required = false) String sort,
	                          @ApiParam(value = "备注", required = false) String remark,
	                          @ApiParam(value = "主键", required = true) @NotBlank(message = "id参数缺省！") String id) throws McpException {
		DicElement dicElement = new DicElement();
		dicElement.setElemCode(elemCode);
		dicElement.setElemName(elemName);
		if(sort != null && !"".equals(sort)){
			dicElement.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			dicElement.setRemark(remark);
		}
		dicElement.setId(id);
		dicService.updateDicElement(dicElement);
		return AppReply.success("修改成功！");
	}
	
	@DeleteMapping("/deleteDicElement")
	@ApiOperation("【系统管理】字典管理——删除字典元素信息")
	@AppAuthAnnotation
	public AppReply deleteDicElement(@ApiParam(value = "主键s", required = true) @NotBlank(message = "ids参数缺省！") String ids) throws McpException {
		String idArr[] = ids.split(",");
		dicService.deleteDicElement(idArr);
		return AppReply.success("删除成功！");
	}
	
	@GetMapping("/getElemList")
	@ApiOperation("【系统管理】字典管理——查询字典元素列表")
	@AppAuthAnnotation
	public AppReply getElemList(@ApiParam(value = "元素名称", required = false) String elemname,
	                            @ApiParam(value = "类型编码", required = false) String typecode,
	                            @ApiParam(value = "页码", required = true) @NotBlank(message = "页码参数缺省！") @Min(1) String pageNumber,
	                            @ApiParam(value = "每页条数", required = true) @NotBlank(message = "每页条数参数缺省！") @Min(1) String pageSize) throws McpException {
		PageResult pageResult = dicService.getElemList(elemname, typecode, pageNumber, pageSize);
		return AppReply.success(pageResult);
	}
	
	/**
	 * <p>作者：zcs</p>
	 * <p>时间：2018/12/12</p>
	 * @param typeCode
	 * @return
	 * @throws McpException
	 */
	@GetMapping("/getDictByType")
	@ApiOperation("【字典接口】根据字典分类查询字典接口")
	public AppReply getDictByType(
			@ApiParam(value = "字典分类",required = true)
			@NotBlank(message = "typeCode不能为空") String typeCode)
			throws McpException{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("typeCode",typeCode);
		List<Map<String,Object>> resultList=dicService.getDicListByType(params);
		return AppReply.success(resultList);
	}
}
