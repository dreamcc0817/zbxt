package com.bonc.ioc.constraint;

import com.mysql.jdbc.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: index
 * @description: 验证一个参数是否是正整数的规则
 * @author: zcs
 * @create: 2018-12-27 17:31
 **/
public class IsRealPositiveIntegerValidator implements ConstraintValidator<IsRealPositiveInteger,String>{


    @Override
    public void initialize(IsRealPositiveInteger constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.isNullOrEmpty(s)){
            Pattern pattern = Pattern.compile("[1-9]{1}[0-9]*");
            Matcher isNum = pattern.matcher(s);
            if (!isNum.matches()) {
                return false;
            }
        }
        return true;
    }
}
