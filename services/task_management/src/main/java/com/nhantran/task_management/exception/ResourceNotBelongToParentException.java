package com.nhantran.task_management.exception;

public class ResourceNotBelongToParentException extends GenericDomainException {
    private final static String ERROR_CODE = "RESOURCE_NOT_BELONG_TO_PARENT";

    public ResourceNotBelongToParentException(String message) {
        super(message, ERROR_CODE);
    }
}