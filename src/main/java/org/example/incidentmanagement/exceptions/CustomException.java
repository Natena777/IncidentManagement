package org.example.incidentmanagement.exceptions;

public class CustomException extends RuntimeException {

    private final ErrorCodes errorCodes;

    public CustomException(ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.errorCodes = errorCodes;
    }


    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

}
