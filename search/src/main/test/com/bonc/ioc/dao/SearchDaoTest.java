package com.bonc.ioc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @Title: bonc_ioc_zwzbzd
 * @Package: com.bonc.ioc.dao
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/9 15:11
 * @Version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchDaoTest {

	@Autowired
	private SearchDao dao;

	@Test
	public void getSearchTotalNumber() {
		HashMap hashMap = new HashMap();
		hashMap.put("keywords","机械");
		int result = dao.getSearchTotalNumber(hashMap);
		System.out.println("result = " + result);
	}
}