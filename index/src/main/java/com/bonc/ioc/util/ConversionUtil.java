package com.bonc.ioc.util;

import com.mysql.jdbc.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 转化工具类
 * @author: ${user}
 * @create: 2018-12-20 15:48
 **/
public class ConversionUtil {
    /**
     * 将以“,”分割的字符串转化为List集合
     * <p> 作 者：ycy
     *<p> 创建时间：2018/12/20
     * @return
     * @throws Exception
     */
    public static List<String> ConversionStringToList(String value){
        List<String> result = new ArrayList<>();
        if(StringUtils.isNullOrEmpty(value)){
            return result;
        }
        String[] array = value.split(",");
        if(array == null || array.length == 0){
            return result;
        }
        for (String getValue:array) {
            result.add(getValue);
        }
        return result;
    }
}
