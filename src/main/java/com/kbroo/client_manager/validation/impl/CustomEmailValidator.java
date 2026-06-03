package com.kbroo.client_manager.validation.impl;

import com.kbroo.client_manager.validation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<ValidEmail, String> {
    public CustomEmailValidator() {}

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && !email.isBlank() && email.matches("^.+@gmail\\.com$");
    }
}
