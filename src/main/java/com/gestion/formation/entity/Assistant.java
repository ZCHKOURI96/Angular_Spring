package com.gestion.formation.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("assistant")
public class Assistant extends User {

    private String assistantProperty;

    public String getAssistantProperty() {
        return assistantProperty;
    }

    public void setAssistantProperty(String assistantProperty) {
        this.assistantProperty = assistantProperty;
    }
}
