package com.congrats.app.models.dto;

import java.time.LocalDate;

public class EmailDTO {
    private Long id;
    private LocalDate date;
    private String message;
    private Long teacherId;

    public EmailDTO(LocalDate date, String message, Long teacherId) {
        this.date = date;
        this.message = message;
        this.teacherId = teacherId;
    }

    public EmailDTO(Long id, LocalDate date, String message, Long teacherId) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.teacherId = teacherId;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}