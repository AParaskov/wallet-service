package com.project.wallet_service.util;

import com.project.wallet_service.model.Operation;
import com.project.wallet_service.model.Wallet;
import com.project.wallet_service.model.dto.request.CreateWalletRequest;
import com.project.wallet_service.model.dto.request.OperationRequest;
import com.project.wallet_service.model.dto.response.CreateWalletResponse;
import com.project.wallet_service.model.dto.response.OperationDto;
import com.project.wallet_service.model.dto.response.OperationResponse;
import com.project.wallet_service.model.dto.response.ViewBalanceResponse;
import com.project.wallet_service.model.enumeration.OperationName;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class TestUtil {
    public static final String CREATE_WALLET_URL = "/wallet/create";
    public static final String VIEW_BALANCE_URL = "/wallet/balance";
    public static final String OPERATION_URL = "/wallet/operation";
    public static final String INCORRECT_URL = "/incorrect-url";
    public static final String VALID_EMAIL = "fullName@gmail.com";
    public static final String VALID_FULL_NAME = "Full Name";
    public static final String VALID_PASSWORD = "PassWord123@";
    public static final String VALID_OPERATION_DEPOSIT = "DEPOSIT";
    public static final String VALID_OPERATION_WITHDRAW = "WITHDRAW";
    public static final String VALID_TIMESTAMP = "2025-01-29 11:30:00";
    public static final BigDecimal ZERO = BigDecimal.ZERO;
    public static final BigDecimal FIFTY = BigDecimal.valueOf(50L);

    public static CreateWalletRequest buildValidCreateWalletRequest() {
        return CreateWalletRequest.builder()
                .fullName(VALID_FULL_NAME)
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .confirmPassword(VALID_PASSWORD)
                .build();
    }

    public static CreateWalletRequest buildInvalidCreateWalletRequest() {
        return CreateWalletRequest.builder()
                .fullName("fullname")
                .email("fullName@gmailcom")
                .password("password123@")
                .confirmPassword("password123@")
                .build();
    }

    public static OperationRequest buildValidDepositOperationRequest() {
        return OperationRequest.builder()
                .email(VALID_EMAIL)
                .name(VALID_OPERATION_DEPOSIT)
                .sum(FIFTY)
                .build();
    }

    public static OperationRequest buildValidWithdrawOperationRequest() {
        return OperationRequest.builder()
                .email(VALID_EMAIL)
                .name(VALID_OPERATION_WITHDRAW)
                .sum(FIFTY)
                .build();
    }

    public static OperationRequest buildInvalidOperationRequest() {
        return OperationRequest.builder()
                .email("fullName@gmailcom")
                .name("operation")
                .sum(BigDecimal.valueOf(10L))
                .build();
    }

    public static Wallet buildValidWalletEntity(BigDecimal balance) {
        return Wallet.builder()
                .fullName(VALID_FULL_NAME)
                .email(VALID_EMAIL)
                .balance(balance)
                .password(VALID_PASSWORD)
                .build();
    }

    public static Operation buildValidOperationEntity() {
        return Operation.builder()
                .name(OperationName.DEPOSIT)
                .sum(FIFTY)
                .dateTime(Timestamp.valueOf(VALID_TIMESTAMP))
                .build();
    }

    public static CreateWalletResponse buildValidCreateWalletResponse() {
        return CreateWalletResponse.builder()
                .email(VALID_EMAIL)
                .balance(ZERO)
                .fullName(VALID_FULL_NAME)
                .build();
    }

    public static ViewBalanceResponse buildValidViewBalanceResponse() {
        return ViewBalanceResponse.builder()
                .balance(FIFTY)
                .email(VALID_EMAIL)
                .fullName(VALID_FULL_NAME)
                .accountMovements(List.of(buildValidOperationDto()))
                .build();
    }

    public static OperationResponse buildValidOperationResponse() {
        return OperationResponse.builder()
                .balance(FIFTY)
                .operation(VALID_OPERATION_DEPOSIT)
                .timestamp(VALID_TIMESTAMP)
                .sum(FIFTY)
                .build();
    }

    private static OperationDto buildValidOperationDto() {
        return OperationDto.builder()
                .sum(FIFTY)
                .operation(VALID_OPERATION_DEPOSIT)
                .timestamp(VALID_TIMESTAMP)
                .build();
    }
}
