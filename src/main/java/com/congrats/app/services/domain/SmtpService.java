package com.congrats.app.services.domain;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class SmtpService {
    @Value("${spring.mail.username}")
    private String myEmail;

    private final JavaMailSender javaMailSender;

    public SmtpService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlMailWithAttachment(String name, String to, String subject, String text)
            throws MessagingException, IOException, DocumentException {
        // 1. Crear el HTML con datos dinámicos para enviar por email
        String htmlContent = loadHtmlTemplateToSend(Map.of("name", name,
                "text", text, "date", java.time.LocalDate.now().toString()));
        // 2. Crear HTML con datos dinámicos para generar PDF y adjuntar
        String htmlToPDFContent = loadHtmlToPDFTemplate(Map.of("name", name,
                "text", text, "date", java.time.LocalDate.now().toString()));
        // 3. Convertir HTML a PDF
        File pdfFile = generatePdfFromHtml(htmlToPDFContent);
        // 4. Crear y enviar el correo con el PDF adjunto
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.addAttachment("attachment.pdf", pdfFile);
        javaMailSender.send(message);
        // 4. Borrar archivo temporal
        Files.deleteIfExists(pdfFile.toPath());
    }

    private String loadHtmlTemplateToSend(Map<String, String> variables) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/emailTemplate.html");
        String template = new String(Files.readAllBytes(Paths.get(resource.getURI())), StandardCharsets.UTF_8);
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            template = template.replace("{{" + entry.getKey().toUpperCase() + "}}", entry.getValue());
        }
        return template;
    }

    private String loadHtmlToPDFTemplate(Map<String, String> variables) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/pdfTemplate.html");
        String templatePDF = new String(Files.readAllBytes(Paths.get(resource.getURI())), StandardCharsets.UTF_8);
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            templatePDF = templatePDF.replace("{{" + entry.getKey().toUpperCase() + "}}", entry.getValue());
        }
        return templatePDF;
    }

    private File generatePdfFromHtml(String htmlContent) throws IOException, DocumentException {
        File pdfFile = File.createTempFile("template", ".pdf");
        try (OutputStream outputStream = new FileOutputStream(pdfFile)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        return pdfFile;
    }
}