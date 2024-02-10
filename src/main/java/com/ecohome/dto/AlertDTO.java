package com.ecohome.dto;

import com.ecohome.model.AlertSeverity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertDTO {
    private Long id;
    private String message;
    private LocalDateTime timestamp;
    private boolean resolved;
    private AlertSeverity severity;
}
