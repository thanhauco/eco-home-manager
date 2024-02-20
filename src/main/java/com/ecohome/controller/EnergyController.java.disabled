package com.ecohome.controller;

import com.ecohome.dto.EnergyStatsDTO;
import com.ecohome.model.EnergyLog;
import com.ecohome.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    @Autowired
    private EnergyService energyService;

    @PostMapping("/log")
    public ResponseEntity<Void> logEnergyUsage(
            @RequestParam Long deviceId,
            @RequestParam double consumption) {
        energyService.logEnergyUsage(deviceId, consumption);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/stats/device/{deviceId}")
    public ResponseEntity<EnergyStatsDTO> getDeviceStats(
            @PathVariable Long deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(energyService.getEnergyStatsByDevice(deviceId, startDate, endDate));
    }

    @GetMapping("/stats/total")
    public ResponseEntity<EnergyStatsDTO> getTotalStats(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(energyService.getTotalEnergyStats(startDate, endDate));
    }

    @GetMapping("/logs/recent")
    public ResponseEntity<List<EnergyLog>> getRecentLogs(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(energyService.getRecentLogs(limit));
    }
}
