package com.bonc.ioc.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: index
 * @description: 验证字符串是否是中文字符串
 * @author: zcs
 * @create: 2018-12-27 10:12
 **/
@Target({ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsChineseValidator.class)
public @interface IsChinese {
    String message() default "请输入中文";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
