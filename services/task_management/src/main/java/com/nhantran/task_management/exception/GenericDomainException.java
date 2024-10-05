package com.nhantran.task_management.exception;

import lombok.Getter;

@Getter
public abstract class GenericDomainException extends RuntimeException {
    private final String errorCode;

    public GenericDomainException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
