package com.project.wallet_service.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.project.wallet_service.exception.ErrorCodesAndMessages.INVALID_OPERATION_NAME_INPUT;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = OperationNameValidator.class)
@Documented
public @interface ValidOperationName {
    String message() default INVALID_OPERATION_NAME_INPUT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
