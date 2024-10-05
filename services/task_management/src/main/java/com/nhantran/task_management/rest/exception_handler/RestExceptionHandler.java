package com.nhantran.task_management.rest.exception_handler;

import com.nhantran.task_management.exception.ResourceNotFoundException;
import com.nhantran.task_management.exception.RoleNotAllowedException;
import com.nhantran.task_management.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .error(ex.getErrorCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {RoleNotAllowedException.class})
    protected ResponseEntity<ErrorResponse> handleRoleNotAllowed(RoleNotAllowedException ex, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .error(ex.getErrorCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .error(ex.getErrorCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<ErrorResponse> handleUnknown(RuntimeException ex, WebRequest request) {
        log.error("an error occurred with the request", ex);

        ErrorResponse response = ErrorResponse.builder()
                .error("INTERNAL_ERROR")
                .message("Some unexpected error occurred, please contact the developer")
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}