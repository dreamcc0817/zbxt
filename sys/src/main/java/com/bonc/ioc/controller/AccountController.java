package com.bonc.ioc.controller;

import com.alibaba.fastjson.JSON;
import com.bonc.ioc.core.aop.AppAuthAnnotation;
import com.bonc.ioc.core.base.model.ZTreeNode;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.core.page.PageResult;
import com.bonc.ioc.model.Account;
import com.bonc.ioc.model.DicArea;
import com.bonc.ioc.model.RelAccOrg;
import com.bonc.ioc.model.SysOrg;
import com.bonc.ioc.service.AccountService;
import com.bonc.ioc.service.AreaService;
import com.bonc.ioc.service.SysOrgService;
import com.bonc.ioc.util.PasswordHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统用户控制器
 */
@RestController
@RequestMapping("/account")
@Validated
public class AccountController {
	@Resource
	private AccountService accountService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private AreaService areaService;
	@Resource
	RedisTemplate redisTemplate;
	
	@PostMapping("/save")
	@ApiOperation("【系统管理】用户管理——添加用户信息")
	@AppAuthAnnotation
	public AppReply save(@ApiParam(value = "用户账号", required = true) @NotBlank(message = "用户账号参数缺省！") @Length(max=50,message="用户账号字段长度不能超过{max}个字数") String accNum,
	                     @ApiParam(value = "用户名", required = true) @NotBlank(message = "用户名参数缺省！") @Length(max=50,message="用户名字段长度不能超过{max}个字数") String accName,
	                     @ApiParam(value = "所属行政区划", required = true) @NotBlank(message = "所属行政区划参数缺省！") String areaCode,
	                     @ApiParam(value = "性别", required = true) @NotBlank(message = "性别参数缺省！") String sex,
	                     @ApiParam(value = "联系电话", required = true) @NotBlank(message = "联系电话参数缺省！") String contactMobile,
	                     @ApiParam(value = "邮箱", required = true) @NotBlank(message = "邮箱参数缺省！") String contactMail,
	                     @ApiParam(value = "是否锁定", required = true) @NotBlank(message = "是否锁定参数缺省！") String isLocked,
	                     @ApiParam(value = "用户类型", required = false) String accType,
	                     @ApiParam(value = "联系地址", required = false) String contactAdd,
	                     @ApiParam(value = "排序", required = false) String sort,
	                     @ApiParam(value = "备注", required = false) String ramark,
	                     @ApiParam(value = "用户头像", required = false) String accPhoto,
	                     @ApiParam(value = "所属机构", required = false) String orgCodes) throws McpException {
		Account account = new Account();
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//CommonsMultipartFile orginalFile1 = (CommonsMultipartFile) multipartRequest
		//		.getFile("file1");
		//if (orginalFile1 != null) {
		//	String fileName = orginalFile1.getOriginalFilename();
		//	String filetype=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		//	fileName= UUID.randomUUID().toString().replace("-", "")+"."+filetype;
		//	String filePath = ConfigUtil.getPropertie("rootPath");
		//	filePath = filePath + "/user";
		//	account.setAccPhoto("user/" + FileUpload.upload(orginalFile1, filePath,
		//			fileName));
		//}
		DicArea dicArea = areaService.getByAeraCode(areaCode);
		account.setAreaId(dicArea.getId());
		//默认密码
		account.setAccNum(accNum);
		account.setAccName(accName);
		account.setSex(sex);
		account.setContactMobile(contactMobile);
		account.setContactMail(contactMail);
		account.setIsLocked(Integer.parseInt(isLocked));
		if(accType != null && !"".equals(accType)){
			account.setAccType(Integer.parseInt(accType));
		}
		if(accPhoto != null && !"".equals(accPhoto)){
			account.setAccPhoto(accPhoto);
		}
		if(contactAdd != null && !"".equals(contactAdd)){
			account.setContactAdd(contactAdd);
		}
		if(sort != null && !"".equals(sort)){
			account.setSort(Integer.parseInt(sort));
		}
		if(ramark != null && !"".equals(ramark)){
			account.setRemark(ramark);
		}
		PasswordHelper pwdhelper = new PasswordHelper();
		account.setAccPwd("123456");
		pwdhelper.encryptPassword(account);
		accountService.save(account);
		//判断是否有组织机构code
		if (orgCodes != null && !"".equals(orgCodes)) {
			List<String> orgCodeList = Arrays.asList(orgCodes.split(","));
			for (int i = 0; i < orgCodeList.size(); i++) {
				SysOrg sysOrg = sysOrgService.findByCode(orgCodeList.get(i));
				RelAccOrg relAccOrg = new RelAccOrg();
				relAccOrg.setAccId(account.getId());
				relAccOrg.setOrgId(sysOrg.getId());
				accountService.saveAccOrg(relAccOrg);
			}
		}
		return AppReply.success("新增成功！");
	}
	
