package com.nhantran.task_management.exception;

public class ResourceNotFoundException extends GenericDomainException {
    private static final String ERROR_CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotFoundException() {
        super("Requested resource does not exist", ERROR_CODE);
    }
}
