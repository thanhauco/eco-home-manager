package com.ecohome.service;

import com.ecohome.dto.AlertDTO;
import com.ecohome.model.Alert;
import com.ecohome.model.AlertSeverity;
import com.ecohome.model.Device;
import com.ecohome.repository.AlertRepository;
import com.ecohome.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public AlertDTO createAlert(Long deviceId, String message, AlertSeverity severity) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + deviceId));

        Alert alert = new Alert();
        alert.setDevice(device);
        alert.setMessage(message);
        alert.setSeverity(severity);
        alert.setResolved(false);
        alert.setCreatedAt(LocalDateTime.now());

        Alert savedAlert = alertRepository.save(alert);
        return convertToDTO(savedAlert);
    }

    public List<AlertDTO> getUnresolvedAlerts() {
        return alertRepository.findByResolvedFalse().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AlertDTO> getAlertsByDevice(Long deviceId) {
        return alertRepository.findByDeviceId(deviceId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AlertDTO> getAlertsBySeverity(AlertSeverity severity) {
        return alertRepository.findBySeverity(severity).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AlertDTO resolveAlert(Long alertId) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + alertId));

        alert.setResolved(true);
        alert.setResolvedAt(LocalDateTime.now());

        Alert updatedAlert = alertRepository.save(alert);
        return convertToDTO(updatedAlert);
    }

    private AlertDTO convertToDTO(Alert alert) {
        AlertDTO dto = new AlertDTO();
        dto.setId(alert.getId());
        dto.setDeviceId(alert.getDevice().getId());
        dto.setDeviceName(alert.getDevice().getName());
        dto.setMessage(alert.getMessage());
        dto.setSeverity(alert.getSeverity());
        dto.setResolved(alert.isResolved());
        dto.setCreatedAt(alert.getCreatedAt());
        dto.setResolvedAt(alert.getResolvedAt());
        return dto;
    }
}
