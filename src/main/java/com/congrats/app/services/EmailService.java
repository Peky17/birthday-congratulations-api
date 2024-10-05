package com.congrats.app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String myEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    private final RestTemplate restTemplate;

    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /* HTML Email Template for sending email to me */
    @Async
    public void sendHtmlMailToMe(String name, String to, String text)
            throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        String subject = "New client message";
        String htmlBody = buildHtmlToMe(name, to, subject, text);

        helper.setTo(myEmail);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        javaMailSender.send(message);
    }

    private String buildHtmlToMe(String name, String to, String subject, String text) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/templateToMe.html");
        @SuppressWarnings("resource")
        String template = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        return template
                .replace("{{name}}", name)
                .replace("{{to}}", to)
                .replace("{{subject}}", subject)
                .replace("{{text}}", text);

    }

    /* HTML Email Template for sending email to client */
    @Async
    public void sendHtmlMailToClient(String name, String to, String subject, String text)
            throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlBody = buildHtmlToClient(subject, text, name);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        javaMailSender.send(message);
    }

    private String buildHtmlToClient(String subject, String text, String name) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/templateToClient.html");
        @SuppressWarnings("resource")
        String template = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        return template.replace("{{subject}}", subject)
                .replace("{{text}}", text)
                .replace("{{name}}", name);
    }
}