package com.congrats.app.schedulers;

import com.congrats.app.services.domain.ScheduleService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BirthdayScheduler {
    @Autowired
    private ScheduleService scheduleService;
    // Method to check if it's a teacher's birthday every day at 6:00 AM
    @Scheduled(cron = "0 0 6 * * ?")
    public void checkBirthdays() throws MessagingException, IOException {
        // Check if it's a teacher's birthday and send email using the ScheduleService
        scheduleService.checkTeacherBirthdays();
    }
}