package com.bonc.ioc.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: index
 * @description: 定义一个自然数标签
 * @author: ${user}
 * @create: 2018-12-24 12:37
 **/
@Target({ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsPositiveIntegerValidator.class)
public @interface IsPositiveInteger {

    String message() default "请输入正整数";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
