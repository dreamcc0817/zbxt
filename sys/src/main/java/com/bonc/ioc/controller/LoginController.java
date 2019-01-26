package com.bonc.ioc.controller;

import com.alibaba.fastjson.JSON;
import com.bonc.ioc.core.base.tips.AppReply;
import com.bonc.ioc.core.exception.McpException;
import com.bonc.ioc.model.Account;
import com.bonc.ioc.service.AccountService;
import com.bonc.ioc.util.PasswordHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 系统登录控制器
 */
@RestController
@RequestMapping("/login")
@Validated
public class LoginController {
	@Resource
	RedisTemplate redisTemplate;
	@Resource
	AccountService accountService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse  response;
	
	@PostMapping("/login")
	@ApiOperation("【系统登录】登录")
	public AppReply login(
	                      @ApiParam(value = "用户名", required = true) @NotBlank(message = "用户名参数缺省！") String username,
	                      @ApiParam(value = "密码", required = true) @NotBlank(message = "密码参数缺省！") String password,
	                      @ApiParam(value = "验证码", required = true) @NotBlank(message = "验证码参数缺省！") String code,
						  @ApiParam(value = "用户类型", required = true) @NotBlank(message = "用户类型参数缺省！") String userType) throws McpException {
		//javax.servlet.http.Cookie cookie=new javax.servlet.http.Cookie("JSESSIONID",request.getSession().getId());
		//cookie.setPath("/");
		//response.addCookie(cookie);
		//redisTemplate.opsForValue().set(request.getSession().getId()+"code",code);
		//String secCode = (String) request.getSession().getAttribute("SECURITY_CODE");
		//Cookie
		HashMap<String,Object> param=new HashMap<>();
		String sesionId=request.getHeader("Cookie");
		if(StringUtils.isEmpty(sesionId)){
			sesionId=request.getSession().getId();
		}
		String secCode = (String)redisTemplate.opsForValue().get(sesionId+"code");
//		if (code != null) {
		if (code != null && secCode != null && secCode.equalsIgnoreCase(code)) {
			param.put("loginName",username);
			param.put("userType",userType);
			Account acc = accountService.findAccountByLoginName(param);
			if(acc == null){
				return AppReply.error("用户名错误或者和用户类型不匹配！");
			}
			Account account = new Account();
			account.setAccNum(username);
			account.setAccPwd(password);
			account.setSalt(acc.getSalt());
			if(acc.getAccPwd().equals(new PasswordHelper().getEncryptPassword(account))){
				redisTemplate.opsForValue().set(request.getSession().getId(),acc.getId());//tokenId userId
				redisTemplate.opsForValue().set(acc.getId(),request.getSession().getId());
				redisTemplate.opsForValue().set(acc.getId()+"VO",JSON.toJSONString(acc));
				redisTemplate.expire(request.getSession().getId(),30*60, TimeUnit.SECONDS);
				redisTemplate.expire(acc.getId(),30*60, TimeUnit.SECONDS);
				redisTemplate.expire(acc.getId()+"VO",30*60, TimeUnit.SECONDS);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user", acc);
				map.put("tokenId", request.getSession().getId());
				return AppReply.success(map);
			}else{
				return AppReply.error("密码错误！");
			}
		}
		return AppReply.error("验证码错误！");
	}
}
