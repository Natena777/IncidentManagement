package org.example.incidentmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "sys_users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public User() {
    }

    public User(int id, String username, String firstname, String lastname, String password, String email, String phone, String address, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.username = username;
        this.firstname= firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private User(int id, String username, String password, String email, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }




}
