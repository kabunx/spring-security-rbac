package com.career.work.validation;

import com.career.work.annotation.CareerPassword;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordSecurityValidator implements ConstraintValidator<CareerPassword, String> {


    @Override
    public void initialize(CareerPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(6, 18),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),   // 包含一个大写字母
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false), // 连续字母重复
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),   // 连续数字重复
                new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(value));
        return result.isValid();
    }
}
