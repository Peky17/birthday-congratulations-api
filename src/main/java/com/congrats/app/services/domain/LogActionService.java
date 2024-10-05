package com.congrats.app.services.domain;

import com.congrats.app.models.entities.LogActionEntity;
import com.congrats.app.repositories.LogActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogActionService {

    @Autowired
    private LogActionRepository logActionRepository;

    // Create or update a log action
    public LogActionEntity saveLogAction(LogActionEntity logAction) {
        return logActionRepository.save(logAction);
    }

    // Update a log action
    public Optional<LogActionEntity> updateLogAction(Long id, LogActionEntity newDetails) {
        return logActionRepository.findById(id).map(logAction -> {
            logAction.setAction(newDetails.getAction());
            logAction.setDate(newDetails.getDate());
            // Set other fields as necessary
            return logActionRepository.save(logAction);
        });
    }

    // Get all log actions
    public List<LogActionEntity> getAllLogActions() {
        return logActionRepository.findAll();
    }

    // Get log action by ID
    public Optional<LogActionEntity> getLogActionById(Long id) {
        return logActionRepository.findById(id);
    }

    // Delete log action by ID
    public void deleteLogAction(Long id) {
        logActionRepository.deleteById(id);
    }
}