package com.congrats.app.services.domain;

import com.congrats.app.models.entities.TemplateEntity;
import com.congrats.app.repositories.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    // Create or update a template
    public TemplateEntity saveTemplate(TemplateEntity template) {
        return templateRepository.save(template);
    }

    // Update a template
    public Optional<TemplateEntity> updateTemplate(Long id, TemplateEntity newDetails) {
        return templateRepository.findById(id).map(template -> {
            template.setTitle(newDetails.getTitle());
            template.setSubtitle(newDetails.getSubtitle());
            template.setDescription(newDetails.getDescription());
            template.setLogoSrc(newDetails.getLogoSrc());
            template.setRectorName(newDetails.getRectorName());
            template.setRectorSignatureSrc(newDetails.getRectorSignatureSrc());
            // Set other fields as necessary
            return templateRepository.save(template);
        });
    }

    // Get all templates
    public List<TemplateEntity> getAllTemplates() {
        return templateRepository.findAll();
    }

    // Get template by ID
    public Optional<TemplateEntity> getTemplateById(Long id) {
        return templateRepository.findById(id);
    }

    // Get the latest template
    public List<TemplateEntity> getLatestTemplate() {
        return templateRepository.findTopByOrderByIdDesc();
    }

    // Delete template by ID
    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }
}