package com.pk.customer.advice;

import javax.security.sasl.AuthenticationException;
import javax.validation.UnexpectedTypeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.pk.customer.domain.ErrorResponse;
import com.pk.customer.exception.CustomerServiceRequestException;

@ControllerAdvice
public class CustomerControllerAdvice {
  private static final Logger log = LoggerFactory.getLogger(CustomerControllerAdvice.class);

  private static final String FAILED = "Failed";

  @ExceptionHandler(ServletRequestBindingException.class)
  public ResponseEntity<ErrorResponse> handleException(ServletRequestBindingException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(FAILED);
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorType(ServletRequestBindingException.class.getSimpleName());
    log.error("Error response - {}", errorResponse);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleException(AuthenticationException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(FAILED);
    errorResponse.setMessage(String.format("%s - %s", "Authentication Error", ex.getMessage()));
    errorResponse.setErrorType(AuthenticationException.class.getSimpleName());
    log.error("Error response - {}", errorResponse);
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(NoHandlerFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(FAILED);
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorType(NoHandlerFoundException.class.getSimpleName());
    log.error("Error response - {}", errorResponse);
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(FAILED);
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorType(MethodArgumentNotValidException.class.getSimpleName());
    log.error("Error response - {}", errorResponse);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomerServiceRequestException.class)
  public ResponseEntity<ErrorResponse> handleException(CustomerServiceRequestException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(FAILED);
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorType(CustomerServiceRequestException.class.getSimpleName());
    log.error("Error response - {}", errorResponse);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  public ResponseEntity<ErrorResponse> handleException(UnexpectedTypeException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(FAILED);
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorType(UnexpectedTypeException.class.getSimpleName());
    log.error("Error response - {}", errorResponse);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
