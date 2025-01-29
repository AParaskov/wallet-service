package com.project.wallet_service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.project.wallet_service.exception.ErrorCodesAndMessages.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(WalletAlreadyExistsException.class)
    public ResponseEntity<Object> handleWalletAlreadyExistsException(WalletAlreadyExistsException ex) {
        String errorMessageDescription = checkIfErrorMessageDescriptionIsValid(ex.getMessage());

        CustomError errorMessage = CustomError.builder()
                .timestamp(LocalDateTime.now().format(dateTimeFormatter))
                .errorCode(BAD_REQUEST_ERROR)
                .errorMessage(ErrorCodesAndMessages.WALLET_ALREADY_EXISTS_EXCEPTION_MESSAGE)
                .description(errorMessageDescription)
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchWalletException.class)
    public ResponseEntity<Object> handleNoSuchWalletException(NoSuchWalletException ex) {
        String errorMessageDescription = checkIfErrorMessageDescriptionIsValid(ex.getMessage());

        CustomError errorMessage = CustomError.builder()
                .timestamp(LocalDateTime.now().format(dateTimeFormatter))
                .errorCode(BAD_REQUEST_ERROR)
                .errorMessage(ErrorCodesAndMessages.NO_SUCH_WALLET_EXCEPTION_MESSAGE)
                .description(errorMessageDescription)
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Object> handleInsufficientFundsException(InsufficientFundsException ex) {
        String errorMessageDescription = checkIfErrorMessageDescriptionIsValid(ex.getMessage());

        CustomError errorMessage = CustomError.builder()
                .timestamp(LocalDateTime.now().format(dateTimeFormatter))
                .errorCode(BAD_REQUEST_ERROR)
                .errorMessage(ErrorCodesAndMessages.INSUFFICIENT_FUNDS_EXCEPTION_MESSAGE)
                .description(errorMessageDescription)
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerExceptions(NullPointerException ex) {
        String errorMessageDescription = checkIfErrorMessageDescriptionIsValid(ex.getMessage());

        CustomError errorMessage = CustomError.builder()
                .timestamp(LocalDateTime.now().format(dateTimeFormatter))
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(SYSTEM_ERROR_MESSAGE)
                .description(errorMessageDescription)
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorMessageDescriptions = ex.getBindingResult().getAllErrors().stream().map(e -> checkIfErrorMessageDescriptionIsValid(e.getDefaultMessage())).toList();

        CustomError errorMessage = CustomError.builder()
                .timestamp(LocalDateTime.now().format(dateTimeFormatter))
                .errorCode(BAD_REQUEST_ERROR)
                .errorMessage(ErrorCodesAndMessages.VALIDATION_ERROR_MESSAGE)
                .description(String.join(" | ", errorMessageDescriptions))
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessageDescription = checkIfErrorMessageDescriptionIsValid(ex.getMessage());

        CustomError errorMessage = CustomError.builder()
                .timestamp(LocalDateTime.now().format(dateTimeFormatter))
                .errorCode(BAD_REQUEST_ERROR)
                .errorMessage(VALIDATION_ERROR_MESSAGE)
                .description(errorMessageDescription)
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private String checkIfErrorMessageDescriptionIsValid(String errorMessageDescription) {
        return errorMessageDescription == null ? "" : errorMessageDescription;
    }
}
