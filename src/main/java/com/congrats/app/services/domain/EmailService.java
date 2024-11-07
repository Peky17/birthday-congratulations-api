package com.congrats.app.services.domain;

import com.congrats.app.models.entities.EmailEntity;
import com.congrats.app.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    public List<EmailEntity> getAllEmails() {
        return emailRepository.findAll();
    }

    public Optional<EmailEntity> getEmailById(Long id) {
        return emailRepository.findById(id);
    }

    public EmailEntity createEmail(EmailEntity email) {
        return emailRepository.save(email);
    }

    public EmailEntity updateEmail(Long id, EmailEntity emailDetails) {
        return emailRepository.findById(id).map(email -> {
            email.setDate(emailDetails.getDate());
            email.setMessage(emailDetails.getMessage());
            email.setTeacher(emailDetails.getTeacher());
            return emailRepository.save(email);
        }).orElse(null);
    }

    public boolean deleteEmail(Long id) {
        if (emailRepository.existsById(id)) {
            emailRepository.deleteById(id);
            return true;
        }
        return false;
    }
}