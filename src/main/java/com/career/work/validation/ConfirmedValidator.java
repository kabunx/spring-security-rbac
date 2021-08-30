package com.career.work.validation;

import com.career.work.annotation.CareerConfirmed;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmedValidator implements ConstraintValidator<CareerConfirmed, String> {

    @Override
    public void initialize(CareerConfirmed constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
