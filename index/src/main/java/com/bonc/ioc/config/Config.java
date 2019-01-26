package com.bonc.ioc.config;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: index
 * @description: ${description}
 * @author: ${user}
 * @create: 2018-12-21 16:34
 **/
@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public TestRestTemplate testRestTemplate(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        return testRestTemplate;
    }
}
