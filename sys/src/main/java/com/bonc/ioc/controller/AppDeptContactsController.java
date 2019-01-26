package com.bonc.ioc.controller;

import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.DeptContacts;
import com.bonc.ioc.service.AppDeptContactsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 移动端组织通讯录控制器
 */
@RestController
@RequestMapping("/appDeptContacts")
@Validated
public class AppDeptContactsController {
	@Resource
	private AppDeptContactsService appDeptContactsService;
	
	@GetMapping("/getDeptList")
	@ApiOperation("【通讯录】通讯录添加部门的部门列表")
	@AppAuthAnnotation(userId = true)
	public AppReply getDeptList(@ApiParam(value = "关键字", required = false) String keyword,
	                            @ApiParam(value = "用户主键", required = true) @NotBlank(message = "用户主键参数缺省！") String userId) throws McpException {
		List<DeptContacts> list = appDeptContactsService.getDeptList(keyword);
		if(list == null || list.size() == 0){
			return AppReply.success("暂无和搜索条件相关的数据！");
		}
		List<String> deptIds = appDeptContactsService.getDeptIdsByAppUserId(userId);
		for(int j = deptIds.size() - 1; j >= 0; j--){
			for(int i = list.size() - 1; i >= 0; i--){
				if(deptIds.get(j).equals(list.get(i).getId())){
					list.remove(i);
				}
				if(list.size() == 0){
					return AppReply.success("没有可以添加的通讯录！");
				}
			}
		}
		return AppReply.success(list);
	}
	
	@PostMapping("/addDeptForUser")
	@ApiOperation("【通讯录】通讯录添加部门")
	@AppAuthAnnotation(userId = true)
	public AppReply addDeptForUser(@ApiParam(value = "用户主键", required = true) @NotBlank(message = "用户主键参数缺省！") String userId,
	                               @ApiParam(value = "部门主键", required = true) @NotBlank(message = "部门主键参数缺省！") String deptId) throws McpException {
		String id = UUID.randomUUID().toString().replace("-", "");
		appDeptContactsService.addDeptForUser(id, userId, deptId);
		return AppReply.success("添加成功！");
	}
	
	@GetMapping("/getDeptContactsTree")
	@ApiOperation("【通讯录】政府通讯录树结构")
	@AppAuthAnnotation(userId = true)
	public AppReply getDeptContactsTree(@ApiParam(value = "用户主键", required = true) @NotBlank(message = "用户主键参数缺省！") String userId) throws McpException {
		//List<DeptContacts> list = appDeptContactsService.getDeptContactsList(userId);
		List<ZTreeNode> tree = appDeptContactsService.getDeptContactsTree(userId);
		return AppReply.success(tree);
	}
}
