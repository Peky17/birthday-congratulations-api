package com.congrats.app.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "teachers")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "Phone is required")
    private String phone;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Birthdate is required")
    private LocalDate birthdate;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EmailEntity> emails;

    public TeacherEntity() {}

    public TeacherEntity(String name, String email, String phone, String address, LocalDate birthdate) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
    }

    public TeacherEntity(String name, String email, String phone, String address, LocalDate birthdate, List<EmailEntity> emails) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
        this.emails = emails;
    }

    public TeacherEntity(Long id, String name, String email, String phone, String address, LocalDate birthdate, List<EmailEntity> emails) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
        this.emails = emails;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<EmailEntity> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailEntity> emails) {
        this.emails = emails;
    }
}