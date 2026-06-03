package com.kbroo.client_manager.validation.impl;

import com.kbroo.client_manager.validation.ValidPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomPhoneValidator implements ConstraintValidator<ValidPhone, String> {
    public CustomPhoneValidator() {}
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && !phone.isBlank() && phone.matches("\\+375\\d{9}");
    }
}
