package com.congrats.app.controllers;

import com.congrats.app.models.dto.ContactDTO;
import com.congrats.app.services.domain.SmtpService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/mail")
public class SmtpController {
    @Autowired
    private SmtpService emailService;

    @PostMapping("/sendEmailToTeacher")
    public void sendHtmlToClient(@RequestBody ContactDTO contact) throws MessagingException, IOException {
        emailService.sendHtmlMailToClient(contact.getName(), contact.getTo(), contact.getSubject(),
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
    @GetMapping("/email-cliente")
    public String clientTemplate() {
        return "forward:/templateToClient.html";
    }

    @GetMapping("/email-to-me")
    public String toMeTemplate() {
        return "forward:/templateToMe.html";
    }
}
