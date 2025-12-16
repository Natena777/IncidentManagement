package org.example.incidentmanagement.exceptions;

public enum ErrorCodes {

    INVALID_EMAIL(-1, "Invalid Email"),
    INVALID_USERNAME(-2, "Invalid Username"),
    INVALID_PASSWORD(-3, "Invalid Password"),
    INVALID_USER(-4, "Invalid User"),
    INVALID_ROLE(-5, "Invalid Role"),
    INVALID_NEW_ROLE(-6, "New Role Already Exists"),
    NULL_CODE(-7, "Null Parameter");

    private int code;
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {return code;}

    public String getMessage() {return message;}


}
