package com.congrats.app.services.domain;

import com.congrats.app.models.dto.EmailDTO;
import com.congrats.app.models.entities.EmailEntity;
import com.congrats.app.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public List<EmailDTO> getAllEmails() {
        return emailRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<EmailDTO> getEmailById(Long id) {
        return emailRepository.findById(id).map(this::mapToDTO);
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

    private EmailDTO mapToDTO(EmailEntity email) {
        return new EmailDTO(
                email.getId(),
                email.getDate(),
                email.getMessage(),
                email.getTeacher() != null ? email.getTeacher().getId() : null
        );
    }
}