package com.bonc.ioc.util;

/*
 * @program: index
 * @description: ${description}
 * @author: ${user}
 * @create: 2018-12-19 17:30
 **/
public class DocumentUtil {

    /**
     *  指标和参考依据关联表中参考依据状态
     * ycy
     * 2018-12-19 17:30
     */
    public interface DocumentStatus{
        String STORAGE_TEMP = "1";      //暂存
        String STORAGE_TRUE = "0";      //真存
        String DELETE = "2";            //删除
    }
}
