package com.gestion.formation.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends User {

    private String formateurProperty;

    public String getFormateurProperty() {
        return formateurProperty;
    }

    public void setFormateurProperty(String formateurProperty) {
        this.formateurProperty = formateurProperty;
    }
}