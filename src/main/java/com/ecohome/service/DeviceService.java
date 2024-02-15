package com.ecohome.service;

import com.ecohome.dto.DeviceCreateRequest;
import com.ecohome.dto.DeviceDTO;
import com.ecohome.model.Device;
import com.ecohome.model.Room;
import com.ecohome.repository.DeviceRepository;
import com.ecohome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<DeviceDTO> getAllDevices() {
        return deviceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));
        return convertToDTO(device);
    }

    public List<DeviceDTO> getDevicesByRoom(Long roomId) {
        return deviceRepository.findByRoomId(roomId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO createDevice(DeviceCreateRequest request) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + request.getRoomId()));

        Device device = new Device();
        device.setName(request.getName());
        device.setType(request.getType());
        device.setRoom(room);
        device.setStatus(true);
        device.setCreatedAt(LocalDateTime.now());

        Device savedDevice = deviceRepository.save(device);
        return convertToDTO(savedDevice);
    }

    public DeviceDTO updateDeviceStatus(Long id, boolean status) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));
        device.setStatus(status);
        device.setUpdatedAt(LocalDateTime.now());
        Device updatedDevice = deviceRepository.save(device);
        return convertToDTO(updatedDevice);
    }

    public void deleteDevice(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new RuntimeException("Device not found with id: " + id);
        }
        deviceRepository.deleteById(id);
    }

    private DeviceDTO convertToDTO(Device device) {
        DeviceDTO dto = new DeviceDTO();
        dto.setId(device.getId());
        dto.setName(device.getName());
        dto.setType(device.getType());
        dto.setStatus(device.isStatus());
        dto.setRoomId(device.getRoom().getId());
        dto.setRoomName(device.getRoom().getName());
        return dto;
    }
}
