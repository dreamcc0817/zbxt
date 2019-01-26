package com.bonc.ioc.common.utils;


import java.io.UnsupportedEncodingException;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *    Base64加密解密工具类
 *
 * @author lijing
 */
public class Base64Util {
	private static final String charset = "utf-8";
	
	/**
	 * 解密
	 *
	 * @param data
	 * @return
	 * @author lijing
	 */
	public static String decode(String data) {
		try {
			if (null == data) {
				return null;
			}
			
			return new String(Base64.decodeBase64(data.getBytes(charset)), charset);
		} catch (UnsupportedEncodingException e) {
		
		}
		
		return null;
	}
	
	/**
	 * 加密
	 *
	 * @param data
	 * @return
	 * @author lijing
	 */
	public static String encode(String data) {
		try {
			if (null == data) {
				return null;
			}
			return new String(Base64.encodeBase64(data.getBytes(charset)), charset);
		} catch (UnsupportedEncodingException e) {
		
		}
		
		return null;
	}
	
	
}