package com.bonc.ioc.controller;

import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.model.SysMenu;
import com.bonc.ioc.service.SysMenuService;
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
 * 系统菜单控制器
 */
@RestController
@RequestMapping("/menu")
@Validated
public class SysMenuController {
	@Resource
	private SysMenuService sysMenuService;
	
	@PostMapping("/save")
	@ApiOperation("【系统管理】菜单管理——添加菜单信息")
	@AppAuthAnnotation
	public AppReply save(@ApiParam(value = "菜单名称", required = true) @NotBlank(message = "菜单名称参数缺省！") @Length(max=50,message="菜单名称字段长度不能超过{max}个字数") String menuName,
	                     @ApiParam(value = "菜单编码", required = true) @NotBlank(message = "菜单编码参数缺省！") @Length(max=50,message="菜单编码字段长度不能超过{max}个字数") String menuCode,
	                     @ApiParam(value = "父菜单编码", required = false) String menuPcode,
	                     @ApiParam(value = "菜单类型", required = false) String menuType,
	                     @ApiParam(value = "菜单url", required = false) String menuUrl,
	                     @ApiParam(value = "菜单图标", required = false) String menuIcon,
	                     @ApiParam(value = "排序", required = false) String sort,
	                     @ApiParam(value = "备注", required = false) String remark,
	                     @ApiParam(value = "系统主键", required = true) @NotBlank(message = "系统主键参数缺省！") String sysId) throws McpException {
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile("file");
		//if(orginalFile!=null){
		//	String fileName=orginalFile.getOriginalFilename();
		//	String filePath=ConfigUtil.getPropertie("rootPath");
		//	filePath=filePath+"/sysSeting";
		//	sysMenu.setMenuIcon(FileUpload.upload(orginalFile, filePath, fileName));
		//}
		SysMenu sysMenu = new SysMenu();
		sysMenu.setMenuName(menuName);
		sysMenu.setMenuCode(menuCode);
		if(menuPcode != null && !"".equals(menuPcode)){
			sysMenu.setMenuPcode(menuPcode);
		}
		if(menuType != null && !"".equals(menuType)){
			sysMenu.setMenuType(menuType);
		}
		if(menuUrl != null && !"".equals(menuUrl)){
			sysMenu.setMenuUrl(menuUrl);
		}
		if(menuIcon != null && !"".equals(menuIcon)){
			sysMenu.setMenuIcon(menuIcon);
		}
		if(sort != null && !"".equals(sort)){
			sysMenu.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			sysMenu.setRemark(remark);
		}
		sysMenuService.save(sysMenu,sysId);
		return AppReply.success("新增成功！");
	}
	
	@PutMapping("/update")
	@ApiOperation("【系统管理】菜单管理——编辑菜单信息")
	@AppAuthAnnotation
	public AppReply update(@ApiParam(value = "菜单名称", required = true) @NotBlank(message = "菜单名称参数缺省！") @Length(max=50,message="菜单名称字段长度不能超过{max}个字数") String menuName,
	                       @ApiParam(value = "菜单编码", required = true) @NotBlank(message = "菜单编码参数缺省！") @Length(max=50,message="菜单编码字段长度不能超过{max}个字数") String menuCode,
	                       @ApiParam(value = "父菜单编码", required = false) String menuPcode,
	                       @ApiParam(value = "菜单类型", required = false) String menuType,
	                       @ApiParam(value = "菜单url", required = false) String menuUrl,
	                       @ApiParam(value = "菜单图标", required = false) String menuIcon,
	                       @ApiParam(value = "排序", required = false) String sort,
	                       @ApiParam(value = "备注", required = false) String remark,
	                       @ApiParam(value = "主键", required = true) @NotBlank(message = "主键参数缺省！") String id) throws McpException{
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile("file");
		//if(orginalFile!=null){
		//	String fileName=orginalFile.getOriginalFilename();
		//	String filePath=ConfigUtil.getPropertie("rootPath");
		//	filePath=filePath+"/sysSeting";
		//	sysMenu.setMenuIcon(FileUpload.upload(orginalFile, filePath, fileName));
		//}
		SysMenu sysMenu = new SysMenu();
		sysMenu.setMenuName(menuName);
		sysMenu.setMenuCode(menuCode);
		if(menuPcode != null && !"".equals(menuPcode)){
			sysMenu.setMenuPcode(menuPcode);
		}
		if(menuType != null && !"".equals(menuType)){
			sysMenu.setMenuType(menuType);
		}
		if(menuUrl != null && !"".equals(menuUrl)){
			sysMenu.setMenuUrl(menuUrl);
		}
		if(menuIcon != null && !"".equals(menuIcon)){
			sysMenu.setMenuIcon(menuIcon);
		}
		if(sort != null && !"".equals(sort)){
			sysMenu.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			sysMenu.setRemark(remark);
		}
		sysMenu.setId(id);
		sysMenuService.update(sysMenu);
		return AppReply.success("修改成功！");
	}
	
	@DeleteMapping("/delete")
	@ApiOperation("【系统管理】菜单管理——删除菜单信息")
	@AppAuthAnnotation
	public AppReply delete(@ApiParam(value = "主键s", required = true) @NotBlank(message = "ids参数缺省！") String ids) {
		String idArr[] = ids.split(",");
		String result = sysMenuService.checkCanDel(idArr);//检出是否能进行删除操作
		if(result.equals("able")){
			sysMenuService.delete(idArr);
		}else{
			return AppReply.error("false");
		}
		return AppReply.success("删除成功！");
	}
	
	@GetMapping("/getSysMenuTree")
	@ApiOperation("【系统管理】菜单管理——查询生成父级菜单树")
	@AppAuthAnnotation
	public AppReply getSysMenuTree(@ApiParam(value = "系统主键", required = false) String sysId) {
		List<ZTreeNode> tree = sysMenuService.getSysMenuTree(sysId);
		return AppReply.success(tree);
	}
	
	@GetMapping("/getList")
	@ApiOperation("【系统管理】菜单管理——菜单列表查询")
	@AppAuthAnnotation
	public AppReply getList(@ApiParam(value = "菜单名称", required = false) String menuname,
	                        @ApiParam(value = "菜单编码", required = false) String menuCode,
	                        @ApiParam(value = "页码", required = true) @NotBlank(message = "页码参数缺省！") @Min(1) String pageNumber,
	                        @ApiParam(value = "每页条数", required = true) @NotBlank(message = "每页条数参数缺省！") @Min(1) String pageSize) throws McpException{
		PageResult pageResult = sysMenuService.findSysMenuList(menuCode, menuname, pageNumber, pageSize);
		return AppReply.success(pageResult);
	}
}