	@PutMapping("/update")
	@ApiOperation("【系统管理】用户管理——修改用户信息")
	@AppAuthAnnotation
	public AppReply update(@ApiParam(value = "用户账号", required = true) @NotBlank(message = "用户账号参数缺省！") @Length(max=50,message="用户账号字段长度不能超过{max}个字数") String accNum,
	                       @ApiParam(value = "用户名", required = true) @NotBlank(message = "用户名参数缺省！") @Length(max=50,message="用户名字段长度不能超过{max}个字数") String accName,
	                       @ApiParam(value = "所属行政区划", required = true) @NotBlank(message = "所属行政区划参数缺省！") String areaCode,
	                       @ApiParam(value = "性别", required = true) @NotBlank(message = "性别参数缺省！") String sex,
	                       @ApiParam(value = "联系电话", required = true) @NotBlank(message = "联系电话参数缺省！") String contactMobile,
	                       @ApiParam(value = "邮箱", required = true) @NotBlank(message = "邮箱参数缺省！") String contactMail,
	                       @ApiParam(value = "是否锁定", required = true) @NotBlank(message = "是否锁定参数缺省！") String isLocked,
	                       @ApiParam(value = "用户类型", required = false) String accType,
	                       @ApiParam(value = "联系地址", required = false) String contactAdd,
	                       @ApiParam(value = "排序", required = false) String sort,
	                       @ApiParam(value = "备注", required = false) String remark,
	                       @ApiParam(value = "用户头像", required = false) String accPhoto,
	                       @ApiParam(value = "所属机构", required = true) @NotBlank(message = "所属机构参数缺省！") String orgCodes,
	                       @ApiParam(value = "主键", required = true) @NotBlank(message = "主键参数缺省！") String id) {
		Account account = new Account();
		//if(request instanceof MultipartHttpServletRequest){
		//	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//	CommonsMultipartFile orginalFile1 = (CommonsMultipartFile) multipartRequest
		//			.getFile("file1");
		//	if (orginalFile1 != null) {
		//		String fileName = orginalFile1.getOriginalFilename();
		//		String filetype=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		//		fileName= UUID.randomUUID().toString().replace("-", "")+"."+filetype;
		//		String filePath = ConfigUtil.getPropertie("rootPath");
		//		filePath = filePath + "/user";
		//		account.setAccPhoto("user/" + FileUpload.upload(orginalFile1, filePath,
		//				fileName));
		//	}
		//}
		account.setAccNum(accNum);
		account.setAccName(accName);
		account.setSex(sex);
		account.setContactMobile(contactMobile);
		account.setContactMail(contactMail);
		account.setIsLocked(Integer.parseInt(isLocked));
		if(accType != null && !"".equals(accType)){
			account.setAccType(Integer.parseInt(accType));
		}
		if(accPhoto != null && !"".equals(accPhoto)){
			account.setAccPhoto(accPhoto);
		}
		if(contactAdd != null && !"".equals(contactAdd)){
			account.setContactAdd(contactAdd);
		}
		if(sort != null && !"".equals(sort)){
			account.setSort(Integer.parseInt(sort));
		}
		if(remark != null && !"".equals(remark)){
			account.setRemark(remark);
		}
		account.setId(id);
		//根据所属区划code查询信息，写入areaId
		DicArea dicArea = areaService.getByAeraCode(areaCode);
		account.setAreaId(dicArea.getId());
		accountService.update(account);
		
		//根据orgCode 查询机构信息
		accountService.addOrgAccRel(orgCodes,id);
		//Session session = SecurityUtils.getSubject().getSession();
		//Account acc = (Account) session.getAttribute(Constants.CURRENT_ACCOUNT);
		//if ( account.getId().equals(acc.getId()) ) {
		//	session.setAttribute(Constants.CURRENT_ACCOUNT,accountService.getById(account.getId()));
		//}
		return AppReply.success("修改成功！");
	}
	
	@DeleteMapping("/delete")
	@ApiOperation("【系统管理】用户管理——删除用户信息")
	@AppAuthAnnotation
	public AppReply delete(@ApiParam(value = "主键s", required = true) @NotBlank(message = "主键s参数缺省！") String ids) {
		String idArr[] = ids.split(",");
		List<Account> list = new ArrayList<Account>();
		for(int i=0;i<idArr.length;i++){
			Account acc = accountService.getFindByIsAdmin(idArr[i]);
			if(acc != null){
				list.add(acc);
			}
		}
		if(list.size()>0){
			return AppReply.error("false");
		}else{
			accountService.delete(idArr);
		}
		return AppReply.success("删除成功！");
	}
	
