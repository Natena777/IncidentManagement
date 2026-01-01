package org.example.incidentmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "sys_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private Integer CreatedBy;

    @Column(name = "modified_by")
    private Integer UpdatedBy;

    @Column(name = "modified_on")
    private LocalDateTime updatedOn;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "first_name_en")
    private String firstNameEn;

    @Column(name = "last_name_en")
    private String lastNameEn;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "active")
    private String active;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public User() {
    }

    public User(int id, LocalDateTime createdOn, Integer createdBy,
                LocalDateTime updatedOn, Integer updatedBy, String username,
                String firstName, String lastName, String fullName,
                String firstNameEn, String lastNameEn, String fullNameEn,
                String password,
                String email, String phone, String address, String active,
                LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.createdOn = createdOn;
        this.CreatedBy = createdBy;
        this.updatedOn = updatedOn;
        this.UpdatedBy = updatedBy;
        this.username = username;
        this.firstName= firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.fullNameEn = fullNameEn;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    public Integer getCreatedBy() {
        return CreatedBy;
    }
    public void setCreatedBy(Integer createdBy) {
        this.CreatedBy = createdBy;
    }
    public Integer getUpdatedBy() {
        return UpdatedBy;
    }
    public void setUpdatedBy(Integer updatedBy) {
        this.UpdatedBy = updatedBy;
    }
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }
    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public String getActive() {return active;}
    public void setActive(String active) {this.active = active;}
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

    public String getFirstNameEn() {
        return firstNameEn;
    }
    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }
    public String getLastNameEn() {
        return lastNameEn;
    }
    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }
    public String getFullNameEn() {
        return fullNameEn;
    }
    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }




}
