package com.gestion.formation.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {
    
    private String adminProperty;

    public String getAdminProperty() {
        return adminProperty;
    }

    public void setAdminProperty(String adminProperty) {
        this.adminProperty = adminProperty;
    }
}