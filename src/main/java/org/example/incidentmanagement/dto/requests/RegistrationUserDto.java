package org.example.incidentmanagement.dto.requests;

public class RegistrationUserDto {

    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String password;


    public RegistrationUserDto() {}

    public RegistrationUserDto(String firstname, String lastname, String email, String address, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getPassword() {
        return password;
    }



}
