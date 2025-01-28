package com.project.wallet_service.model.dto.response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OperationDto {
    private String timestamp;
    private String operation;
    private BigDecimal sum;
}
