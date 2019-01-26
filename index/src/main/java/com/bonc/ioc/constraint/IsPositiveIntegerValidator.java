package com.bonc.ioc.constraint;

import com.mysql.jdbc.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: index
 * @description: 验证一个参数是否是自然数的规则
 * @author: ${user}
 * @create: 2018-12-24 12:38
 **/
public class IsPositiveIntegerValidator implements ConstraintValidator<IsPositiveInteger,String> {
    @Override
    public void initialize(IsPositiveInteger constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.isNullOrEmpty(s)){
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(s);
            if (!isNum.matches()) {
                return false;
            }
        }
        return true;
    }
}
