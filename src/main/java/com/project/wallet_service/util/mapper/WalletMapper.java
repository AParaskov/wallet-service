package com.project.wallet_service.util.mapper;

import com.project.wallet_service.model.Operation;
import com.project.wallet_service.model.Wallet;
import com.project.wallet_service.model.dto.request.CreateWalletRequest;
import com.project.wallet_service.model.dto.request.OperationRequest;
import com.project.wallet_service.model.dto.response.CreateWalletResponse;
import com.project.wallet_service.model.dto.response.OperationDto;
import com.project.wallet_service.model.dto.response.OperationResponse;
import com.project.wallet_service.model.dto.response.ViewBalanceResponse;
import com.project.wallet_service.model.enumeration.OperationName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class WalletMapper {
    private final BCryptPasswordEncoder passwordEncoder;
    private final SimpleDateFormat simpleDateFormat;

    public Wallet createWalletRequestToWalletEntity(CreateWalletRequest request) {
        return Wallet.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .balance(BigDecimal.ZERO)
                .accountMovements(new ArrayList<>())
                .build();
    }

    public CreateWalletResponse walletEntityToCreateWalletResponse(Wallet wallet) {
        return CreateWalletResponse.builder()
                .fullName(wallet.getFullName())
                .email(wallet.getEmail())
                .balance(wallet.getBalance())
                .build();
    }

    public Operation operationRequestToOperationEntity(OperationRequest request, Wallet wallet) {
        return Operation.builder()
                .name(OperationName.valueOf(request.getName()))
                .sum(request.getSum())
                .wallet(wallet)
                .build();
    }

    public OperationResponse operationEntityToOperationResponse(Operation operation) {
        return OperationResponse.builder()
                .operation(operation.getName().name())
                .sum(operation.getSum())
                .timestamp(simpleDateFormat.format(operation.getDateTime()))
                .balance(operation.getWallet().getBalance())
                .build();
    }

    public ViewBalanceResponse walletEntityToViewBalanceResponse(Wallet wallet) {
        return ViewBalanceResponse.builder()
                .fullName(wallet.getFullName())
                .email(wallet.getEmail())
                .balance(wallet.getBalance())
                .accountMovements(wallet.getAccountMovements().stream().map(am -> OperationDto.builder()
                        .operation(am.getName().name())
                        .sum(am.getSum())
                        .timestamp(simpleDateFormat.format(am.getDateTime()))
                        .build()).toList())
                .build();
    }
}
