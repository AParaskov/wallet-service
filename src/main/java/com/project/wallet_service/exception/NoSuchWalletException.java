package com.project.wallet_service.exception;

public class NoSuchWalletException extends RuntimeException{
    public NoSuchWalletException(String message) {
        super(message);
    }
}
