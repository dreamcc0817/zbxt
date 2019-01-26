package com.bonc.ioc.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @Title: bonc_ioc_zwzbzd
 * @Package: com.bonc.ioc.config
 * @Description: 在需要忽略某些空字段
 * @Author: dreamcc
 * @Date: 2019/1/2 10:52
 * @Version: V1.0
 */
@Configurable
public class JsonConfig {
	static {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}
}
