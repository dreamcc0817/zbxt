package com.bonc.ioc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeChange {
    public  Date toMysqlType(Object obj) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(obj.toString());
    }
}
