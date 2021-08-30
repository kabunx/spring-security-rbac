package com.career.work.annotation;

import com.career.work.validation.ConfirmedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE}) // 上下文
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ConfirmedValidator.class)
public @interface CareerConfirmed {

    String message() default "必须一致";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