	@GetMapping("/getList")
	@ApiOperation("【系统管理】用户管理——查询用户列表")
	@AppAuthAnnotation
	public AppReply getAccountList(@ApiParam(value = "用户名", required = false) String accName,
	                               @ApiParam(value = "是否锁定", required = false) String isLocked,
	                               @ApiParam(value = "机构名", required = false) String orgName,
	                               @ApiParam(value = "页码", required = true) @NotBlank(message = "页码参数缺省！") @Min(1) String pageNumber,
	                               @ApiParam(value = "每页条数", required = true) @NotBlank(message = "每页条数参数缺省！") @Min(1) String pageSize) throws McpException {
		PageResult pageResult = accountService.findAccountList(isLocked, accName,
				orgName, pageNumber, pageSize);
		return AppReply.success(pageResult);
	}
	
	@GetMapping("/getAccountRoleTree")
	@ApiOperation("【系统管理】用户管理——授权用户中角色列表生成")
	@AppAuthAnnotation
	public AppReply getAccountRoleTree(@ApiParam(value = "用户主键", required = true) @NotBlank(message = "用户主键参数缺省！") String accountId) throws McpException {
		List<ZTreeNode> accountRoleTree = accountService
				.getAccountRoleTree(accountId);
		return AppReply.success(accountRoleTree);
	}
	
	@PostMapping("/saveAccountRole")
	@ApiOperation("【系统管理】用户管理——用户授权")
	@AppAuthAnnotation
	public AppReply saveAccountRole(@ApiParam(value = "用户主键", required = true) @NotBlank(message = "用户主键参数缺省！") String accountId,
	                                @ApiParam(value = "角色主键s", required = true) @NotBlank(message = "角色主键s参数缺省！") String ids) throws McpException {
		accountService.saveAccRole(accountId, ids);
		return AppReply.success("成功！");
	}
	
	@PutMapping("/addImportPwd")
	@ApiOperation("【系统管理】用户管理——用户密码重置")
	@AppAuthAnnotation
	public AppReply addImportPwd(HttpServletRequest req, @ApiParam(value = "主键s", required = true) @NotBlank(message = "主键s参数缺省！") String ids) throws McpException {
		//Account account = (Account) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_ACCOUNT);
		String accId = (String)redisTemplate.opsForValue().get(req.getSession().getId());
		String user = (String)redisTemplate.opsForValue().get(accId + "VO");
		Account account = JSON.parseObject(user, Account.class);
		if("1".equals(account.getIsAdmin())){//如果是管理员
			accountService.addImportPwd(ids);
			return AppReply.success("success");
		}else
			return AppReply.error("YOU ARE NOT THE ADMIN.");
	}
	
	@PutMapping("/updateLockedState")
	@ApiOperation("【系统管理】用户管理——锁定与取消锁定用户")
	@AppAuthAnnotation
	public AppReply updateLockedState(@ApiParam(value = "是否锁定", required = true) @NotBlank(message = "是否锁定参数缺省！") String isLocked,
	                                  @ApiParam(value = "用户主键", required = true) @NotBlank(message = "用户主键参数缺省！") String userId) throws McpException {
		accountService.updateLockedState(isLocked, userId);
		return AppReply.success("success");
	}
	
	/**
	 * 修改密码
	 */
	@PutMapping(value="/updatePwd")
	@ApiOperation("【系统登录】修改密码")
	@AppAuthAnnotation
	public AppReply updatePwd(@ApiParam(value = "用户主键", required = true) @NotBlank(message = "用户主键参数缺省！") String userId,
	                          @ApiParam(value = "旧密码", required = true) @NotBlank(message = "旧密码参数缺省！") String oldPassword,
	                          @ApiParam(value = "新密码", required = true) @NotBlank(message = "新密码参数缺省！") String newPassword) throws McpException{
		Account user = accountService.getUserById(userId);
		if(user != null){
			Account acc = new Account();
			acc.setAccNum(user.getAccNum());
			acc.setAccPwd(oldPassword);
			acc.setSalt(user.getSalt());
			if(new PasswordHelper().getEncryptPassword(acc).equals(user.getAccPwd())){
				Account account = new Account();
				account.setAccNum(user.getAccNum());
				account.setAccPwd(newPassword);
				new PasswordHelper().encryptPassword(account);
				accountService.updatePwd(account.getAccPwd(), account.getSalt(), userId);
				return AppReply.success("修改成功！");
			}
			return AppReply.error("原密码错误！");
		}
		return AppReply.error("该用户不存在！");
	}
}
