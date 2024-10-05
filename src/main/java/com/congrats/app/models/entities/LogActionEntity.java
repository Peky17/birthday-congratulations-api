package com.congrats.app.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity(name = "log_actions")
public class LogActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Action is required")
    private String action;
    @NotNull(message = "Date is required")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "administrator_id", nullable = false)
    @JsonBackReference
    private AdministratorEntity administrator;

    public LogActionEntity() {}

    public LogActionEntity(String action, LocalDate date) {
        this.action = action;
        this.date = date;
    }

    public LogActionEntity(Long id, String action, LocalDate date) {
        this.id = id;
        this.action = action;
        this.date = date;
    }

    public LogActionEntity(String action, LocalDate date, AdministratorEntity administrator) {
        this.action = action;
        this.date = date;
        this.administrator = administrator;
    }

    public LogActionEntity(Long id, String action, LocalDate date, AdministratorEntity administrator) {
        this.id = id;
        this.action = action;
        this.date = date;
        this.administrator = administrator;
    }
    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AdministratorEntity getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorEntity administrator) {
        this.administrator = administrator;
    }
}
