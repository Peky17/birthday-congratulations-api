package com.congrats.app.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "email")
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Message is required")
    private String message;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonBackReference
    private TeacherEntity teacher;

    public EmailEntity() {}

    public EmailEntity(LocalDate date, String message) {
        this.date = date;
        this.message = message;
    }

    public EmailEntity(Long id, LocalDate date, String message) {
        this.id = id;
        this.date = date;
        this.message = message;
    }

    public EmailEntity(LocalDate date, String message, TeacherEntity teacher) {
        this.date = date;
        this.message = message;
        this.teacher = teacher;
    }

    public EmailEntity(Long id, LocalDate date, String message, TeacherEntity teacher) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.teacher = teacher;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }
}