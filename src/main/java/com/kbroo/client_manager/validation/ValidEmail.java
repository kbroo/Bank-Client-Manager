package com.kbroo.client_manager.validation;

import com.kbroo.client_manager.validation.impl.CustomEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomEmailValidator.class)
public @interface ValidEmail {
    String message() default "Формат почты: example@gmail.com";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
