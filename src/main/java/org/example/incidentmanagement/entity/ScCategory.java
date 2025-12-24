package org.example.incidentmanagement.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "category")
public class ScCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String scCategoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private String active;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy; 

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public ScCategory() {
    }

    public ScCategory(Integer id, String scCategoryName, String description, String active,
                      Integer createdBy, Integer updatedBy,
                      LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.id = id;
        this.scCategoryName = scCategoryName;
        this.description = description;
        this.active = active;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getScCategoryName() {
        return scCategoryName;
    }
    public void setScCategoryName(String scCategoryName) {  
        this.scCategoryName = scCategoryName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public Integer getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    public Integer getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }
    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

}