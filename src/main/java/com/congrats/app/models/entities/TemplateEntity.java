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
}
