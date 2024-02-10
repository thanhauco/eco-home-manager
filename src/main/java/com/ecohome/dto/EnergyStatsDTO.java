package com.ecohome.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyStatsDTO {
    private Double totalConsumption;
    private String period; // e.g., "TODAY", "WEEK", "MONTH"
}
