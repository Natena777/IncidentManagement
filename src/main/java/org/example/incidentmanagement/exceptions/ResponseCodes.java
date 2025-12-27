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
    DELETE_SERVICE_CATALOG_DEPARTMENT_SUCCESFULY(30, "Service Catalog Department Deleted Succesfully"),
    INVALID_SERIVCE_CATALOG_CATEGORY(-9, "Invalid Service Catalog Category"),
    DELETE_SERVICE_CATALOG_CATEGORY_SUCCESFULY(35, "Service Catalog Category Deleted Succesfully"),
    INVALID_SERIVCE_CATALOG_SUBCATEGORY(-10, "Invalid Service Catalog SubCategory"),
    DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY(40, "Service Catalog SubCategory Deleted Succesfully"),
    INVALID_SERIVCE_CATALOG_SERVICES(-11, "Invalid Service Catalog Services"),
    DELETE_SERVICE_CATALOG_SERVICES_SUCCESFULY(45, "Service Catalog Services Deleted Succesfully"),
    CASE_STATUS_DELETED_SUCCESFULY(50, "Case Status Deleted Succesfully"),
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
