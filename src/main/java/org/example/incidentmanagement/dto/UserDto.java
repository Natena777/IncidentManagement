package org.example.incidentmanagement.dto;

public class UserDto {

    private int id;
    private String fistName;
    private String lastName;
    private String email;


    public UserDto() {}
    public UserDto(int id, String fistName, String lastName, String email) {
        this.id = id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFistName() {
        return fistName;
    }
    public void setFistName(String fistName) {
        this.fistName = fistName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
