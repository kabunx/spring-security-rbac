package com.career.work.annotation;

import com.career.work.validation.PasswordSecurityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE}) // 上下文
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordSecurityValidator.class)
public @interface CareerPassword {

    String message() default "密码长度至少6位；必须含有数字&字母（一个大写）";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
