package org.example.incidentmanagement.model;

import java.util.Date;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Date startDate;
    private Date endDate;

    public User() {
    }

    public User(int id, String username, String password, String email, String phone, String address, Date startDate, Date endDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private User(int id, String username, String password, String email, Date startDate, Date endDate) {
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

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }




}
