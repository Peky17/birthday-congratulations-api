package com.congrats.app.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "administrators")
public class AdministratorEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is required")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Cellphone is required")
    private String cellphone;
    @NotNull(message = "Password is required")
    private String password;

    @OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<LogActionEntity> logActions;

    public AdministratorEntity() {}

    public AdministratorEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AdministratorEntity(String name, String email, String cellphone, String password) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.password = password;
    }

    public AdministratorEntity(String name, String email, String cellphone, String password, List<LogActionEntity> logActions) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.password = password;
        this.logActions = logActions;
    }

    public AdministratorEntity(Long id, String name, String email, String cellphone, String password, List<LogActionEntity> logActions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.password = password;
        this.logActions = logActions;
    }

    // Getters and setters
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<LogActionEntity> getLogActions() {
        return logActions;
    }

    public void setLogActions(List<LogActionEntity> logActions) {
        this.logActions = logActions;
    }

    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
