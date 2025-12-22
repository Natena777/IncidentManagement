package org.example.incidentmanagement.exceptions;

public enum ResponseCodes {

    INVALID_EMAIL(-1, "Invalid Email"),
    INVALID_USERNAME(-2, "Invalid Username"),
    INVALID_PASSWORD(-3, "Invalid Password"),
    INVALID_USER(-4, "Invalid User"),
    INVALID_ROLE(-5, "Invalid Role"),
    INVALID_NEW_ROLE(-6, "New Role Already Exists"),
    INVALID_ASSIGNEE_GROUP(-7, "Assignee Group Not Found"),
    INVALID_SERIVCE_CATALOG_DEPARTMENTS(-8, "Invalid Service Catalog Departments"),
    USERROLEDELETED(10, "User Role Deleted Successfully "),
    USER_DELETED(15, "User  Deleted Successfully "),
    ROLE_DELETED(20, "Role Deleted Successfully "),
    ASSIGNEE_GROUP_DELETED(25, "Assignee Group Deleted Successfully "),
    VALIDATION_ERROR(-999, "Validation Error");

    private int code;
    private String message;

    ResponseCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {return code;}

    public String getMessage() {return message;}


}
