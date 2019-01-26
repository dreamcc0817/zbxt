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
 * @Date: 2019/1/2 14:40
 * @Version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * 测试参数为空值
	 * @throws Exception
	 */
	@Test
	public void moveCategoryControllerTestNull() throws Exception {
		String url = "/category/moveCategory";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON)
																			 .param("categoryId","")
																			 .param("moveAfterFatherCategoryId",""))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	/**
	 * 测试参数过长
	 * @throws Exception
	 */
	@Test
	public void moveCategoryControllerTestMax() throws Exception {
		String url = "/category/moveCategory";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON)
				.param("categoryId","sssssssssssssssssssssssssssssssssssssssssssss")
				.param("moveAfterFatherCategoryId","ssssssssssssssssssssssssssssssssssssssss"))

//				测试参数正常情况
//				.param("categoryId","ssssssssssssssssssssssssssssss")
//				.param("moveAfterFatherCategoryId","sssssssssssssssssssss"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	/**
	 * 测试参数为空值
	 * @throws Exception
	 */
	@Test
	public void getCategoryControllerTestNull() throws Exception {
		String url = "/category/getCategory";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)
				.param("categoryId",""))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	/**
	 * 测试参数过长
	 * @throws Exception
	 */
	@Test
	public void getCategoryControllerTestMax() throws Exception {
		String url = "/category/getCategory";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)
				.param("categoryId","ssssssssssssssssssssssssssssssssssssssssss"))
				//测试参数为正常情况
//				.param("categoryId","sssssssssssss"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	@Test
	public void getChildrenParallelCategoryController() throws Exception{
		String url = "/getChildrenParallelCategory";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)
				.param("categoryId","1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void deleteCategoryController() throws Exception{
		// 测试url传参为空
		// String url = "/deleteCategory";
		String url = "/deleteCategory/39";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url).accept(MediaType.APPLICATION_JSON)
				//测试参数为空
				//.param("categoryId",""))
				//测试参数过长
				//.param("categoryId","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"))
				//测试参数正常值
				//.param("categoryId","33"))
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void updateCategoryController() throws Exception{
		String url = "/category/updateCategory";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url).accept(MediaType.APPLICATION_JSON)
				//测试参数为空
//				.param("categoryId","")
//				.param("categoryName","")
//				.param("categoryCode","")
//				.param("categoryDesc",""))
				//测试参数过长
				.param("categoryId","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryName","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryCode","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryDesc","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryId","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void addCategoryController() throws Exception{
		String url = "/category/updateCategory";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON)
				//测试参数为空
//				.param("categoryId","")
//				.param("categoryName","")
//				.param("categoryCode","")
//				.param("categoryDesc",""))
				//测试参数正常
//				.param("categoryId","55")
//				.param("categoryName","测试参数名称")
//				.param("categoryCode","测试code")
//				.param("categoryDesc","测试"))
				//测试参数过长
				.param("categoryId","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryName","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryCode","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryDesc","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
				.param("categoryId","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}