package com.bonc.ioc.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Title: bonc_ioc_zwzbzd
 * @Package: com.bonc.ioc.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/3 10:43
 * @Version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryAttributesControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getCategoryPropertyInfoController() throws Exception {
		//测试URL为空
		//String url = "/getCategoryPropertyInfo/1";
		//测试参数过长
		//String url = "/getCategoryPropertyInfo/1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		//测试参数为小数
		String url = "/getCategoryPropertyInfo/1.1";
		//String url = "/getCategoryPropertyInfo/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void getCategoryPropertyInfosSelectByPageController() throws Exception {
		String url = "/getCategoryPropertyInfos";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)
				.param("categoryFullcode", "aaa")
				//.param("pageSize","10")
				//测试传入参数为小数
				//.param("pageSize","1.1")
				//测试传入参数为空
				//.param("pageSize","")
				//.param("pageNumber","10")
				//测试传入参数为小数
				//.param("pageNumber","1.1")
				//测试传入参数为空
				//.param("pageNumber","")
				//测试页码为1111111111111111111111
				.param("pageSize", "11111111111111111")
				.param("pageNumber", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void deleteCategoryPropertyInfoController() throws Exception {
		//测试URL为空
		//String url = "/deleteCategoryPropertyInfo/";
		//测试参数过长
		//String url = "/deleteCategoryPropertyInfo/1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		//测试参数为小数
		String url = "/deleteCategoryPropertyInfo/1.1";
		//String url = "/deleteCategoryPropertyInfo/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void addCategoryPropertyInfoController() throws Exception {
		String url = "/addCategoryPropertyInfo";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON)
				//测试参数为空
//				.param("categoryDictArray","")
//				.param("categoryFullcode","")
//				.param("attrNameCn","")
//				.param("attrNameEn","")
//              .param("attrIssingle","")
//              .param("attrIsrequired",""))
				//测试参数正常
//				.param("categoryDictArray","")
//				.param("categoryFullcode","")
//				.param("attrNameCn","")
//				.param("attrNameEn","")
//              .param("attrIssingle","")
//              .param("attrIsrequired",""))
				//测试参数过长
				.param("categoryDictArray", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryFullcode", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("attrNameCn", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("attrNameEn", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("attrIssingle", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("attrIsrequired", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void updateCategoryPropertyInfoController() throws Exception{
		String url = "/updateCategoryPropertyInfo";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON)
				//测试参数为空
//				.param("categoryDictArray","")
//				.param("categoryFullcode","")
//				.param("attrNameCn","")
//				.param("attrNameEn","")
//              .param("attrIssingle","")
//              .param("attrIsrequired",""))
				//测试参数正常
//				.param("categoryDictArray","")
//				.param("categoryFullcode","")
//				.param("attrNameCn","")
//				.param("attrNameEn","")
//              .param("attrIssingle","")
//              .param("attrIsrequired",""))
				//测试参数过长
				.param("categoryDictArray", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryFullcode", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("attrNameCn", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("attrNameEn", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("attrIssingle", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("attrIsrequired", "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}