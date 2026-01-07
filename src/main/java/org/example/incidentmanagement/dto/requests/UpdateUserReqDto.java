package org.example.incidentmanagement.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserReqDto {

    @NotBlank(message = "First Name is Required")
    private String firstName;

    @NotBlank(message = "Last Name is Required")
    private String lastName;
    private String email;
    private String address;

    @NotBlank(message = "Password is Required")
    @Size(min = 8, message = "Password Must Be Minimum 5 Symbol")
    private String password;
    private String phone;


    public UpdateUserReqDto() {}

    public @NotBlank(message = "First Name is Required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First Name is Required") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last Name is Required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last Name is Required") String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public @NotBlank(message = "Password is Required") @Size(min = 8, message = "Password Must Be Minimum 5 Symbol") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is Required") @Size(min = 8, message = "Password Must Be Minimum 5 Symbol") String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
