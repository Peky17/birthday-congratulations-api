package com.congrats.app.controllers;

import com.congrats.app.services.domain.ScheduleService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/check-teacher-birthdays")
    public ResponseEntity<String> checkBirthdays() {
        try {
            scheduleService.checkTeacherBirthdays();
            return ResponseEntity.ok("Birthday check completed successfully.");
        } catch (MessagingException | IOException e) {
            // Log the exception (you can use a logging framework)
            return ResponseEntity.status(500).body("Error occurred while checking birthdays: " + e.getMessage());
        }
    }
}