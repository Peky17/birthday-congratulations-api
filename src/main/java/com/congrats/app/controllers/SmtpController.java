package com.congrats.app.controllers;

import com.congrats.app.models.dto.ContactDTO;
import com.congrats.app.services.domain.EmailService;
import com.congrats.app.services.domain.SmtpService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/mail")
public class SmtpController {
    @Autowired
    private SmtpService smtpService;

    @PostMapping("/sendEmailToTeacher")
    public void sendHtmlMailWithAttachment(@RequestBody ContactDTO contact) throws MessagingException, IOException {
        smtpService.sendHtmlMailWithAttachment(contact.getName(), contact.getTo(), contact.getSubject(),
                contact.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred");
    }
}

@Controller
@RequestMapping("/templates")
class TemplateController {
    @GetMapping("/email-template")
    public String emailTemplate() {
        return "forward:/emailTemplate.html";
    }
}
