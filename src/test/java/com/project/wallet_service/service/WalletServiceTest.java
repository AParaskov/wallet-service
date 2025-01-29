package com.project.wallet_service.service;

import com.project.wallet_service.exception.InsufficientFundsException;
import com.project.wallet_service.exception.NoSuchWalletException;
import com.project.wallet_service.exception.WalletAlreadyExistsException;
import com.project.wallet_service.model.dto.request.OperationRequest;
import com.project.wallet_service.model.dto.response.CreateWalletResponse;
import com.project.wallet_service.model.dto.response.OperationResponse;
import com.project.wallet_service.model.dto.response.ViewBalanceResponse;
import com.project.wallet_service.repository.OperationRepository;
import com.project.wallet_service.repository.WalletRepository;
import com.project.wallet_service.service.impl.WalletServiceImpl;
import com.project.wallet_service.util.mapper.WalletMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static com.project.wallet_service.util.TestUtil.FIFTY;
import static com.project.wallet_service.util.TestUtil.VALID_EMAIL;
import static com.project.wallet_service.util.TestUtil.ZERO;
import static com.project.wallet_service.util.TestUtil.buildValidCreateWalletRequest;
import static com.project.wallet_service.util.TestUtil.buildValidCreateWalletResponse;
import static com.project.wallet_service.util.TestUtil.buildValidDepositOperationRequest;
import static com.project.wallet_service.util.TestUtil.buildValidOperationResponse;
import static com.project.wallet_service.util.TestUtil.buildValidViewBalanceResponse;
import static com.project.wallet_service.util.TestUtil.buildValidWalletEntity;
import static com.project.wallet_service.util.TestUtil.buildValidWithdrawOperationRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
public class WalletServiceTest {
    private WalletService walletService;
    private WalletRepository walletRepository;
    private OperationRepository operationRepository;
    private WalletMapper walletMapper;

    @BeforeEach
    public void setUp() {
        walletRepository = Mockito.mock(WalletRepository.class);
        operationRepository = Mockito.mock(OperationRepository.class);
        walletMapper = Mockito.mock(WalletMapper.class);
        walletService = new WalletServiceImpl(walletRepository, operationRepository, walletMapper);
    }

    @Test
    public void createWalletTest() {
        CreateWalletResponse expectedResponse = buildValidCreateWalletResponse();

        when(walletRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(walletMapper.createWalletRequestToWalletEntity(any())).thenReturn(buildValidWalletEntity(ZERO));
        when(walletMapper.walletEntityToCreateWalletResponse(any())).thenReturn(buildValidCreateWalletResponse());
        when(walletRepository.save(any())).thenReturn(buildValidWalletEntity(ZERO));

        CreateWalletResponse actualResponse = walletService.createWallet(buildValidCreateWalletRequest());

        assertEquals(actualResponse.getFullName(), expectedResponse.getFullName());
        assertEquals(actualResponse.getBalance(), expectedResponse.getBalance());
        assertEquals(actualResponse.getEmail(), expectedResponse.getEmail());
    }

    @Test
    public void createWalletThrowsWalletAlreadyExistsExceptionTest() {
        when(walletRepository.findByEmail(any())).thenReturn(Optional.of(buildValidWalletEntity(ZERO)));

        assertThrows(WalletAlreadyExistsException.class, () -> walletService.createWallet(buildValidCreateWalletRequest()));
    }

    @Test
    public void viewBalanceTest() {
        ViewBalanceResponse expectedResponse = buildValidViewBalanceResponse();

        when(walletRepository.findByEmail(any())).thenReturn(Optional.of(buildValidWalletEntity(FIFTY)));
        when(walletMapper.walletEntityToViewBalanceResponse(any())).thenReturn(buildValidViewBalanceResponse());

        ViewBalanceResponse actualResponse = walletService.viewBalance(VALID_EMAIL);

        assertEquals(actualResponse.getFullName(), expectedResponse.getFullName());
        assertEquals(actualResponse.getBalance(), expectedResponse.getBalance());
        assertEquals(actualResponse.getEmail(), expectedResponse.getEmail());
        assertEquals(actualResponse.getAccountMovements().size(), expectedResponse.getAccountMovements().size());
    }

    @Test
    public void viewBalanceThrowsNoSuchWalletExceptionTest() {
        when(walletRepository.findByEmail(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchWalletException.class, () -> walletService.viewBalance(VALID_EMAIL));
    }

    @Test
    public void operationDepositTest() {
        OperationResponse expectedResponse = buildValidOperationResponse();

        when(walletRepository.findByEmail(any())).thenReturn(Optional.of(buildValidWalletEntity(FIFTY)));
        when(walletMapper.operationEntityToOperationResponse(any())).thenReturn(buildValidOperationResponse());

        OperationResponse actualResponse = walletService.operation(buildValidDepositOperationRequest());

        assertEquals(actualResponse.getOperation(), expectedResponse.getOperation());
        assertEquals(actualResponse.getBalance(), expectedResponse.getBalance());
        assertEquals(actualResponse.getSum(), expectedResponse.getSum());
        assertEquals(actualResponse.getTimestamp(), expectedResponse.getTimestamp());
    }

    @Test
    public void operationWithdrawTest() {
        OperationResponse expectedResponse = buildValidOperationResponse();

        when(walletRepository.findByEmail(any())).thenReturn(Optional.of(buildValidWalletEntity(FIFTY)));
        when(walletMapper.operationEntityToOperationResponse(any())).thenReturn(buildValidOperationResponse());


        OperationResponse actualResponse = walletService.operation(buildValidWithdrawOperationRequest());

        assertEquals(actualResponse.getOperation(), expectedResponse.getOperation());
        assertEquals(actualResponse.getBalance(), expectedResponse.getBalance());
        assertEquals(actualResponse.getSum(), expectedResponse.getSum());
        assertEquals(actualResponse.getTimestamp(), expectedResponse.getTimestamp());
    }

    @Test
    public void operationThrowsNoSuchWalletExceptionTest() {
        when(walletRepository.findByEmail(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchWalletException.class, () -> walletService.operation(buildValidDepositOperationRequest()));
    }

    @Test
    public void operationThrowsInsufficientFundsExceptionTest() {
        OperationRequest request = buildValidWithdrawOperationRequest();
        request.setSum(BigDecimal.valueOf(70L));

        when(walletRepository.findByEmail(any())).thenReturn(Optional.of(buildValidWalletEntity(FIFTY)));

        assertThrows(InsufficientFundsException.class, () -> walletService.operation(request));
    }
}
