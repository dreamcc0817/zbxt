package com.bonc.ioc.constraint;

import com.mysql.jdbc.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: index
 * @description: 验证字符串全都是中文的规则
 * @author: zcs
 * @create: 2018-12-27 12:27
 **/
public class IsChineseValidator implements ConstraintValidator<IsChinese,String>{
    @Override
    public void initialize(IsChinese constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isNullOrEmpty(s)){
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
        Matcher isChinese = pattern.matcher(s);
        if (!isChinese.matches()) {
            return false;
        }
        return true;
    }
}
