package com.project.wallet_service.model.dto.request;

import com.project.wallet_service.util.validation.ValidConfirmPassword;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.project.wallet_service.exception.ErrorCodesAndMessages.INVALID_EMAIL_INPUT;
import static com.project.wallet_service.exception.ErrorCodesAndMessages.INVALID_FULL_NAME_INPUT;
import static com.project.wallet_service.exception.ErrorCodesAndMessages.INVALID_PASSWORD_INPUT;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ValidConfirmPassword
public class CreateWalletRequest {
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}(?: [A-Z][a-zA-Z]*){1,2}$", message = INVALID_FULL_NAME_INPUT)
    private String fullName;
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = INVALID_EMAIL_INPUT)
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", message = INVALID_PASSWORD_INPUT)
    private String password;
    private String confirmPassword;
}
