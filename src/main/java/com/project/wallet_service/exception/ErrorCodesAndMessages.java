package com.project.wallet_service.exception;

public class ErrorCodesAndMessages {
    // Exception messages
    public static final String INSUFFICIENT_FUNDS_EXCEPTION_DESCRIPTION = "Insufficient funds.";
    public static final String INSUFFICIENT_FUNDS_EXCEPTION_MESSAGE = "Error during withdraw operation.";
    public static final String NO_SUCH_WALLET_EXCEPTION_DESCRIPTION = "No such wallet exists.";
    public static final String NO_SUCH_WALLET_EXCEPTION_MESSAGE = "Error during operation.";
    public static final String WALLET_ALREADY_EXISTS_EXCEPTION_DESCRIPTION = "Wallet already exists.";
    public static final String WALLET_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Error creating wallet.";
    public static final String BAD_REQUEST_ERROR = "400 - Bad request.";
    public static final String INTERNAL_SERVER_ERROR = "500 - Server error.";
    public static final String SYSTEM_ERROR_MESSAGE = "System error.";

    // Input validation messages
    public static final String VALIDATION_ERROR_MESSAGE = "Validation error.";
    public static final String INVALID_FULL_NAME_INPUT = "Invalid full name.";
    public static final String INVALID_EMAIL_INPUT = "Invalid email.";
    public static final String INVALID_PASSWORD_INPUT = "Password is not strong enough.";
    public static final String INVALID_SUM_INPUT = "Invalid sum.";
    public static final String INVALID_CONFIRM_PASSWORD_INPUT = "Passwords don't match.";
    public static final String INVALID_OPERATION_NAME_INPUT = "Invalid operation.";
}
