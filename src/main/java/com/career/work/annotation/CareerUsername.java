package com.career.work.annotation;

import com.career.work.validation.UsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE}) // 上下文
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UsernameValidator.class)
public @interface CareerUsername {

    String message() default "用户名必须是汉字、字母、数字的组合";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
