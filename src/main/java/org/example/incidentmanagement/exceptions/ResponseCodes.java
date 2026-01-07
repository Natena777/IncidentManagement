package org.example.incidentmanagement.exceptions;

public enum ResponseCodes {

    //Invalid Default Codes
    INVALID_EMAIL(-1, "Invalid Email"),
    INVALID_USERNAME(-2, "Invalid Username"),
    INVALID_PASSWORD(-3, "Invalid Password"),
    INVALID_USER(-4, "Invalid User"),
    INVALID_ROLE(-5, "Invalid Role"),
    
    //Invalid Specific Codes
    INVALID_ASSIGNEE_GROUP(-7, "Assignee Group Not Found"),
    INVALID_SERIVCE_CATALOG_DEPARTMENTS(-8, "Invalid Service Catalog Departments"),
    INVALID_SERIVCE_CATALOG_CATEGORY(-9, "Invalid Service Catalog Category"),
    INVALID_SERIVCE_CATALOG_SUBCATEGORY(-10, "Invalid Service Catalog SubCategory"),
    INVALID_SERIVCE_CATALOG_SERVICES(-11, "Invalid Service Catalog Services"),
    INVALID_CASE_STATUS(-12, "Invalid Case Status"),


    //Success Default Codes
    DELETE_SERVICE_CATALOG_DEPARTMENT_SUCCESFULY(30, "Service Catalog Department Deleted Succesfully"),
    DELETE_SERVICE_CATALOG_CATEGORY_SUCCESFULY(35, "Service Catalog Category Deleted Succesfully"),
    DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY(40, "Service Catalog SubCategory Deleted Succesfully"),
    DELETE_SERVICE_CATALOG_SERVICES_SUCCESFULY(45, "Service Catalog Services Deleted Succesfully"),
    USERROLEDELETED(10, "User Role Deleted Successfully "),
    USER_DELETED(15, "User  Deleted Successfully "),
    ROLE_DELETED(20, "Role Deleted Successfully "),
    ASSIGNEE_GROUP_DELETED(25, "Assignee Group Deleted Successfully "),
    CASE_STATUS_DELETED_SUCCESFULY(50, "Case Status Deleted Succesfully"),
    DELETE_CASE_SUCCESFULY(100, "Case Deleted Succesfully"),


    //Already Exists Specific Codes
    USER_ROLE_EXIST(-12, "User Already Have One Role"),
    INVALID_NEW_ROLE(-6, "New Role Already Exists"),

    //Other Errors
    INVALID_USER_ROLE(-13, "Invalid User Role"),
    VALIDATION_ERROR(-998, "Validation Error");

    private int code;
    private String message;

    ResponseCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {return code;}

    public String getMessage() {return message;}


}
