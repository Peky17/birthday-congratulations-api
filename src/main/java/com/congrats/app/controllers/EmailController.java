package com.congrats.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class EmailController {
    //@Autowired
    //private EmailService emailService;

    //@PostMapping("/sendEmail")
    /*public ResponseEntity<ApiResponse> sendHtmlMail(
            //@Valid @RequestBody Contact contact,
            @RequestParam("g-recaptcha-response") String recaptchaResponse) {
        if (!emailService.verifyCaptcha(recaptchaResponse)) {
            ApiResponse response = new ApiResponse("Captcha verification failed", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            // Send email to client
            emailService.sendHtmlMailToClient(contact.getName(), contact.getTo(),
                    contact.getSubject(), contact.getMessage());
            // Send email to me
            emailService.sendHtmlMailToMe(contact.getName(),
                    contact.getTo(), contact.getMessage());
            ApiResponse response = new ApiResponse("Email sent successfully", true);
            return ResponseEntity.ok(response);
        } catch (MessagingException | IOException e) {
            // Handle email sending errors
            ApiResponse response = new ApiResponse("Failed to send email", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    } */

    //@PostMapping("/sendEmailToClient")
    /*public void sendHtmlToClient(@RequestBody Contact contact) throws MessagingException, IOException {
        emailService.sendHtmlMailToClient(contact.getName(), contact.getTo(), contact.getSubject(),
                contact.getMessage());
    }*/

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
