package com.ecohome.controller;

import com.ecohome.dto.AlertDTO;
import com.ecohome.model.AlertSeverity;
import com.ecohome.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<AlertDTO> createAlert(
            @RequestParam Long deviceId,
            @RequestParam String message,
            @RequestParam AlertSeverity severity) {
        AlertDTO created = alertService.createAlert(deviceId, message, severity);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/unresolved")
    public ResponseEntity<List<AlertDTO>> getUnresolvedAlerts() {
        return ResponseEntity.ok(alertService.getUnresolvedAlerts());
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<AlertDTO>> getAlertsByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(alertService.getAlertsByDevice(deviceId));
    }

    @GetMapping("/severity/{severity}")
    public ResponseEntity<List<AlertDTO>> getAlertsBySeverity(@PathVariable AlertSeverity severity) {
        return ResponseEntity.ok(alertService.getAlertsBySeverity(severity));
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<AlertDTO> resolveAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}
