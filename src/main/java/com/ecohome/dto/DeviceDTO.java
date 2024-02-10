package com.ecohome.dto;

import com.ecohome.model.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
    private Long id;
    private String name;
    private DeviceType type;
    private boolean isOn;
    private Double powerRating;
    private Long roomId;
    private String roomName;
    private LocalDateTime lastActive;
}
