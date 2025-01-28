package com.project.wallet_service.util.validation;

import com.project.wallet_service.model.enumeration.OperationName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OperationNameValidator implements ConstraintValidator<ValidOperationName, String> {
    @Override
    public void initialize(ValidOperationName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String operationName, ConstraintValidatorContext constraintValidatorContext) {
        return operationName.equals(OperationName.WITHDRAW.name()) || operationName.equals(OperationName.DEPOSIT.name());
    }
}
