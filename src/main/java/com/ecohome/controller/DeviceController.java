package com.ecohome.controller;

import com.ecohome.dto.DeviceCreateRequest;
import com.ecohome.dto.DeviceDTO;
import com.ecohome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getDeviceById(id));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<DeviceDTO>> getDevicesByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(deviceService.getDevicesByRoom(roomId));
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody DeviceCreateRequest request) {
        DeviceDTO created = deviceService.createDevice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DeviceDTO> updateDeviceStatus(
            @PathVariable Long id,
            @RequestParam boolean status) {
        return ResponseEntity.ok(deviceService.updateDeviceStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
