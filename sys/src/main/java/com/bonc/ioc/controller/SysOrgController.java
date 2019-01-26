package com.bonc.ioc.controller;

import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.model.SysOrg;
import com.bonc.ioc.service.SysOrgService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 系统组织机构控制器
 */
@RestController
@RequestMapping("/org")
@Validated
public class SysOrgController {
	
	@Resource
	private SysOrgService orgService;
	
	@PostMapping("/save")
	@ApiOperation("【系统管理】组织管理——添加组织机构")
	@AppAuthAnnotation
	public AppReply save(@ApiParam(value = "机构名称", required = true) @NotBlank(message = "机构名称参数缺省！") @Length(max=50,message="机构名称字段长度不能超过{max}个字数") String orgNameF,
	                     @ApiParam(value = "机构编码", required = true) @NotBlank(message = "机构编码参数缺省！") @Length(max=50,message="机构编码字段长度不能超过{max}个字数") String orgCode,
	                     @ApiParam(value = "父机构编码", required = false) String orgPcode,
	                     @ApiParam(value = "机构类型", required = false) String orgType,
	                     @ApiParam(value = "排序", required = false) String sort,
	                     @ApiParam(value = "备注", required = false) String remark) throws McpException {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgNameF(orgNameF);
		sysOrg.setOrgCode(orgCode);
		if(orgPcode != null && !"".equals(orgPcode)){
			sysOrg.setOrgPcode(orgPcode);
		}
		if(orgType != null && !"".equals(orgType)){
			sysOrg.setOrgType(orgType);
		}
		if(sort != null && !"".equals(sort)){
			sysOrg.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			sysOrg.setRemark(remark);
		}
		orgService.save(sysOrg);
		return AppReply.success("新增成功！");
	}
	
	@PutMapping("/update")
	@ApiOperation("【系统管理】组织管理——修改组织机构")
	@AppAuthAnnotation
	public AppReply update(@ApiParam(value = "机构名称", required = true) @NotBlank(message = "机构名称参数缺省！") @Length(max=50,message="机构名称字段长度不能超过{max}个字数") String orgNameF,
	                       @ApiParam(value = "机构编码", required = true) @NotBlank(message = "机构编码参数缺省！") @Length(max=50,message="机构编码字段长度不能超过{max}个字数") String orgCode,
	                       @ApiParam(value = "父机构编码", required = false) String orgPcode,
	                       @ApiParam(value = "排序", required = false) String sort,
	                       @ApiParam(value = "备注", required = false) String remark,
	                       @ApiParam(value = "主键", required = true) @NotBlank(message = "主键参数缺省！") String id) throws McpException {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgNameF(orgNameF);
		sysOrg.setOrgCode(orgCode);
		if(orgPcode != null && !"".equals(orgPcode)){
			sysOrg.setOrgPcode(orgPcode);
		}
		if(sort != null && !"".equals(sort)){
			sysOrg.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			sysOrg.setRemark(remark);
		}
		sysOrg.setId(id);
		orgService.update(sysOrg);
		return AppReply.success("修改成功！");
	}
	
	@DeleteMapping("/delete")
	@ApiOperation("【系统管理】组织管理——删除组织机构")
	@AppAuthAnnotation
	public AppReply delete(@ApiParam(value = "主键s", required = true) @NotBlank(message = "ids参数缺省！") String ids) throws McpException {
		String idArr[] = ids.split(",");
		String result = orgService.checkCanDel(idArr);//检出是否能见进行删除操作
		if(result.equals("able")){
			orgService.delete(idArr);
		}
		return AppReply.success("删除成功！");
	}
	
	@GetMapping("/getSysOrgTree")
	@ApiOperation("【系统管理】组织管理——查询组织机构树")
	@AppAuthAnnotation
	public AppReply getSysOrgTree() throws McpException {
		List<ZTreeNode> tree = orgService.getSysOrgTree();
		return AppReply.success(tree);
	}
	
	@GetMapping("/getList")
	@ApiOperation("【系统管理】组织管理——分页查询组织机构列表")
	@AppAuthAnnotation
	public AppReply getList(@ApiParam(value = "页码", required = true) @NotBlank(message = "页码参数缺省！") @Min(1) String pageNumber,
	                        @ApiParam(value = "每页条数", required = true) @NotBlank(message = "每页条数参数缺省！") @Min(1) String pageSize,
	                        @ApiParam(value = "机构编码", required = false) String orgcode,
	                        @ApiParam(value = "机构名称", required = false) String orgname,
	                        @ApiParam(value = "机构类型", required = false) String orgType) throws McpException {
		PageResult pageResult = orgService.findOrgList(orgcode, orgname, orgType, pageNumber, pageSize);
		return AppReply.success(pageResult);
	}
}
