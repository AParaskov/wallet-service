package com.project.wallet_service.controller;

import com.project.wallet_service.model.dto.request.CreateWalletRequest;
import com.project.wallet_service.model.dto.request.OperationRequest;
import com.project.wallet_service.model.dto.response.CreateWalletResponse;
import com.project.wallet_service.model.dto.response.OperationResponse;
import com.project.wallet_service.model.dto.response.ViewBalanceResponse;
import com.project.wallet_service.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateWalletResponse> createWallet(@Valid @RequestBody CreateWalletRequest request) {
        return new ResponseEntity<>(walletService.createWallet(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewBalanceResponse> viewBalance(@RequestParam String email) {
        return new ResponseEntity<>(walletService.viewBalance(email), HttpStatus.OK);
    }

    @PostMapping(value = "/operation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationResponse> operation(@Valid @RequestBody OperationRequest request) {
        return new ResponseEntity<>(walletService.operation(request), HttpStatus.OK);
    }
}
