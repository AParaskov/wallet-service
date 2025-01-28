package com.project.wallet_service.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.project.wallet_service.exception.ErrorCodesAndMessages.INVALID_CONFIRM_PASSWORD_INPUT;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Documented
public @interface ValidConfirmPassword {
    String message() default INVALID_CONFIRM_PASSWORD_INPUT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
