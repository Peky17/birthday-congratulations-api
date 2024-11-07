package com.congrats.app.controllers;

import com.congrats.app.models.entities.EmailEntity;
import com.congrats.app.services.domain.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<EmailEntity> getAllEmails() {
        return emailService.getAllEmails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailEntity> getEmailById(@PathVariable Long id) {
        Optional<EmailEntity> email = emailService.getEmailById(id);
        return email.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmailEntity createEmail(@RequestBody EmailEntity email) {
        return emailService.createEmail(email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailEntity> updateEmail(@PathVariable Long id, @RequestBody EmailEntity emailDetails) {
        EmailEntity updatedEmail = emailService.updateEmail(id, emailDetails);
        return updatedEmail != null ? ResponseEntity.ok(updatedEmail) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        boolean deleted = emailService.deleteEmail(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}