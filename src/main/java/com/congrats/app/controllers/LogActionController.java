package com.congrats.app.controllers;

import com.congrats.app.models.entities.LogActionEntity;
import com.congrats.app.services.domain.LogActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/log-actions")
public class LogActionController {

    @Autowired
    private LogActionService logActionService;

    @PostMapping
    public ResponseEntity<LogActionEntity> createLogAction(@RequestBody LogActionEntity logAction) {
        LogActionEntity savedLogAction = logActionService.saveLogAction(logAction);
        return ResponseEntity.ok(savedLogAction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogActionEntity> updateLogAction(@PathVariable Long id, @RequestBody LogActionEntity logActionDetails) {
        Optional<LogActionEntity> updatedLogAction = logActionService.updateLogAction(id, logActionDetails);
        return updatedLogAction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<LogActionEntity> getAllLogActions() {
        return logActionService.getAllLogActions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogActionEntity> getLogActionById(@PathVariable Long id) {
        Optional<LogActionEntity> logAction = logActionService.getLogActionById(id);
        return logAction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogAction(@PathVariable Long id) {
        logActionService.deleteLogAction(id);
        return ResponseEntity.noContent().build();
    }
}