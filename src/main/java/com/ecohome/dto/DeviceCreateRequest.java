package com.ecohome.dto;

import com.ecohome.model.DeviceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCreateRequest {
    @NotBlank(message = "Device name is required")
    private String name;

    @NotNull(message = "Device type is required")
    private DeviceType type;

    private Double powerRating;
    private Long roomId;
}
