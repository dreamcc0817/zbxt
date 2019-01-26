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
 * @Date: 2019/1/7 9:53
 * @Version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllTopLevelCategory() throws Exception {
		String url = "/search/getAllTopLevelCategory";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
									 .andExpect(MockMvcResultMatchers.status().isOk())
									 .andDo(MockMvcResultHandlers.print())
									 .andReturn();
	}

}