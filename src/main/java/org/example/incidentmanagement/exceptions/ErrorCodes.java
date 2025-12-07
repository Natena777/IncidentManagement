package org.example.incidentmanagement.exceptions;

public enum ErrorCodes {

    INVALID_EMAIL(-20, "Invalid Email"),
    INVALID_USERNAME(-10, "Invalid Username"),
    INVALID_PASSWORD(-11, "Invalid Password"),
    INVALID_USER(-1, "Invalid User"),;

    private int code;
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {return code;}

    public String getMessage() {return message;}


}
