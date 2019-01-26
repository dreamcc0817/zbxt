package com.bonc.ioc.util;

/**
 * 属性字典工具类
 * @author:ycy
 * @create: 2018-12-20 11:30
 **/
public class AttrsDicsUtil {

    /**
     *  指标和属性字典关联表中属性字典状态
     * ycy
     * 2018-12-19 17:30
     */
    public interface AttrsDicsStatus{
        String STORAGE_TEMP = "1";      //暂存
        String STORAGE_TRUE = "0";      //真存
        String DELETE = "2";            //删除
    }
}
