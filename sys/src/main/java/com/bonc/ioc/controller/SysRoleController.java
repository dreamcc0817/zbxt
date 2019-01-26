package com.bonc.ioc.controller;

import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageData;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.model.SysRole;
import com.bonc.ioc.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 系统角色控制器
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {
	@Resource
	private SysRoleService sysRoleService;
	
	@PostMapping("/save")
	@ApiOperation("【系统管理】角色管理——添加角色")
	@AppAuthAnnotation
	public AppReply save(@ApiParam(value = "角色名称", required = true) @NotBlank(message = "角色名称参数缺省！") @Length(max=50,message="角色名称字段长度不能超过{max}个字数") String roleName,
	                     @ApiParam(value = "角色编码", required = true) @NotBlank(message = "角色编码参数缺省！") @Length(max=50,message="角色编码字段长度不能超过{max}个字数") String roleCode,
	                     @ApiParam(value = "角色类型", required = false) String roleType,
	                     @ApiParam(value = "是否锁定", required = true) @NotBlank(message = "是否锁定参数缺省！") String isLocked,
	                     @ApiParam(value = "角色图标", required = false) String roleIcon,
	                     @ApiParam(value = "排序号", required = false) String sort,
	                     @ApiParam(value = "备注", required = false) String remark) throws McpException {
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile("file");
		//if(orginalFile!=null){
		//	String fileName=orginalFile.getOriginalFilename();
		//	String filePath=ConfigUtil.getPropertie("rootPath");
		//	filePath=filePath+"/sysSeting";
		//	sysRole.setRoleIcon(FileUpload.upload(orginalFile, filePath, fileName));
		//}
		SysRole sysRole = new SysRole();
		sysRole.setRoleName(roleName);
		sysRole.setRoleCode(roleCode);
		if(roleType != null && !"".equals(roleType)){
			sysRole.setRoleType(roleType);
		}
		sysRole.setIsLocked(Integer.parseInt(isLocked));
		if(roleIcon != null && !"".equals(roleIcon)){
			sysRole.setRoleIcon(roleIcon);
		}
		if(sort != null && !"".equals(sort)){
			sysRole.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			sysRole.setRemark(remark);
		}
		sysRoleService.save(sysRole);
		return AppReply.success("新增成功！");
	}
	
	@PutMapping("/update")
	@ApiOperation("【系统管理】角色管理——编辑角色")
	@AppAuthAnnotation
	public AppReply update(@ApiParam(value = "角色名称", required = true) @NotBlank(message = "角色名称参数缺省！") @Length(max=50,message="角色名称字段长度不能超过{max}个字数") String roleName,
	                       @ApiParam(value = "角色编码", required = true) @NotBlank(message = "角色编码参数缺省！") @Length(max=50,message="角色编码字段长度不能超过{max}个字数") String roleCode,
	                       @ApiParam(value = "角色类型", required = false) String roleType,
	                       @ApiParam(value = "是否锁定", required = true) @NotBlank(message = "是否锁定参数缺省！") String isLocked,
	                       @ApiParam(value = "角色图标", required = false) String roleIcon,
	                       @ApiParam(value = "排序号", required = false) String sort,
	                       @ApiParam(value = "备注", required = false) String remark,
	                       @ApiParam(value = "主键", required = true) @NotBlank(message = "主键参数缺省！") String id) throws McpException {
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile("file");
		//if(orginalFile!=null){
		//	String fileName=orginalFile.getOriginalFilename();
		//	String filePath=ConfigUtil.getPropertie("rootPath");
		//	filePath=filePath+"/sysSeting";
		//	sysRole.setRoleIcon(FileUpload.upload(orginalFile, filePath, fileName));
		//}
		SysRole sysRole = new SysRole();
		sysRole.setRoleName(roleName);
		sysRole.setRoleCode(roleCode);
		if(roleType != null && !"".equals(roleType)){
			sysRole.setRoleType(roleType);
		}
		sysRole.setIsLocked(Integer.parseInt(isLocked));
		if(roleIcon != null && !"".equals(roleIcon)){
			sysRole.setRoleIcon(roleIcon);
		}
		if(sort != null && !"".equals(sort)){
			sysRole.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			sysRole.setRemark(remark);
		}
		sysRole.setId(id);
		sysRole.setRoleName(sysRole.getRoleName());
		sysRoleService.update(sysRole);
		return AppReply.success("修改成功！");
	}
	
	@DeleteMapping("/delete")
	@ApiOperation("【系统管理】角色管理——删除角色")
	@AppAuthAnnotation
	public AppReply delete(@ApiParam(value = "主键s", required = true) @NotBlank(message = "ids参数缺省！") String ids) throws McpException {
		String idArr[] = ids.split(",");
		String adminRoleId = "F02D136ACCFBC78893FD221EF97F3282";//管理员角色Id
		for(String id:idArr){
			if(adminRoleId.equals(id)){
				return AppReply.error("admin");
			}
		}
		sysRoleService.delete(idArr);
		return AppReply.success("删除成功！");
	}
	
	@GetMapping("/getRoleTree")
	@ApiOperation("【系统管理】角色管理——查询左边角色父类树")
	@AppAuthAnnotation
	public AppReply getRoleTree() throws McpException {
		List<ZTreeNode> tree = sysRoleService.getRoleTree();
		return AppReply.success(tree);
	}
	
	@GetMapping("/getList")
	@ApiOperation("【系统管理】角色管理——分页查询角色列表")
	@AppAuthAnnotation
	public AppReply getList(@ApiParam(value = "角色编码", required = false) String roleCode,
	                          @ApiParam(value = "角色名称", required = false) String roleName,
	                          @ApiParam(value = "角色类型", required = false) String roleType,
	                          @ApiParam(value = "是否锁定", required = false) String isLocked,
	                          @ApiParam(value = "主键", required = false) String id,
	                          @ApiParam(value = "页码", required = true) @NotBlank(message = "页码参数缺省！") @Min(1) String pageNumber,
	                          @ApiParam(value = "每页条数", required = true) @NotBlank(message = "每页条数参数缺省！") @Min(1) String pageSize) throws McpException {
		HashMap<String ,Object> searchMap = new HashMap<String ,Object>();
		searchMap.put("roleCode", roleCode);
		searchMap.put("roleName", roleName);
		searchMap.put("roleType", roleType);
		searchMap.put("isLocked", isLocked);
		searchMap.put("id", id);
		searchMap.put("pageNumber", pageNumber);
		searchMap.put("pageSize", pageSize);
		PageResult pageResult = sysRoleService.findRoleList(searchMap);
		return AppReply.success(pageResult);
	}
	
	/**
	 * 查询角色可操作的菜单
	 */
	@GetMapping("/getRoleMenu")
	@ApiOperation("【系统管理】角色管理——授权时弹出框中菜单栏列表")
	@AppAuthAnnotation
	public AppReply getRoleMenu(@ApiParam(value = "角色主键", required = true) @NotBlank(message = "角色主键参数缺省！") String roleId) throws McpException {
		//List<ZTreeNode> allList = sysRoleService.getSysMenuTree(roleId);
		List<ZTreeNode> allList = sysRoleService.getRoleSysMenuTree(roleId);
		return AppReply.success(allList);
	}
	
	@PutMapping("/setRoleMenu")
	@ApiOperation("【系统管理】角色管理——角色授权")
	@AppAuthAnnotation
	public AppReply setRoleMenu(@ApiParam(value = "菜单主键s", required = true) @NotBlank(message = "菜单主键s参数缺省！") String menuIds,
	                          @ApiParam(value = "角色主键", required = true) @NotBlank(message = "角色主键参数缺省！") String roleId) throws McpException {
		sysRoleService.setRoleMenu(menuIds,roleId);
		return AppReply.success("授权成功！");
	}
	
	@GetMapping("/getRoleAccountTree")
	@ApiOperation("【系统管理】角色管理——分配账户时弹出框中账户栏列表")
	@AppAuthAnnotation
	public AppReply getRoleAccountTree(@ApiParam(value = "角色主键", required = true) @NotBlank(message = "角色主键参数缺省！") String roleId) throws McpException {
		List<ZTreeNode> roleAccountTree =sysRoleService.getRoleAccountTree(roleId);
		return AppReply.success(roleAccountTree);
	}
	
	@PutMapping("/saveRoleAccount")
	@ApiOperation("【系统管理】角色管理——保存角色分配账户信息")
	@AppAuthAnnotation
	public AppReply saveRoleAccount(@ApiParam(value = "角色主键", required = true) @NotBlank(message = "角色主键参数缺省！") String roleId,
	                                @ApiParam(value = "用户主键s", required = true) @NotBlank(message = "用户主键s参数缺省！") String accIds) throws McpException {
		sysRoleService.saveRoleAcc(roleId,accIds);
		return AppReply.success("保存成功！");
	}
}
