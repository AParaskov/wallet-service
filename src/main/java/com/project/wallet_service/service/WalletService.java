package com.project.wallet_service.service;

import com.project.wallet_service.model.dto.request.CreateWalletRequest;
import com.project.wallet_service.model.dto.request.OperationRequest;
import com.project.wallet_service.model.dto.response.CreateWalletResponse;
import com.project.wallet_service.model.dto.response.OperationResponse;
import com.project.wallet_service.model.dto.response.ViewBalanceResponse;

public interface WalletService {
    CreateWalletResponse createWallet(CreateWalletRequest request);

    ViewBalanceResponse viewBalance(String email);

    OperationResponse operation(OperationRequest request);
}
