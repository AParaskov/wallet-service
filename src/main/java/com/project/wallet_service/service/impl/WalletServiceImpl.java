package com.project.wallet_service.service.impl;

import com.project.wallet_service.exception.InsufficientFundsException;
import com.project.wallet_service.exception.NoSuchWalletException;
import com.project.wallet_service.exception.WalletAlreadyExistsException;
import com.project.wallet_service.model.Operation;
import com.project.wallet_service.model.Wallet;
import com.project.wallet_service.model.dto.request.CreateWalletRequest;
import com.project.wallet_service.model.dto.request.OperationRequest;
import com.project.wallet_service.model.dto.response.CreateWalletResponse;
import com.project.wallet_service.model.dto.response.OperationResponse;
import com.project.wallet_service.model.dto.response.ViewBalanceResponse;
import com.project.wallet_service.model.enumeration.OperationName;
import com.project.wallet_service.repository.OperationRepository;
import com.project.wallet_service.repository.WalletRepository;
import com.project.wallet_service.service.WalletService;
import com.project.wallet_service.util.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.project.wallet_service.exception.ErrorCodesAndMessages.*;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final OperationRepository operationRepository;
    private final WalletMapper walletMapper;

    @Override
    public CreateWalletResponse createWallet(CreateWalletRequest request) {
        if (walletRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new WalletAlreadyExistsException(WALLET_ALREADY_EXISTS_EXCEPTION_DESCRIPTION);
        }

        Wallet wallet = walletRepository.save(walletMapper.createWalletRequestToWalletEntity(request));

        return walletMapper.walletEntityToCreateWalletResponse(wallet);
    }

    @Override
    public ViewBalanceResponse viewBalance(String email) {
        Optional<Wallet> wallet = walletRepository.findByEmail(email);

        if (wallet.isEmpty()) {
            throw new NoSuchWalletException(NO_SUCH_WALLET_EXCEPTION_DESCRIPTION);
        }

        return walletMapper.walletEntityToViewBalanceResponse(wallet.get());
    }

    @Override
    public OperationResponse operation(OperationRequest request) {
        Optional<Wallet> wallet = walletRepository.findByEmail(request.getEmail());

        if (wallet.isEmpty()) {
            throw new NoSuchWalletException(NO_SUCH_WALLET_EXCEPTION_DESCRIPTION);
        }

        if (request.getName().equals(OperationName.WITHDRAW.name())) {
            if (wallet.get().getBalance().subtract(request.getSum()).compareTo(BigDecimal.ZERO) < 0) {
                throw new InsufficientFundsException(INSUFFICIENT_FUNDS_EXCEPTION_DESCRIPTION);
            }

            return walletMapper.operationEntityToOperationResponse(saveOperation(wallet.get(), request));
        }

        return walletMapper.operationEntityToOperationResponse(saveOperation(wallet.get(), request));
    }

    private Operation saveOperation(Wallet wallet, OperationRequest request) {
        BigDecimal newBalance = request.getName().equals(OperationName.WITHDRAW.name()) ? wallet.getBalance().subtract(request.getSum()) : wallet.getBalance().add(request.getSum());
        wallet.setBalance(newBalance);
        walletRepository.saveAndFlush(wallet);

        return operationRepository.saveAndFlush(walletMapper.operationRequestToOperationEntity(request, wallet));
    }
}
