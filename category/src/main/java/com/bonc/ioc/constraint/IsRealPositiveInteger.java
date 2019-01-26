package com.bonc.ioc.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: index
 * @description: 定义一个整数是否是正整数标签
 * @author: zcs
 * @create: 2018-12-27 17:37
 **/
@Target({ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsRealPositiveIntegerValidator.class)
public @interface IsRealPositiveInteger {
    String message() default "请输入正整数";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
