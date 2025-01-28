package com.project.wallet_service.model.dto.request;

import com.project.wallet_service.model.enumeration.OperationName;
import com.project.wallet_service.util.validation.ValidOperationName;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static com.project.wallet_service.exception.ErrorCodesAndMessages.INVALID_EMAIL_INPUT;
import static com.project.wallet_service.exception.ErrorCodesAndMessages.INVALID_SUM_INPUT;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OperationRequest {
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = INVALID_EMAIL_INPUT)
    private String email;
    @ValidOperationName
    private String name;
    @DecimalMin(value = "20")
    @Digits(integer = 6, fraction = 2, message = INVALID_SUM_INPUT)
    private BigDecimal sum;
}
