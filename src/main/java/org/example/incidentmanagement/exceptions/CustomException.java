package org.example.incidentmanagement.exceptions;

public class CustomException extends RuntimeException {

    private final ResponseCodes responseCodes;

    public CustomException(ResponseCodes responseCodes) {
        super(responseCodes.getMessage());
        this.responseCodes = responseCodes;
    }


    public ResponseCodes getErrorCodes() {
        return responseCodes;
    }

}
