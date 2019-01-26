package com.bonc.ioc.controller;

import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.model.DeptContacts;
import com.bonc.ioc.service.DeptContactsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 组织通讯录控制器
 */
@RestController
@RequestMapping("/zmhd")
@Validated
public class DeptContactsController {
	@Resource
	private DeptContactsService deptContactsService;
	
	/**
	 * 组织通讯录(管理者)——添加组织信息
	 * @param orgCode
	 * @param orgName
	 * @param isAbeldComent
	 * @param depId
	 * @return
	 * @throws McpException
	 */
	@PostMapping("/addORG")
	@ApiOperation("【政民互动】组织通讯录(管理者)——添加组织信息")
	@AppAuthAnnotation
	public AppReply addORG(@ApiParam(value = "机构代码", required = true) @NotBlank(message = "机构代码参数缺省！") @Length(max=50,message="机构编码字段长度不能超过{max}个字数") String orgCode,
	                       @ApiParam(value = "机构名称", required = true) @NotBlank(message = "机构名称参数缺省！") @Length(max=50,message="机构名称字段长度不能超过{max}个字数") String orgName,
	                       @ApiParam(value = "是否可留言", required = false) String isAbeldComent,
	                       @ApiParam(value = "留言处理部门", required = false)String depId) throws McpException {
		DeptContacts deptContacts = new DeptContacts();
		deptContacts.setId(UUID.randomUUID().toString().replace("-", ""));
		deptContacts.setDeptName(orgName);
		deptContacts.setDeptCode(orgCode);
		if(isAbeldComent != null && !"".equals(isAbeldComent)){
			deptContacts.setIsAbeldComent(isAbeldComent);
		}
		if(depId != null && !"".equals(depId)){
			deptContacts.setDepId(depId);
		}
		deptContacts.setCreateDate(new Date());
		deptContacts.setUpdateDate(new Date());
		deptContacts.setIsValid("1");
		deptContactsService.addORG(deptContacts);
		return AppReply.success("新增成功！");
	}
	
	/**
	 * 组织通讯录(管理者)——修改组织信息
	 * @param orgCode
	 * @param orgName
	 * @param isAbeldComent
	 * @param depId
	 * @param id
	 * @return
	 * @throws McpException
	 */
	@PutMapping("/editORG")
	@ApiOperation("【政民互动】组织通讯录(管理者)——修改组织信息")
	@AppAuthAnnotation
	public AppReply editORG(@ApiParam(value = "机构代码", required = true) @NotBlank(message = "机构代码参数缺省！") @Length(max=50,message="机构编码字段长度不能超过{max}个字数") String orgCode,
	                        @ApiParam(value = "机构名称", required = true) @NotBlank(message = "机构名称参数缺省！") @Length(max=50,message="机构名称字段长度不能超过{max}个字数") String orgName,
	                        @ApiParam(value = "是否可留言", required = false)String isAbeldComent,
	                        @ApiParam(value = "留言处理部门", required = false)String depId,
	                        @ApiParam(value = "主键", required = true) @NotBlank(message = "主键参数缺省！") String id) throws McpException {
		DeptContacts deptContacts = new DeptContacts();
		deptContacts.setDeptName(orgName);
		deptContacts.setDeptCode(orgCode);
		if(isAbeldComent != null && !"".equals(isAbeldComent)){
			deptContacts.setIsAbeldComent(isAbeldComent);
		}
		
		if(depId != null && !"".equals(depId)){
			deptContacts.setDepId(depId);
		}
		deptContacts.setUpdateDate(new Date());
		deptContacts.setId(id);
		deptContactsService.editORG(deptContacts);
		return AppReply.success("修改成功！");
	}
	
	/**
	 * 组织通讯录(管理者)——删除组织信息
	 * @param id
	 * @return
	 * @throws McpException
	 */
	@DeleteMapping("/delORG")
	@ApiOperation("【政民互动】组织通讯录(管理者)——删除组织信息")
	@AppAuthAnnotation
	public AppReply delORG(@ApiParam(value = "主键", required = true) @NotBlank(message = "主键参数缺省！") String id) throws McpException {
		deptContactsService.delORG(id);
		return AppReply.success("删除成功！");
	}
	
	/**
	 * 组织列表的生成
	 * @param keyword
	 * @return
	 * @throws McpException
	 */
	@GetMapping("/getORG")
	@ApiOperation("【政民互动】组织通讯录(管理者)——组织列表的生成")
	@AppAuthAnnotation
	public AppReply getORG(@ApiParam(value = "关键字", required = false) String keyword) throws McpException {
		List<DeptContacts> list = deptContactsService.getORG(keyword);
		return AppReply.success(list);
	}
	
