package com.congrats.app.controllers;

import com.congrats.app.models.entities.TemplateEntity;
import com.congrats.app.services.domain.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
public class TemplateFormatController {

    @Autowired
    private TemplateService templateService;

    // Create or update a template
    @PostMapping
    public ResponseEntity<TemplateEntity> createOrUpdateTemplate(@RequestBody TemplateEntity template) {
        TemplateEntity savedTemplate = templateService.saveTemplate(template);
        return new ResponseEntity<>(savedTemplate, HttpStatus.CREATED);
    }

    // Get all templates
    @GetMapping
    public ResponseEntity<List<TemplateEntity>> getAllTemplates() {
        List<TemplateEntity> templates = templateService.getAllTemplates();
        return new ResponseEntity<>(templates, HttpStatus.OK);
    }

    // Get template by ID
    @GetMapping("/{id}")
    public ResponseEntity<TemplateEntity> getTemplateById(@PathVariable Long id) {
        Optional<TemplateEntity> template = templateService.getTemplateById(id);
        return template.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a template
    @PutMapping("/{id}")
    public ResponseEntity<TemplateEntity> updateTemplate(@PathVariable Long id, @RequestBody TemplateEntity newDetails) {
        Optional<TemplateEntity> updatedTemplate = templateService.updateTemplate(id, newDetails);
        return updatedTemplate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete template by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}