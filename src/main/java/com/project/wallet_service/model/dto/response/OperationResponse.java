package com.project.wallet_service.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OperationResponse {
    private String timestamp;
    private String operation;
    private BigDecimal sum;
    private BigDecimal balance;
}
