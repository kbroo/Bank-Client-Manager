package com.kbroo.client_manager.validation;

import com.kbroo.client_manager.validation.impl.CustomPhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomPhoneValidator.class)
public @interface ValidPhone {
    String message() default "Формат должен быть +375XXXXXXXXX";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
