package com.congrats.app.models.dto;
public class ContactDTO {
    private String name;
    private String to;
    private String subject;
    private String message;

    public ContactDTO() {}

    public ContactDTO(String name, String to, String subject, String message) {
        this.name = name;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
