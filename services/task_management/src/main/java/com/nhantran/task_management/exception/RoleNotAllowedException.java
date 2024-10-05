package com.nhantran.task_management.exception;

public class RoleNotAllowedException extends GenericDomainException {
    private static final String ERROR_CODE = "ROLE_NOT_ALLOWED";

    public RoleNotAllowedException() {
        super("User is not allowed to perform this action", ERROR_CODE);
    }
}
