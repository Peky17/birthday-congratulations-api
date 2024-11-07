package com.congrats.app.services.domain;

import com.congrats.app.models.entities.TeacherEntity;
import com.congrats.app.models.entities.TemplateEntity;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private SmtpService emailService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TemplateService templateService;

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
                // Send congratulations email to teacher in context
                emailService.sendHtmlMailWithAttachment(
                        teacher.getName(),
                        teacher.getEmail(),
                        "¡Feliz cumpleaños! " + teacher.getName(),
                        template.getDescription()
                );
            }
        }
    }
}
