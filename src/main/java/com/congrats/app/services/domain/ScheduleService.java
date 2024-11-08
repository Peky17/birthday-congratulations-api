package com.congrats.app.services.domain;

import com.congrats.app.models.entities.EmailEntity;
import com.congrats.app.models.entities.TeacherEntity;
import com.congrats.app.models.entities.TemplateEntity;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private SmtpService smtpService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TemplateService templateService;

    // Create an email entity
    EmailEntity email = new EmailEntity();

    public void checkTeacherBirthdays() throws MessagingException, IOException {
        List<TeacherEntity> teachers = teacherService.getAllTeachers();
        LocalDate today = LocalDate.now();
        // Iterate over all teachers to check if it's their birthday
        for (TeacherEntity teacher : teachers) {
            if (teacher.getBirthdate().getMonth() == today.getMonth() &&
                    teacher.getBirthdate().getDayOfMonth() == today.getDayOfMonth()) {
                // Get latest template data
                List<TemplateEntity> templateData = templateService.getLatestTemplate();
                // Get the latest template
                TemplateEntity template = templateData.get(0);
                // Validate if the template is empty and send error 400
                if (template.getDescription().isEmpty()) {
                    throw new IllegalArgumentException("Template data is empty");
                }
                // Send congratulations email to teacher in context
                smtpService.sendHtmlMailWithAttachment(
                        teacher.getName(),
                        teacher.getEmail(),
                        "¡Feliz cumpleaños! " + teacher.getName(),
                        template.getDescription()
                );
                // Save the email sent to the teacher
                email.setDate(today);
                email.setMessage(template.getDescription());
                email.setTeacher(teacher);
                emailService.createEmail(email);
            }
        }
    }
}
