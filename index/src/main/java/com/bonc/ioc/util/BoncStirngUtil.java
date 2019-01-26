package com.bonc.ioc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: String 工具类
 * @author: ycy
 * @create: 2018-12-21 11:28
 */
public class BoncStirngUtil {

    /**
     * @program: 判断一个数字是否是数字，包括整数、小数、正负
     * @author: ycy
     * @create: 2018-12-21 11:28
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    /**
     * @program: 判断一个数字是否是整数
     * @author: ycy
     * @create: 2018-12-21 11:28
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }

        return true;
    }
    /**
     * @program: 判断一个数字是否是正整数
     * @author: ycy
     * @create: 2018-12-21 11:28
     */
    public static boolean isPositiveInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    public static void main(String[] args){

        System.out.println("print:"+BoncStirngUtil.isInteger("-29"));
    }
}
