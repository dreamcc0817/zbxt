package com.bonc.ioc.handler;

import com.bonc.ioc.core.base.tips.AppReply;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @creater lyh
 * @creat_time 2018/11/28 17:54 
 * @changer   ycy
 * @change_time 2018/12/24 10:49
 */
@RestControllerAdvice
@Order(-1)
public class MyModelExceptionHandler {
	
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AppReply illegalStateException(BindException e) {
		return AppReply.error(validation(e.getBindingResult()));
	}
	
	private String validation(BindingResult bindingResult) {
		List<ObjectError> errors = bindingResult.getAllErrors();
		StringBuilder sb = new StringBuilder("");
		if (!CollectionUtils.isEmpty(errors)) {
			for (ObjectError error : errors) {
				FieldError fieldError = (FieldError) error;
				sb.append(fieldError.getDefaultMessage() + ",");
			}
		}
		return sb.length()>0?sb.substring(0,sb.length()-1):"请求参数不合法";
	}
	
}