	/**
	 * 组织通讯录(管理者)——添加部门通讯录
	 * @param deptCode
	 * @param deptName
	 * @param deptPhone
	 * @param isAbeldComent
	 * @param depId
	 * @param parentId
	 * @return
	 * @throws McpException
	 */
	@PostMapping("/addDEPT")
	@ApiOperation("【政民互动】组织通讯录(管理者)——添加部门通讯录")
	@AppAuthAnnotation
	public AppReply addDEPT(@ApiParam(value = "部门代码", required = true) @NotBlank(message = "部门代码参数缺省！") @Length(max=50,message="部门编码字段长度不能超过{max}个字数") String deptCode,
	                        @ApiParam(value = "部门名称", required = true) @NotBlank(message = "部门名称参数缺省！") @Length(max=50,message="部门名称字段长度不能超过{max}个字数") String deptName,
	                        @ApiParam(value = "工作电话", required = true) @NotBlank(message = "工作电话参数缺省！") String deptPhone,
	                        @ApiParam(value = "是否可留言", required = false) String isAbeldComent,
	                        @ApiParam(value = "留言处理部门", required = false)String depId,
	                        @ApiParam(value = "上级组织", required = true) @NotBlank(message = "上级组织参数缺省！") String parentId) throws McpException {
		DeptContacts deptContacts = new DeptContacts();
		deptContacts.setId(UUID.randomUUID().toString().replace("-", ""));
		deptContacts.setDeptName(deptName);
		deptContacts.setDeptCode(deptCode);
		deptContacts.setDeptPhone(deptPhone);
		if(isAbeldComent != null && !"".equals(isAbeldComent)){
			deptContacts.setIsAbeldComent(isAbeldComent);
		}
		if(depId != null && !"".equals(depId)){
			deptContacts.setDepId(depId);
		}
		deptContacts.setParentId(parentId);
		deptContacts.setCreateDate(new Date());
		deptContacts.setUpdateDate(new Date());
		deptContacts.setIsValid("1");
		deptContactsService.addDEPT(deptContacts);
		return AppReply.success("新增成功！");
	}
	
	/**
	 * 组织通讯录(管理者)——编辑部门通讯录
	 * @param deptCode
	 * @param deptName
	 * @param deptPhone
	 * @param isAbeldComent
	 * @param depId
	 * @param id
	 * @return
	 * @throws McpException
	 */
	@PutMapping("/editDEPT")
	@ApiOperation("【政民互动】组织通讯录(管理者)——编辑部门通讯录")
	@AppAuthAnnotation
	public AppReply editDEPT(@ApiParam(value = "部门代码", required = true) @NotBlank(message = "部门代码参数缺省！") @Length(max=50,message="部门编码字段长度不能超过{max}个字数") String deptCode,
	                         @ApiParam(value = "部门名称", required = true) @NotBlank(message = "部门名称参数缺省！") @Length(max=50,message="部门名称字段长度不能超过{max}个字数") String deptName,
	                         @ApiParam(value = "工作电话", required = true) @NotBlank(message = "工作电话参数缺省！") String deptPhone,
	                         @ApiParam(value = "是否可留言", required = false)String isAbeldComent,
	                         @ApiParam(value = "留言处理部门", required = false)String depId,
	                        @ApiParam(value = "主键", required = true) @NotBlank(message = "主键参数缺省！") String id) throws McpException {
		DeptContacts deptContacts = new DeptContacts();
		deptContacts.setDeptName(deptName);
		deptContacts.setDeptCode(deptCode);
		deptContacts.setDeptPhone(deptPhone);
		if(isAbeldComent != null && !"".equals(isAbeldComent)){
			deptContacts.setIsAbeldComent(isAbeldComent);
		}
		
		if(depId != null && !"".equals(depId)){
			deptContacts.setDepId(depId);
		}
		deptContacts.setUpdateDate(new Date());
		deptContacts.setId(id);
		deptContactsService.editDEPT(deptContacts);
		return AppReply.success("修改成功！");
	}
	
	/**
	 * 组组织通讯录(管理者)——批量删除部门通讯录
	 * @param ids
	 * @return
	 * @throws McpException
	 */
	@DeleteMapping("/delDEPT")
	@ApiOperation("【政民互动】组织通讯录(管理者)——批量删除部门通讯录")
	@AppAuthAnnotation
	public AppReply delDEPT(@ApiParam(value = "主键s", required = true) @NotBlank(message = "主键s参数缺省！") String ids) throws McpException {
		deptContactsService.delDEPT(ids);
		return AppReply.success("删除成功！");
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
	@GetMapping("/getDEPT")
	@ApiOperation("【政民互动】组织通讯录(管理者)——分页查询部门通讯录")
	@AppAuthAnnotation
	public AppReply getDEPT(@ApiParam(value = "关键字", required = false)String keyword,
	                        @ApiParam(value = "部门Id", required = false)String deptId,
	                        @ApiParam(value = "页码", required = true) @NotBlank(message = "页码参数缺省！") @Min(1) String pageNumber,
	                        @ApiParam(value = "每页条数", required = true) @NotBlank(message = "每页条数参数缺省！") @Min(1) String pageSize) throws McpException {
		PageResult pageResult = deptContactsService.selectByPageDEPT(keyword, deptId, pageNumber, pageSize);
		return AppReply.success(pageResult);
	}
}
