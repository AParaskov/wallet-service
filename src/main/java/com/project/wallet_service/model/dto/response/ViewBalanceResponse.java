package com.project.wallet_service.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ViewBalanceResponse {
    private String fullName;
    private String email;
    private BigDecimal balance;
    private List<OperationDto> accountMovements;
}
