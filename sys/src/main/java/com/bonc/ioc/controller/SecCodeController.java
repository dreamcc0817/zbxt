/*
 * Copyright(C)2016-2020 BONC Software Co. Ltd. All rights reserved.
 * 系统名称：ioc_cms 1.0
 * 作者：李伟 && 手机：15358265560
 * 版本号                                                     日   期                      作     者                  变更说明
 * SysController-V1.0     2016/08/31  李伟                  新建
 */
package com.bonc.ioc.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 类名称：SecCodeController
 * 类描述：
 * 作者单位：
 * 联系方式：
 * @version
 */
@Controller
@RequestMapping("/codeController")
public class SecCodeController {
	@Resource
	RedisTemplate redisTemplate;
	
	@RequestMapping(value="generate")
	public void generate(HttpServletRequest request, HttpServletResponse response){
		try {
			System.setProperty("java.awt.headless", "true");
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			String code = drawImg(output);
			//javax.servlet.http.Cookie cookie=new javax.servlet.http.Cookie("JSESSIONID",request.getSession().getId());
			//cookie.setPath("/");
			//response.addCookie(cookie);
			//request.getSession().setAttribute("SECURITY_CODE", code);
			String sesionId=request.getHeader("Cookie");
			if(StringUtils.isEmpty(sesionId)){
				sesionId=request.getSession().getId();
			}
//			redisTemplate.opsForValue().get(request.getHeader("COOKIE"));
			redisTemplate.opsForValue().set(sesionId+"code",code);
			redisTemplate.expire(request.getSession().getId()+"code",3*60, TimeUnit.SECONDS);
			ServletOutputStream out = response.getOutputStream();
			output.writeTo(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String drawImg(ByteArrayOutputStream output){
		String code = "";
		for(int i=0; i<4; i++){
			code += randomChar();
		}
		int width = 70;
		int height = 25;
		BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);
		Font font = new Font("Times New Roman", Font.PLAIN,20);
		Graphics2D g = bi.createGraphics();
		g.setFont(font);
		Color color = new Color(66,2,82);
		g.setColor(color);
		g.setBackground(new Color(226,226,240));
		g.clearRect(0, 0, width, height);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(code, context);
		double x = (width - bounds.getWidth()) / 2;
		double y = (height - bounds.getHeight()) / 2;
		double ascent = bounds.getY();
		double baseY = y - ascent;
		g.drawString(code, (int)x, (int)baseY);
		g.dispose();
		try {
			ImageIO.write(bi, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
	
	private char randomChar(){
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
}
