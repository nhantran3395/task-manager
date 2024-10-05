package com.nhantran.task_management.exception;

public class UserNotFoundException extends GenericDomainException {
    private static final String ERROR_CODE = "USER_NOT_FOUND";

    public UserNotFoundException() {
        super("User with this request is not found", ERROR_CODE);
    }
}
