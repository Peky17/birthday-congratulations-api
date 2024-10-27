package com.congrats.app.services.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.client.RestTemplate;

@Service
public class SmtpService {
    @Value("${spring.mail.username}")
    private String myEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    private final RestTemplate restTemplate;

    public SmtpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /* HTML Email Template for sending email to client */
    @Async
    public void sendHtmlMailToClient(String name, String to, String subject, String text)
            throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        String htmlBody = buildHtmlTemplate(subject, text, name);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        javaMailSender.send(message);
    }

    private String buildHtmlTemplate(String subject, String text, String name) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/emailTemplate.html");
        @SuppressWarnings("resource")
        String template = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return template
                .replace("{{SUBJECT}}", subject)
                .replace("{{TEXT}}", text)
                .replace("{{NAME}}", name)
                .replace("{{DATE}}", java.time.LocalDate.now().toString());
    }
}