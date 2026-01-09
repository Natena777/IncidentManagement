package org.example.incidentmanagement.exceptions;

public enum ResponseCodes {

    //Invalid Default Codes
    INVALID_EMAIL(-1, "Invalid Email"),
    INVALID_USERNAME(-2, "Invalid Username"),
    INVALID_PASSWORD(-3, "Invalid Password"),
    INVALID_USER(-4, "Invalid User"),
    INVALID_ROLE(-5, "Invalid Role"),
    INVALID_USER_ROLE(-6, "Invalid User Role"),
    FAILED_DATABASE_MANUAL_OPERATION(-7, "Database Manual Operation Failed"),
    
    //Invalid Specific Codes
    INVALID_ASSIGNEE_GROUP(-7, "Assignee Group Not Found"),
    INVALID_SERIVCE_CATALOG_DEPARTMENTS(-8, "Invalid Service Catalog Departments"),
    INVALID_SERIVCE_CATALOG_CATEGORY(-9, "Invalid Service Catalog Category"),
    INVALID_SERIVCE_CATALOG_SUBCATEGORY(-10, "Invalid Service Catalog SubCategory"),
    INVALID_SERIVCE_CATALOG_SERVICES(-11, "Invalid Service Catalog Services"),
    INVALID_CASE_STATUS(-12, "Invalid Case Status"),
    ASSIGNEE_GROUP_HAVE_CASE(-13, "Assignee Group Have Case"),
    ASSIGNEE_GROUP_HAVE_USERS(-14, "Assignee Group Have Users"),
    ASSIGNEE_GROUP_HAVE_SERVICES(-15, "Assignee Group Have Services"),


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
    DELETE_CASE_SUCCESFULY(90, "Case Deleted Succesfully"),
    ACCESS_RIGHTS_DELETED_SUCCESFULY(100, "Access Rights Deleted Succesfully"),


    //Not Found
    USERNAME_NOTFOUND(-150, "Username Not Found"),

    //Inactive User
    INACTIVE_USER(-40, "Inactive User, Please Contact System Administrator"),


    //Not Found
    USERNAME_NOTFOUND(-150, "Username Not Found"),

    //Inactive User
    INACTIVE_USER(-40, "Inactive User, Please Contact System Administrator"),


    //Already Exists Specific Codes
    USER_ROLE_EXIST(-50, "User Already Have One Role"),
    INVALID_NEW_ROLE(-16, "New Role Already Exists"),

    //Other Errors
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
