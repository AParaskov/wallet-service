package com.project.wallet_service.util.validation;

import com.project.wallet_service.model.dto.request.CreateWalletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ValidConfirmPassword, CreateWalletRequest> {
    @Override
    public void initialize(ValidConfirmPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateWalletRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getConfirmPassword() != null && request.getPassword() != null && request.getPassword().equals(request.getConfirmPassword());
    }
}
