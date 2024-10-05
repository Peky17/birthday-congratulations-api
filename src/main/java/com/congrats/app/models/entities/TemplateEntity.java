package com.congrats.app.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "CustomTemplate")
public class TemplateEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private String logoSrc;
    private String rectorName;
    private String rectorSignatureSrc;

    public TemplateEntity() {
    }

    public TemplateEntity(String title, String subtitle, String description, String logoSrc, String rectorName, String rectorSignatureSrc) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.logoSrc = logoSrc;
        this.rectorName = rectorName;
        this.rectorSignatureSrc = rectorSignatureSrc;
    }

    public TemplateEntity(Long id, String title, String subtitle, String description, String logoSrc, String rectorName, String rectorSignatureSrc) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.logoSrc = logoSrc;
        this.rectorName = rectorName;
        this.rectorSignatureSrc = rectorSignatureSrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoSrc() {
        return logoSrc;
    }

    public void setLogoSrc(String logoSrc) {
        this.logoSrc = logoSrc;
    }

    public String getRectorName() {
        return rectorName;
    }

    public void setRectorName(String rectorName) {
        this.rectorName = rectorName;
    }

    public String getRectorSignatureSrc() {
        return rectorSignatureSrc;
    }

    public void setRectorSignatureSrc(String rectorSignatureSrc) {
        this.rectorSignatureSrc = rectorSignatureSrc;
    }
}
